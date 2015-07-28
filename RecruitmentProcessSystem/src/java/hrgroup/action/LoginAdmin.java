/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.action;

import com.opensymphony.xwork2.Action;
import hrgroup.controller.AdminDataController;
import java.util.Map;

/**
 *
 * @author tuananh
 */
public class LoginAdmin {
    
    private String username;
    private String password;
    private Map<String,Object> sessionMap;
    private AdminDataController controller;

    public LoginAdmin() {
        controller = new AdminDataController();
    }
    
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

    public Map<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }
    
    public String execute() throws Exception {
        if(controller.checkLogin(username, password)) {
            return Action.SUCCESS;
        }
        else {
            return Action.ERROR;
        }
    }
    
}
