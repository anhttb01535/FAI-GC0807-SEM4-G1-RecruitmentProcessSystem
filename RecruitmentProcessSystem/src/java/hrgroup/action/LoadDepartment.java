/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.action;

import hrgroup.controller.DepartmentDataController;
import hrgroup.db.entities.Department;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trant
 */
public class LoadDepartment {
    
    private List<String> departments = new ArrayList<>();
    private String status = "no";
    public LoadDepartment() {
    }

    public List<String> getDepartments() {
        return departments;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDepartments(List<String> departments) {
        this.departments = departments;
    }
    
    public String execute() throws Exception {
        DepartmentDataController controller = new DepartmentDataController();
        List<Department> departs = controller.findAllDepartment();
        for(Department d:departs) {
            departments.add(d.getName());
        }
        return "success";
    }
    
}
