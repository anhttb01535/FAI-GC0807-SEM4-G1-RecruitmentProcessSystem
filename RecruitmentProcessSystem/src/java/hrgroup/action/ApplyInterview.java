/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.action;

import com.opensymphony.xwork2.ActionContext;
import hrgroup.controller.ApplicantDatacontroller;
import hrgroup.controller.InterviewResultDataController;

/**
 *
 * @author trant
 */
public class ApplyInterview {
    private String id;
    
    InterviewResultDataController controller = new InterviewResultDataController();
    public ApplyInterview() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    public String execute() throws Exception {
        String username = (String) ActionContext.getContext().getSession().get("username");
        ApplicantDatacontroller appController = new ApplicantDatacontroller();
        String appId = appController.findApplicantByUsername(username).getId();
        controller.create(appId, id);
        return "success";
    }
    
}
