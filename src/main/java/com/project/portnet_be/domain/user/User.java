package com.project.portnet_be.domain.user;

import com.project.portnet_be.structure.utils.Authority;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String username;
    private String password;
    private String nickname;
    private String email;

    private String authority;

    @Builder
    public User(String username, String password, String nickname, String email, String authority) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.authority = authority;
    }
}
