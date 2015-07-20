/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.db.controller;

import hrgroup.db.controller.exceptions.NonexistentEntityException;
import hrgroup.db.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import hrgroup.db.entities.Interview;
import hrgroup.db.entities.Applicant;
import hrgroup.db.entities.InterviewResult;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author tuananh
 */
public class InterviewResultJpaController implements Serializable {

    public InterviewResultJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InterviewResult interviewResult) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Interview idInterview = interviewResult.getIdInterview();
            if (idInterview != null) {
                idInterview = em.getReference(idInterview.getClass(), idInterview.getId());
                interviewResult.setIdInterview(idInterview);
            }
            Applicant idApplicant = interviewResult.getIdApplicant();
            if (idApplicant != null) {
                idApplicant = em.getReference(idApplicant.getClass(), idApplicant.getId());
                interviewResult.setIdApplicant(idApplicant);
            }
            em.persist(interviewResult);
            if (idInterview != null) {
                idInterview.getInterviewResultList().add(interviewResult);
                idInterview = em.merge(idInterview);
            }
            if (idApplicant != null) {
                idApplicant.getInterviewResultList().add(interviewResult);
                idApplicant = em.merge(idApplicant);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInterviewResult(interviewResult.getId()) != null) {
                throw new PreexistingEntityException("InterviewResult " + interviewResult + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InterviewResult interviewResult) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InterviewResult persistentInterviewResult = em.find(InterviewResult.class, interviewResult.getId());
            Interview idInterviewOld = persistentInterviewResult.getIdInterview();
            Interview idInterviewNew = interviewResult.getIdInterview();
            Applicant idApplicantOld = persistentInterviewResult.getIdApplicant();
            Applicant idApplicantNew = interviewResult.getIdApplicant();
            if (idInterviewNew != null) {
                idInterviewNew = em.getReference(idInterviewNew.getClass(), idInterviewNew.getId());
                interviewResult.setIdInterview(idInterviewNew);
            }
            if (idApplicantNew != null) {
                idApplicantNew = em.getReference(idApplicantNew.getClass(), idApplicantNew.getId());
                interviewResult.setIdApplicant(idApplicantNew);
            }
            interviewResult = em.merge(interviewResult);
            if (idInterviewOld != null && !idInterviewOld.equals(idInterviewNew)) {
                idInterviewOld.getInterviewResultList().remove(interviewResult);
                idInterviewOld = em.merge(idInterviewOld);
            }
            if (idInterviewNew != null && !idInterviewNew.equals(idInterviewOld)) {
                idInterviewNew.getInterviewResultList().add(interviewResult);
                idInterviewNew = em.merge(idInterviewNew);
            }
            if (idApplicantOld != null && !idApplicantOld.equals(idApplicantNew)) {
                idApplicantOld.getInterviewResultList().remove(interviewResult);
                idApplicantOld = em.merge(idApplicantOld);
            }
            if (idApplicantNew != null && !idApplicantNew.equals(idApplicantOld)) {
                idApplicantNew.getInterviewResultList().add(interviewResult);
                idApplicantNew = em.merge(idApplicantNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = interviewResult.getId();
                if (findInterviewResult(id) == null) {
                    throw new NonexistentEntityException("The interviewResult with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InterviewResult interviewResult;
            try {
                interviewResult = em.getReference(InterviewResult.class, id);
                interviewResult.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The interviewResult with id " + id + " no longer exists.", enfe);
            }
            Interview idInterview = interviewResult.getIdInterview();
            if (idInterview != null) {
                idInterview.getInterviewResultList().remove(interviewResult);
                idInterview = em.merge(idInterview);
            }
            Applicant idApplicant = interviewResult.getIdApplicant();
            if (idApplicant != null) {
                idApplicant.getInterviewResultList().remove(interviewResult);
                idApplicant = em.merge(idApplicant);
            }
            em.remove(interviewResult);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InterviewResult> findInterviewResultEntities() {
        return findInterviewResultEntities(true, -1, -1);
    }

    public List<InterviewResult> findInterviewResultEntities(int maxResults, int firstResult) {
        return findInterviewResultEntities(false, maxResults, firstResult);
    }

    private List<InterviewResult> findInterviewResultEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InterviewResult.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public InterviewResult findInterviewResult(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InterviewResult.class, id);
        } finally {
            em.close();
        }
    }

    public int getInterviewResultCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InterviewResult> rt = cq.from(InterviewResult.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
