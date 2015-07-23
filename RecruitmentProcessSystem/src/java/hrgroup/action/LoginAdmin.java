/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.action;

import com.opensymphony.xwork2.Action;
import hrgroup.business.SHAEncoder;
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

    public LoginAdmin() {
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
        String encodePass = SHAEncoder.Encode(password);
        if(AdminDataController.checkLogin(username, encodePass)) {
            return Action.SUCCESS;
        }
        else {
            return Action.ERROR;
        }
    }
    
}
