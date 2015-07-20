/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.db.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tuananh
 */
@Entity
@Table(name = "InterviewResult")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InterviewResult.findAll", query = "SELECT i FROM InterviewResult i"),
    @NamedQuery(name = "InterviewResult.findById", query = "SELECT i FROM InterviewResult i WHERE i.id = :id"),
    @NamedQuery(name = "InterviewResult.findByStatus", query = "SELECT i FROM InterviewResult i WHERE i.status = :status")})
public class InterviewResult implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "id_interview", referencedColumnName = "id")
    @ManyToOne
    private Interview idInterview;
    @JoinColumn(name = "id_applicant", referencedColumnName = "id")
    @ManyToOne
    private Applicant idApplicant;

    public InterviewResult() {
    }

    public InterviewResult(Integer id) {
        this.id = id;
    }

    public InterviewResult(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Interview getIdInterview() {
        return idInterview;
    }

    public void setIdInterview(Interview idInterview) {
        this.idInterview = idInterview;
    }

    public Applicant getIdApplicant() {
        return idApplicant;
    }

    public void setIdApplicant(Applicant idApplicant) {
        this.idApplicant = idApplicant;
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
        if (!(object instanceof InterviewResult)) {
            return false;
        }
        InterviewResult other = (InterviewResult) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hrgroup.db.entities.InterviewResult[ id=" + id + " ]";
    }
    
}
