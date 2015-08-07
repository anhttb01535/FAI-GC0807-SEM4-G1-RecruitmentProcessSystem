/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.controller;

import hrgroup.db.controller.VacancyJpaController;
import hrgroup.db.entities.Vacancy;
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
public class VacancyDataController {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("RecruitmentProcessSystemPU");
    VacancyJpaController jpaController = new VacancyJpaController(emf);

    public VacancyDataController() {
    }
    
    public List<Vacancy> findAllVacancies() {
        return jpaController.findVacancyEntities();
    }
    
    public List<Vacancy> findVacanciesByDepartment(String department) {
        List<Vacancy> vacancies = findAllVacancies();
        List<Vacancy> selectVacan = new ArrayList<>();
        for(Vacancy v:vacancies) {
            if(v.getIdDepartment().getName().equals(department)) {
                selectVacan.add(v);
            }
        }
        return selectVacan;
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
