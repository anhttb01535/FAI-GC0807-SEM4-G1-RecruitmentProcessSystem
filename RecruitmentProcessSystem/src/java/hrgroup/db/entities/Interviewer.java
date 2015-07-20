/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.db.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tuananh
 */
@Entity
@Table(name = "Interviewer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Interviewer.findAll", query = "SELECT i FROM Interviewer i"),
    @NamedQuery(name = "Interviewer.findById", query = "SELECT i FROM Interviewer i WHERE i.id = :id"),
    @NamedQuery(name = "Interviewer.findByName", query = "SELECT i FROM Interviewer i WHERE i.name = :name"),
    @NamedQuery(name = "Interviewer.findByUsername", query = "SELECT i FROM Interviewer i WHERE i.username = :username"),
    @NamedQuery(name = "Interviewer.findByPassword", query = "SELECT i FROM Interviewer i WHERE i.password = :password"),
    @NamedQuery(name = "Interviewer.findByBirthday", query = "SELECT i FROM Interviewer i WHERE i.birthday = :birthday"),
    @NamedQuery(name = "Interviewer.findByEmail", query = "SELECT i FROM Interviewer i WHERE i.email = :email"),
    @NamedQuery(name = "Interviewer.findBySex", query = "SELECT i FROM Interviewer i WHERE i.sex = :sex"),
    @NamedQuery(name = "Interviewer.findByAddress", query = "SELECT i FROM Interviewer i WHERE i.address = :address"),
    @NamedQuery(name = "Interviewer.findByPhone", query = "SELECT i FROM Interviewer i WHERE i.phone = :phone")})
public class Interviewer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "birthday")
    private String birthday;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "sex")
    private String sex;
    @Basic(optional = false)
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @OneToMany(mappedBy = "idInterviewer")
    private List<Interview> interviewList;
    @JoinColumn(name = "id_department", referencedColumnName = "id")
    @ManyToOne
    private Department idDepartment;

    public Interviewer() {
    }

    public Interviewer(String id) {
        this.id = id;
    }

    public Interviewer(String id, String name, String username, String password, String birthday, String email, String sex, String address) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.email = email;
        this.sex = sex;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @XmlTransient
    public List<Interview> getInterviewList() {
        return interviewList;
    }

    public void setInterviewList(List<Interview> interviewList) {
        this.interviewList = interviewList;
    }

    public Department getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Department idDepartment) {
        this.idDepartment = idDepartment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Interviewer)) {
            return false;
        }
        Interviewer other = (Interviewer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hrgroup.db.entities.Interviewer[ id=" + id + " ]";
    }
    
}
