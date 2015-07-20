/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.db.controller;

import hrgroup.db.controller.exceptions.NonexistentEntityException;
import hrgroup.db.controller.exceptions.PreexistingEntityException;
import hrgroup.db.entities.Applicant;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import hrgroup.db.entities.InterviewResult;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author tuananh
 */
public class ApplicantJpaController implements Serializable {

    public ApplicantJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Applicant applicant) throws PreexistingEntityException, Exception {
        if (applicant.getInterviewResultList() == null) {
            applicant.setInterviewResultList(new ArrayList<InterviewResult>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<InterviewResult> attachedInterviewResultList = new ArrayList<InterviewResult>();
            for (InterviewResult interviewResultListInterviewResultToAttach : applicant.getInterviewResultList()) {
                interviewResultListInterviewResultToAttach = em.getReference(interviewResultListInterviewResultToAttach.getClass(), interviewResultListInterviewResultToAttach.getId());
                attachedInterviewResultList.add(interviewResultListInterviewResultToAttach);
            }
            applicant.setInterviewResultList(attachedInterviewResultList);
            em.persist(applicant);
            for (InterviewResult interviewResultListInterviewResult : applicant.getInterviewResultList()) {
                Applicant oldIdApplicantOfInterviewResultListInterviewResult = interviewResultListInterviewResult.getIdApplicant();
                interviewResultListInterviewResult.setIdApplicant(applicant);
                interviewResultListInterviewResult = em.merge(interviewResultListInterviewResult);
                if (oldIdApplicantOfInterviewResultListInterviewResult != null) {
                    oldIdApplicantOfInterviewResultListInterviewResult.getInterviewResultList().remove(interviewResultListInterviewResult);
                    oldIdApplicantOfInterviewResultListInterviewResult = em.merge(oldIdApplicantOfInterviewResultListInterviewResult);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findApplicant(applicant.getId()) != null) {
                throw new PreexistingEntityException("Applicant " + applicant + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Applicant applicant) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Applicant persistentApplicant = em.find(Applicant.class, applicant.getId());
            List<InterviewResult> interviewResultListOld = persistentApplicant.getInterviewResultList();
            List<InterviewResult> interviewResultListNew = applicant.getInterviewResultList();
            List<InterviewResult> attachedInterviewResultListNew = new ArrayList<InterviewResult>();
            for (InterviewResult interviewResultListNewInterviewResultToAttach : interviewResultListNew) {
                interviewResultListNewInterviewResultToAttach = em.getReference(interviewResultListNewInterviewResultToAttach.getClass(), interviewResultListNewInterviewResultToAttach.getId());
                attachedInterviewResultListNew.add(interviewResultListNewInterviewResultToAttach);
            }
            interviewResultListNew = attachedInterviewResultListNew;
            applicant.setInterviewResultList(interviewResultListNew);
            applicant = em.merge(applicant);
            for (InterviewResult interviewResultListOldInterviewResult : interviewResultListOld) {
                if (!interviewResultListNew.contains(interviewResultListOldInterviewResult)) {
                    interviewResultListOldInterviewResult.setIdApplicant(null);
                    interviewResultListOldInterviewResult = em.merge(interviewResultListOldInterviewResult);
                }
            }
            for (InterviewResult interviewResultListNewInterviewResult : interviewResultListNew) {
                if (!interviewResultListOld.contains(interviewResultListNewInterviewResult)) {
                    Applicant oldIdApplicantOfInterviewResultListNewInterviewResult = interviewResultListNewInterviewResult.getIdApplicant();
                    interviewResultListNewInterviewResult.setIdApplicant(applicant);
                    interviewResultListNewInterviewResult = em.merge(interviewResultListNewInterviewResult);
                    if (oldIdApplicantOfInterviewResultListNewInterviewResult != null && !oldIdApplicantOfInterviewResultListNewInterviewResult.equals(applicant)) {
                        oldIdApplicantOfInterviewResultListNewInterviewResult.getInterviewResultList().remove(interviewResultListNewInterviewResult);
                        oldIdApplicantOfInterviewResultListNewInterviewResult = em.merge(oldIdApplicantOfInterviewResultListNewInterviewResult);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = applicant.getId();
                if (findApplicant(id) == null) {
                    throw new NonexistentEntityException("The applicant with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Applicant applicant;
            try {
                applicant = em.getReference(Applicant.class, id);
                applicant.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The applicant with id " + id + " no longer exists.", enfe);
            }
            List<InterviewResult> interviewResultList = applicant.getInterviewResultList();
            for (InterviewResult interviewResultListInterviewResult : interviewResultList) {
                interviewResultListInterviewResult.setIdApplicant(null);
                interviewResultListInterviewResult = em.merge(interviewResultListInterviewResult);
            }
            em.remove(applicant);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Applicant> findApplicantEntities() {
        return findApplicantEntities(true, -1, -1);
    }

    public List<Applicant> findApplicantEntities(int maxResults, int firstResult) {
        return findApplicantEntities(false, maxResults, firstResult);
    }

    private List<Applicant> findApplicantEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Applicant.class));
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

    public Applicant findApplicant(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Applicant.class, id);
        } finally {
            em.close();
        }
    }

    public int getApplicantCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Applicant> rt = cq.from(Applicant.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
