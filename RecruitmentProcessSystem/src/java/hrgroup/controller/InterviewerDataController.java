/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.controller;

import hrgroup.db.controller.InterviewerJpaController;
import hrgroup.db.entities.Interviewer;
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
public class InterviewerDataController {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("RecruitmentProcessSystemPU");
    InterviewerJpaController jpaController = new InterviewerJpaController(emf);

    public InterviewerDataController() {
    }
    
    public List<Interviewer> findAllInterviewer() {
        return jpaController.findInterviewerEntities();
    }
    
    public List<Interviewer> findInterviewerByDepartment(String departmentId) {
        List<Interviewer> interviewers = findAllInterviewer();
        List<Interviewer> selectInter = new ArrayList<>();
        for(Interviewer i:interviewers) {
            if(i.getIdDepartment().getId().equals(departmentId)) {
                selectInter.add(i);
            }
        }
        return selectInter;
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
