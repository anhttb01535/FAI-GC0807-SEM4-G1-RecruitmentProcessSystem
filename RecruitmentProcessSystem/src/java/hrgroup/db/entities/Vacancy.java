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
@Table(name = "Vacancy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vacancy.findAll", query = "SELECT v FROM Vacancy v"),
    @NamedQuery(name = "Vacancy.findById", query = "SELECT v FROM Vacancy v WHERE v.id = :id"),
    @NamedQuery(name = "Vacancy.findByTitle", query = "SELECT v FROM Vacancy v WHERE v.title = :title"),
    @NamedQuery(name = "Vacancy.findByDateOfCreation", query = "SELECT v FROM Vacancy v WHERE v.dateOfCreation = :dateOfCreation"),
    @NamedQuery(name = "Vacancy.findByNumberOfApplicant", query = "SELECT v FROM Vacancy v WHERE v.numberOfApplicant = :numberOfApplicant"),
    @NamedQuery(name = "Vacancy.findByStatus", query = "SELECT v FROM Vacancy v WHERE v.status = :status")})
public class Vacancy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "date_of_creation")
    private String dateOfCreation;
    @Basic(optional = false)
    @Column(name = "number_of_applicant")
    private int numberOfApplicant;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "id_department", referencedColumnName = "id")
    @ManyToOne
    private Department idDepartment;
    @OneToMany(mappedBy = "idVacancy")
    private List<Interview> interviewList;

    public Vacancy() {
    }

    public Vacancy(String id) {
        this.id = id;
    }

    public Vacancy(String id, String title, String dateOfCreation, int numberOfApplicant, String status) {
        this.id = id;
        this.title = title;
        this.dateOfCreation = dateOfCreation;
        this.numberOfApplicant = numberOfApplicant;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public int getNumberOfApplicant() {
        return numberOfApplicant;
    }

    public void setNumberOfApplicant(int numberOfApplicant) {
        this.numberOfApplicant = numberOfApplicant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Department getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Department idDepartment) {
        this.idDepartment = idDepartment;
    }

    @XmlTransient
    public List<Interview> getInterviewList() {
        return interviewList;
    }

    public void setInterviewList(List<Interview> interviewList) {
        this.interviewList = interviewList;
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
        if (!(object instanceof Vacancy)) {
            return false;
        }
        Vacancy other = (Vacancy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hrgroup.db.entities.Vacancy[ id=" + id + " ]";
    }
    
}
