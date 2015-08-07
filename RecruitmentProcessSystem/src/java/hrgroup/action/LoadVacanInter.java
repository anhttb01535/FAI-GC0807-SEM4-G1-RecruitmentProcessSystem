/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.action;

import hrgroup.controller.DepartmentDataController;
import hrgroup.db.entities.Department;
import hrgroup.db.entities.Interviewer;
import hrgroup.db.entities.Vacancy;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trant
 */
public class LoadVacanInter {

    private String department;
    private List<String> interviewers = new ArrayList<>();
    private List<String> vacancies = new ArrayList<>();
    
    public LoadVacanInter() {
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<String> getInterviewers() {
        return interviewers;
    }

    public void setInterviewers(List<String> interviewers) {
        this.interviewers = interviewers;
    }

    public List<String> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<String> vacancies) {
        this.vacancies = vacancies;
    }

    
    public String execute() throws Exception {
        List<Vacancy> vacans = new ArrayList<>();
        List<Interviewer> inters = new ArrayList<>();
        DepartmentDataController controller = new DepartmentDataController();
        Department depart = controller.findDepartmentByName(department);
        vacans = depart.getVacancyList();
        inters = depart.getInterviewerList();
        for(Interviewer i:inters) {
            interviewers.add(i.getId());
        }
        for(Vacancy v:vacans) {
            vacancies.add(v.getTitle());
        }
        return "success";
    }

}
