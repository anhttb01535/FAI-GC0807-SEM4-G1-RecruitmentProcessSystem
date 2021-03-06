/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import hrgroup.controller.AdminDataController;
import java.util.Map;

/**
 *
 * @author tuananh
 */
public class LoginAdmin {
    
    private String username;
    private String password;
    private String error;
    private AdminDataController controller;

    public LoginAdmin() {
        controller = new AdminDataController();
    }

//    public List<String> getTitles() {
//        return titles;
//    }
//
//    public void setTitles(List<String> titles) {
//        this.titles = titles;
//    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        if(controller.checkLogin(username, password)) {
            session.put("loggedin", "true");
            session.put("username", getUsername());
            return Action.SUCCESS;
        }
        else {
            error="Username or Password incorrect";
            return Action.ERROR;
        }
    }
    
}
