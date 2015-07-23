/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.controller;

import hrgroup.business.SHAEncoder;
import hrgroup.db.controller.AdministratorJpaController;
import hrgroup.db.entities.Administrator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tuananh
 */
public class AdminDataController {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("RecruitmentProcessSystemPU");
    private static final AdministratorJpaController jpaController = new AdministratorJpaController(emf);
    
    public static List<Administrator> findAllAdmin() {
        return jpaController.findAdministratorEntities();
    }
    
    public static boolean checkLogin(String username, String password) throws Exception {
        List<Administrator> admins = findAllAdmin();
        String encodePass = SHAEncoder.Encode(password);
        if(username.isEmpty()) {
                throw new Exception("Username is required");
            }
            if(password.isEmpty()) {
                throw new Exception("Password is required");
            }
        for(Administrator admin:admins) {
            if(username.equals(admin.getUsername())) {
                if(encodePass.equals(admin.getPassword())) {
                    return true;
                }
            }            
        }
        return false;
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
