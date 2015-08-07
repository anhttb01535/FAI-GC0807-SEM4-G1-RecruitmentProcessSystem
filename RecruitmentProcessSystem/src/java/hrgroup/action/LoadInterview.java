/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.action;

import hrgroup.controller.DepartmentDataController;
import hrgroup.controller.InterviewerDataController;
import hrgroup.controller.VacancyDataController;
import hrgroup.db.entities.Department;
import hrgroup.db.entities.Interviewer;
import hrgroup.db.entities.Vacancy;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trant
 */
public class LoadInterview {
    
    private List<Vacancy> vacancies = new ArrayList<>();
    private List<Department> departments = new ArrayList<>();
    private List<Interviewer> interviewers = new ArrayList<>();
    
    public LoadInterview() {
        
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Interviewer> getInterviewers() {
        return interviewers;
    }

    public void setInterviewers(List<Interviewer> interviewers) {
        this.interviewers = interviewers;
    }
    
    public String execute() throws Exception {
        DepartmentDataController controller = new DepartmentDataController();
        VacancyDataController controller1 = new VacancyDataController();
        InterviewerDataController controller2 = new InterviewerDataController();
        vacancies = controller1.findAllVacancies();
        interviewers =  controller2.findAllInterviewer();
        departments = controller.findAllDepartment();
        return "success";
    }
    
}
