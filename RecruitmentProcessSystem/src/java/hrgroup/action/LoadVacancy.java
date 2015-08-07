/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.action;

import hrgroup.controller.VacancyDataController;
import hrgroup.db.entities.Vacancy;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trant
 */
public class LoadVacancy {
    
    private String department;
    private List<Vacancy> vacancies = new ArrayList<>();
    private VacancyDataController controller;
    
    public LoadVacancy() {
        controller = new VacancyDataController();
        
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }
    
    public String execute() throws Exception {
        vacancies = controller.findVacanciesByDepartment(department);
        return "success";
    }
    
}
