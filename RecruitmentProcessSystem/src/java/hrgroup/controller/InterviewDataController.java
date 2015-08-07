/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.controller;

import hrgroup.db.controller.InterviewJpaController;
import hrgroup.db.entities.Interview;
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
public class InterviewDataController {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("RecruitmentProcessSystemPU");
    InterviewJpaController controller = new InterviewJpaController(emf);

    public InterviewDataController() {
    }

    public List<Interview> findAllInterview() {
        return controller.findInterviewEntities();
    }
    
    public List<Interview> findInterviewByVacancy(String id) {
        List<Interview> interviews = findAllInterview();
        List<Interview> selectInter = new ArrayList<>();
        for(Interview i:interviews) {
            if(i.getIdVacancy().getId().equals(id)) {
                selectInter.add(i);
            }
        }
        return selectInter;
    }
    
    public Interview findInterviewById(String id) {
        return controller.findInterview(id);
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
