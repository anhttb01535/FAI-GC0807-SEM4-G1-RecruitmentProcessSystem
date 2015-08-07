/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.action;

import hrgroup.controller.InterviewDataController;

/**
 *
 * @author trant
 */
public class AddInterview {
    
    private String interviewer;
    private String vacancy;
    private String startDate;
    private String endDate;
    
    public AddInterview() {
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public String execute() throws Exception {
        InterviewDataController controller = new InterviewDataController();
        controller.create(interviewer, vacancy, startDate, endDate);
        return "success";
    }   
}
