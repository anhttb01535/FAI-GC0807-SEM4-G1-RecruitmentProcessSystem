/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.controller;

import hrgroup.db.controller.InterviewResultJpaController;
import hrgroup.db.entities.InterviewResult;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author trant
 */
public class InterviewResultDataController {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("RecruitmentProcessSystemPU");
    InterviewResultJpaController jpaController = new InterviewResultJpaController(emf);

    public InterviewResultDataController() {
    }
    
    public List<InterviewResult> findAllInterviewResult() {
        return jpaController.findInterviewResultEntities();
    }
    
    public List<InterviewResult> findInterviewResultByAppId(String id) {
        List<InterviewResult> irs = findAllInterviewResult();
        List<InterviewResult> selectIrs = new ArrayList<>();
        for(InterviewResult ir:irs) {
            if(ir.getIdApplicant().getId().equals(id)) {
                selectIrs.add(ir);
            }
        }
        return selectIrs;
    }
    
    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
