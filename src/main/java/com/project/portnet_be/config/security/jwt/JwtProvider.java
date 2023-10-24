package com.project.portnet_be.config.security.jwt;

import com.project.portnet_be.domain.jwt.RefreshToken;
import com.project.portnet_be.domain.jwt.RefreshTokenReopsitory;
import com.project.portnet_be.domain.user.User;
import com.project.portnet_be.domain.user.UserRepository;
import com.project.portnet_be.structure.principal.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtProvider {

    private static final String secretKey = "VlwEyVBsYt9V7zq57TejMnVUyzblYcfPQye08f7MGVA9XkHa";
    // 만료시간 accestoken 30분 refresh 토큰 1주
    private long accesTokenVaildTime = Duration.ofMinutes(30).toMillis();
    private long refreshTokenVaildTime = Duration.ofDays(7).toMillis();

    private @Autowired RefreshTokenReopsitory refreshTokenReopsitory;
    private @Autowired UserRepository userRepository;

    /**
     * JWT 토큰 생성
     *
     * */
    public JwtToken creatToken(Authentication authentication, User user){

        log.info("Start Create Token");

        JwtToken jwtToken = new JwtToken();
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        // 권한 가져오기
        String auhorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining());
        //현재시간 가져오기
        Date now = new Date();


        //accesToken 생성
        String accesToken = Jwts.builder().setSubject(authentication.getName())
                .claim("AUTH",auhorities)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(new Date(now.getTime()+ accesTokenVaildTime))
                .compact();
        //refreshToken 생성
        String refreshToken = Jwts.builder()
                .setSubject(authentication.getName())
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(new Date(now.getTime()+ refreshTokenVaildTime))
                .compact();

        log.info("Token make:" + accesToken);

        RefreshToken refreshTokenEntity = new RefreshToken(authentication.getName(), refreshToken);

        refreshTokenReopsitory.save(refreshTokenEntity);
        log.info("RefreshToken Create Success:" + accesToken);


        jwtToken.setAccesToken(accesToken);
        jwtToken.setRefreshToken(refreshToken);
        jwtToken.setNickname(user.getNickname());
        jwtToken.setUsername(user.getUsername());

        log.info("Finish Create Token");

        return jwtToken;

    }


    /**
     * jwt 토큰 검증
     * */
    public JwtCode validateToken(String token){
        log.info("Start Token validate");
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return JwtCode.ACCESS;
        }catch (io.jsonwebtoken.security.SignatureException | MalformedJwtException e){
            log.error("Invaidate Token");
        }catch (ExpiredJwtException e){
            log.info("ExpiredToken Checking RefreshToken");
            return JwtCode.EXPIRED;
        }catch (UnsupportedJwtException e){
          log.error("Unsupported Token");
        }catch(IllegalArgumentException e){
            log.error("Jwt is Empty");
        }
        return JwtCode.DENIED;
    }

    /**
     * jwt 토큰 복호화
     * */

    public Authentication getAuthentication(String token){
        Claims claims = parseClaims(token);

        if(claims.get("AUTH") == null) {
            log.error("Token not find Auth Info ");
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
    }

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("AUTH").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        CustomUserDetails userDetails = new CustomUserDetails(claims.getSubject(), "", authorities.toString());

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }


    private Claims parseClaims(String token){
        try {
            return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }

    @Transactional
    public JwtToken reissueRefreshToken(String refreshToken) throws RuntimeException{
        Authentication authentication = getAuthentication(refreshToken);

        RefreshToken findRefreshToken = refreshTokenReopsitory.findByUserId(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("userId : " + authentication.getName() + " was not found"));

        if(findRefreshToken.getRefreshToken().equals(refreshToken)){
            User user = userRepository.findByUsername(authentication.getName())
                    .orElseThrow(() -> new UsernameNotFoundException("userId : " + authentication.getName() + " was not found"));
            JwtToken newToken = creatToken(authentication, user);
            return newToken;
        }else{
            log.error("Not Equals Token");
            return null;
        }

    }
}
