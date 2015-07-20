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
@Table(name = "Applicant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Applicant.findAll", query = "SELECT a FROM Applicant a"),
    @NamedQuery(name = "Applicant.findById", query = "SELECT a FROM Applicant a WHERE a.id = :id"),
    @NamedQuery(name = "Applicant.findByName", query = "SELECT a FROM Applicant a WHERE a.name = :name"),
    @NamedQuery(name = "Applicant.findByBrithday", query = "SELECT a FROM Applicant a WHERE a.brithday = :brithday"),
    @NamedQuery(name = "Applicant.findByEmail", query = "SELECT a FROM Applicant a WHERE a.email = :email"),
    @NamedQuery(name = "Applicant.findBySex", query = "SELECT a FROM Applicant a WHERE a.sex = :sex"),
    @NamedQuery(name = "Applicant.findByAddress", query = "SELECT a FROM Applicant a WHERE a.address = :address"),
    @NamedQuery(name = "Applicant.findByPhone", query = "SELECT a FROM Applicant a WHERE a.phone = :phone"),
    @NamedQuery(name = "Applicant.findByHighestDegree", query = "SELECT a FROM Applicant a WHERE a.highestDegree = :highestDegree"),
    @NamedQuery(name = "Applicant.findByUniversity", query = "SELECT a FROM Applicant a WHERE a.university = :university"),
    @NamedQuery(name = "Applicant.findByDateOfCreation", query = "SELECT a FROM Applicant a WHERE a.dateOfCreation = :dateOfCreation"),
    @NamedQuery(name = "Applicant.findByDateOfPassing", query = "SELECT a FROM Applicant a WHERE a.dateOfPassing = :dateOfPassing"),
    @NamedQuery(name = "Applicant.findBySkill", query = "SELECT a FROM Applicant a WHERE a.skill = :skill"),
    @NamedQuery(name = "Applicant.findByUsername", query = "SELECT a FROM Applicant a WHERE a.username = :username"),
    @NamedQuery(name = "Applicant.findByPassword", query = "SELECT a FROM Applicant a WHERE a.password = :password"),
    @NamedQuery(name = "Applicant.findByStatus", query = "SELECT a FROM Applicant a WHERE a.status = :status")})
public class Applicant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "brithday")
    private String brithday;
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
    @Column(name = "highest_degree")
    private String highestDegree;
    @Column(name = "university")
    private String university;
    @Basic(optional = false)
    @Column(name = "date_of_creation")
    private String dateOfCreation;
    @Column(name = "date_of_passing")
    private String dateOfPassing;
    @Column(name = "skill")
    private String skill;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @OneToMany(mappedBy = "idApplicant")
    private List<InterviewResult> interviewResultList;

    public Applicant() {
    }

    public Applicant(String id) {
        this.id = id;
    }

    public Applicant(String id, String name, String brithday, String email, String sex, String address, String dateOfCreation, String username, String password, String status) {
        this.id = id;
        this.name = name;
        this.brithday = brithday;
        this.email = email;
        this.sex = sex;
        this.address = address;
        this.dateOfCreation = dateOfCreation;
        this.username = username;
        this.password = password;
        this.status = status;
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

    public String getBrithday() {
        return brithday;
    }

    public void setBrithday(String brithday) {
        this.brithday = brithday;
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

    public String getHighestDegree() {
        return highestDegree;
    }

    public void setHighestDegree(String highestDegree) {
        this.highestDegree = highestDegree;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getDateOfPassing() {
        return dateOfPassing;
    }

    public void setDateOfPassing(String dateOfPassing) {
        this.dateOfPassing = dateOfPassing;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public List<InterviewResult> getInterviewResultList() {
        return interviewResultList;
    }

    public void setInterviewResultList(List<InterviewResult> interviewResultList) {
        this.interviewResultList = interviewResultList;
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
        if (!(object instanceof Applicant)) {
            return false;
        }
        Applicant other = (Applicant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hrgroup.db.entities.Applicant[ id=" + id + " ]";
    }
    
}
