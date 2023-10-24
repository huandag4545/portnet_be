package com.project.portnet_be.application.popol.service;

import com.project.portnet_be.domain.carrer.CarrerEntity;
import com.project.portnet_be.domain.carrer.CarrerRepository;
import com.project.portnet_be.domain.popol.PopolEntity;
import com.project.portnet_be.domain.popol.PopolRepository;
import com.project.portnet_be.domain.project.ProjectEntity;
import com.project.portnet_be.domain.project.ProjectRepository;
import com.project.portnet_be.application.popol.model.PopolModel;
import com.project.portnet_be.dto.popol.carrer.Carrer;
import com.project.portnet_be.dto.popol.popol.Popol;
import com.project.portnet_be.dto.popol.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PopolService {

    private @Autowired PopolRepository poPolRepository;
    private @Autowired CarrerRepository carrerRepository;

    private @Autowired ProjectRepository projectRepository;
    /**
     * 생성용
     * */
    public PopolModel read(){

        PopolModel model = new PopolModel();
        Popol popol = new Popol();
        Carrer carrer = new Carrer();
        Project project = new Project();

        model.setPopol(popol);
        model.setCarrer(carrer);
        model.setProject(project);

        return model;
    }

    /**
     * 상세조회
     *
     * **/
    public PopolModel readDetail(Long userId){
        PopolModel model = new PopolModel();


        Optional<PopolEntity> optPoPolEntity = poPolRepository.findByUserId(userId);
        PopolEntity poPolEntity = optPoPolEntity.get();

        Optional<CarrerEntity> carrerEntity = carrerRepository.findByPopolId(poPolEntity.getPopolId());
        Optional<ProjectEntity> projectEntity = projectRepository.findByPopolId(poPolEntity.getPopolId());

        Popol popol = new Popol(poPolEntity);
        Carrer carrer = new Carrer(carrerEntity.get());
        Project project = new Project(projectEntity.get());

        model.setPopol(popol);
        model.setCarrer(carrer);
        model.setProject(project);


        return model;
    }

    public Long creatPopol(PopolModel model){

        PopolEntity poPolEntity = PopolEntity.builder()
                        .userId(model.getPopol().getUserId())
                                .jobs(model.getPopol().getJobs())
                                        .devSite(model.getPopol().getDevSite())
                                                .devStack(model.getPopol().getDevStack())
                                                        .rcvChanner(model.getPopol().getRcvChanner())
                                                                .build();

        poPolRepository.save(poPolEntity);

        CarrerEntity carrerEntity = CarrerEntity.builder()
                        .popolId(poPolEntity.getPopolId())
                                .startDt(model.getCarrer().getStartDt())
                                        .endDt(model.getCarrer().getEndDt())
                                                .company(model.getCarrer().getCompany())
                                                        .contents(model.getCarrer().getContents())
                                                                .department(model.getCarrer().getDepartment())
                                                                        .build();
        carrerRepository.save(carrerEntity);

        ProjectEntity projectEntity = ProjectEntity.builder()
                .popolId(poPolEntity.getPopolId())
                .title(model.getProject().getTitle())
                .link(model.getProject().getLink())
                .startDt(model.getProject().getStartDt())
                .finishDt(model.getProject().getFinishDt())
                .useStack(model.getProject().getUseStack())
                .contents(model.getProject().getContents())
                .build();

        projectRepository.save(projectEntity);

        return poPolEntity.getPopolId();
    }

    public Long updtPopol(PopolModel model){

        Optional<PopolEntity> optPopolEntity = poPolRepository.findByPopolId(model.getPopol().getPopolId());


        Optional<CarrerEntity> optCarrerEntity = carrerRepository.findByPopolId(optPopolEntity.get().getPopolId());
        Optional<ProjectEntity> optProjectEntity = projectRepository.findByPopolId(optPopolEntity.get().getPopolId());

        PopolEntity popolEntity = optPopolEntity.get();
        CarrerEntity carrerEntity = optCarrerEntity.get();
        ProjectEntity projectEntity = optProjectEntity.get();

        popolEntity.updatePopol(model.getPopol());
        carrerEntity.updateCarrer(model.getCarrer());
        projectEntity.updateProject(model.getProject());

        poPolRepository.save(popolEntity);
        carrerRepository.save(carrerEntity);
        projectRepository.save(projectEntity);

        return popolEntity.getPopolId();

    }

}
