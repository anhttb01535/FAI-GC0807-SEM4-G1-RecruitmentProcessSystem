/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.action;

import hrgroup.controller.InterviewDataController;
import hrgroup.db.entities.Interview;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trant
 */
public class GetAllInterview {
    
    private List<Interview> interviews = new ArrayList<>();
    public GetAllInterview() {
    }

    public List<Interview> getInterviews() {
        return interviews;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
    }
    
    
    public String execute() throws Exception {
        InterviewDataController controller = new InterviewDataController();
        interviews = controller.findAllInterview();
        return "success";
    }
    
}
