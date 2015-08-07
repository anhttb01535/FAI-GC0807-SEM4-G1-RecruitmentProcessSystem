/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.controller;

import hrgroup.db.controller.InterviewJpaController;
import hrgroup.db.entities.Interview;
import hrgroup.db.entities.Interviewer;
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
    
    public void create(String idInterviewer,String titleVacancy, String startDate, String endDate) throws Exception {
        Interview interview = new Interview();
        interview.setDateOfStartInterview(startDate);
        interview.setDateOfEndInterview(endDate);
        InterviewerDataController controller1 = new InterviewerDataController();
        VacancyDataController controller2 = new VacancyDataController();
        Interviewer in = controller1.findInterviewerById(idInterviewer);
        Vacancy van = controller2.findVacancyByTitle(titleVacancy);
        interview.setIdInterviewer(in);
        interview.setIdVacancy(van);
        interview.setId(genRandomID());
        controller.create(interview);
    }
    
    //Tự động sinh id
    public String genRandomID() {
        List<Interview> interviews = findAllInterview();
        String id = "";
        List<String> ids = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        int num = 0;
        int n = 0;
        for (Interview c : interviews) {
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
            id = "I000" + String.valueOf(num);
        }
        if (10 <= num && num < 100) {
            id = "I00" + String.valueOf(num);
        }
        if (100 <= num && num < 1000) {
            id = "I0" + String.valueOf(num);
        }
        if (1000 <= num && num < 10000) {
            id = "I" + String.valueOf(num);
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
