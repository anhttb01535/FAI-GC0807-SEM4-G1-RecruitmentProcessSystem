/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import hrgroup.controller.ApplicantDatacontroller;
import hrgroup.controller.DepartmentDataController;
import hrgroup.db.entities.Department;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author trant
 */
public class LoginApplicant {
    
    private String username;
    private String password;
    private String error;
    private List<String> departments = new ArrayList<>();
    
    private ApplicantDatacontroller controller;
    private DepartmentDataController controller1;
    public LoginApplicant() {
        controller = new ApplicantDatacontroller();
        controller1 = new DepartmentDataController();
        List<Department> departs = controller1.findAllDepartment();
        for(Department d:departs) {
            departments.add(d.getName());
        }
    }

    public String getUsername() {
        return username;
    }

    public List<String> getDepartments() {
        return departments;
    }

    public void setDepartments(List<String> departments) {
        this.departments = departments;
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
            session.put("loggedinApp", "true");
            session.put("username", getUsername());
            return Action.SUCCESS;
        }
        else {
            error="Username or Password incorrect";
            return Action.ERROR;
        }
    }
    
}
