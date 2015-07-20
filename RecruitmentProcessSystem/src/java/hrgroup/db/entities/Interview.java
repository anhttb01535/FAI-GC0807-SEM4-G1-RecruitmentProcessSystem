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
@Table(name = "Interview")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Interview.findAll", query = "SELECT i FROM Interview i"),
    @NamedQuery(name = "Interview.findById", query = "SELECT i FROM Interview i WHERE i.id = :id"),
    @NamedQuery(name = "Interview.findByDateOfStartInterview", query = "SELECT i FROM Interview i WHERE i.dateOfStartInterview = :dateOfStartInterview"),
    @NamedQuery(name = "Interview.findByDateOfEndInterview", query = "SELECT i FROM Interview i WHERE i.dateOfEndInterview = :dateOfEndInterview")})
public class Interview implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "date_of_start_interview")
    private String dateOfStartInterview;
    @Basic(optional = false)
    @Column(name = "date_of_end_interview")
    private String dateOfEndInterview;
    @JoinColumn(name = "id_vacancy", referencedColumnName = "id")
    @ManyToOne
    private Vacancy idVacancy;
    @JoinColumn(name = "id_interviewer", referencedColumnName = "id")
    @ManyToOne
    private Interviewer idInterviewer;
    @OneToMany(mappedBy = "idInterview")
    private List<InterviewResult> interviewResultList;

    public Interview() {
    }

    public Interview(String id) {
        this.id = id;
    }

    public Interview(String id, String dateOfStartInterview, String dateOfEndInterview) {
        this.id = id;
        this.dateOfStartInterview = dateOfStartInterview;
        this.dateOfEndInterview = dateOfEndInterview;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateOfStartInterview() {
        return dateOfStartInterview;
    }

    public void setDateOfStartInterview(String dateOfStartInterview) {
        this.dateOfStartInterview = dateOfStartInterview;
    }

    public String getDateOfEndInterview() {
        return dateOfEndInterview;
    }

    public void setDateOfEndInterview(String dateOfEndInterview) {
        this.dateOfEndInterview = dateOfEndInterview;
    }

    public Vacancy getIdVacancy() {
        return idVacancy;
    }

    public void setIdVacancy(Vacancy idVacancy) {
        this.idVacancy = idVacancy;
    }

    public Interviewer getIdInterviewer() {
        return idInterviewer;
    }

    public void setIdInterviewer(Interviewer idInterviewer) {
        this.idInterviewer = idInterviewer;
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
        if (!(object instanceof Interview)) {
            return false;
        }
        Interview other = (Interview) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hrgroup.db.entities.Interview[ id=" + id + " ]";
    }
    
}
