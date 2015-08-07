/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.controller;

import hrgroup.db.controller.DepartmentJpaController;
import hrgroup.db.entities.Department;
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
public class DepartmentDataController {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("RecruitmentProcessSystemPU");
    DepartmentJpaController jpaController = new DepartmentJpaController(emf);

    public DepartmentDataController() {       
    }
    
    public List<Department> findAllDepartment() {
        return jpaController.findDepartmentEntities();
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
