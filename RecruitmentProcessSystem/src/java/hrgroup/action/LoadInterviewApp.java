/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.action;

import com.opensymphony.xwork2.ActionContext;
import hrgroup.controller.ApplicantDatacontroller;
import hrgroup.controller.InterviewDataController;
import hrgroup.controller.InterviewResultDataController;
import hrgroup.db.entities.Interview;
import hrgroup.db.entities.InterviewResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author trant
 */
public class LoadInterviewApp {

    private String id;
    private InterviewDataController controller = new InterviewDataController();
    private List<Interview> interviews = new ArrayList<>();
    List<String> status = new ArrayList<>();

    public LoadInterviewApp() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Interview> getInterviews() {
        return interviews;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }


    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        String appUsername = (String) session.get("username");
        ApplicantDatacontroller controller1 = new ApplicantDatacontroller();
        String appId = controller1.findApplicantByUsername(appUsername).getId();
        InterviewResultDataController controller2 = new InterviewResultDataController();
        List<InterviewResult> irs = controller2.findInterviewResultByAppId(appId);
        interviews = controller.findInterviewByVacancy(id);
        for (Interview i : interviews) {
            for (InterviewResult ir : irs) {
                int d = 0;
                if (ir.getIdInterview().getId().equals(i.getId())) {
                    d++;
                }
                if (d == 0) {
                    status.add("no");
                } else {
                    status.add("yes");
                }
            }
        }
        return "success";
    }

}
