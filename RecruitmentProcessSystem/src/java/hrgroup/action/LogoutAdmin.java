/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.action;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;

/**
 *
 * @author trant
 */
public class LogoutAdmin {
    
    public LogoutAdmin() {
    }
    
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        session.remove("loggedin");
        return "success";
    }
    
}
