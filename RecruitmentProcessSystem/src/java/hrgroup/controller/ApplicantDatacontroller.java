/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.controller;

import hrgroup.db.controller.ApplicantJpaController;
import hrgroup.db.entities.Applicant;
import java.util.ArrayList;
import java.util.Date;
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
public class ApplicantDatacontroller {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("RecruitmentProcessSystemPU");
    private static final ApplicantJpaController jpaController = new ApplicantJpaController(emf);

    //Lấy toàn bộ ứng viên
    public static List<Applicant> findAllApplicant() {
        return jpaController.findApplicantEntities();
    }

    //Thêm mới 1 ứng viên
    public static void addApplicant(String name, String birthday, String email, String sex, String address, String username, String password) throws Exception {
        String id = genRandomID();
        Applicant applicant =  new Applicant(id, name, birthday, email, sex, address, new Date().toString(), username, password, "Not in Process");
        jpaController.create(applicant);
    }
    
    //Tìm ứng viên theo id
    public static Applicant findApplicantById(String id) {
        return jpaController.findApplicant(id);
    }

    //Tự động sinh id
    public static String genRandomID() {
        List<Applicant> applicants = findAllApplicant();
        String id = "";
        List<String> ids = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        int num = 0;
        int n = 0;
        for (Applicant c : applicants) {
            ids.add(c.getId());
        }
        for (String str : ids) {
            int idint = Integer.parseInt(str.substring(1));
            numbers.add(idint);
        }
        for (int i = 0; i < numbers.size() - 1; i++) {
            for (int j = 1; j < numbers.size(); j++) {
                if (numbers.get(i) > numbers.get(j)) {
                    int d = numbers.get(i);
                    numbers.set(i, numbers.get(j));
                    numbers.set(j, d);
                }
            }
        }
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i + 1) - numbers.get(i) > 1) {
                num = numbers.get(i) + 1;
                n++;
            }
        }
        if (n == 0) {
            num = numbers.get(numbers.size() - 1) + 1;
        }
        if (num < 10) {
            id = "A000" + String.valueOf(num);
        }
        if (10 <= num && num < 100) {
            id = "A00" + String.valueOf(num);
        }
        if (100 <= num && num < 1000) {
            id = "A0" + String.valueOf(num);
        }
        if (1000 <= num && num < 10000) {
            id = "A" + String.valueOf(num);
        }
        return id;
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
