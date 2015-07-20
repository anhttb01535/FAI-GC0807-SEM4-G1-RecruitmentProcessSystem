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
import hrgroup.db.entities.Department;
import hrgroup.db.entities.Interview;
import hrgroup.db.entities.Interviewer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author tuananh
 */
public class InterviewerJpaController implements Serializable {

    public InterviewerJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Interviewer interviewer) throws PreexistingEntityException, Exception {
        if (interviewer.getInterviewList() == null) {
            interviewer.setInterviewList(new ArrayList<Interview>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Department idDepartment = interviewer.getIdDepartment();
            if (idDepartment != null) {
                idDepartment = em.getReference(idDepartment.getClass(), idDepartment.getId());
                interviewer.setIdDepartment(idDepartment);
            }
            List<Interview> attachedInterviewList = new ArrayList<Interview>();
            for (Interview interviewListInterviewToAttach : interviewer.getInterviewList()) {
                interviewListInterviewToAttach = em.getReference(interviewListInterviewToAttach.getClass(), interviewListInterviewToAttach.getId());
                attachedInterviewList.add(interviewListInterviewToAttach);
            }
            interviewer.setInterviewList(attachedInterviewList);
            em.persist(interviewer);
            if (idDepartment != null) {
                idDepartment.getInterviewerList().add(interviewer);
                idDepartment = em.merge(idDepartment);
            }
            for (Interview interviewListInterview : interviewer.getInterviewList()) {
                Interviewer oldIdInterviewerOfInterviewListInterview = interviewListInterview.getIdInterviewer();
                interviewListInterview.setIdInterviewer(interviewer);
                interviewListInterview = em.merge(interviewListInterview);
                if (oldIdInterviewerOfInterviewListInterview != null) {
                    oldIdInterviewerOfInterviewListInterview.getInterviewList().remove(interviewListInterview);
                    oldIdInterviewerOfInterviewListInterview = em.merge(oldIdInterviewerOfInterviewListInterview);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInterviewer(interviewer.getId()) != null) {
                throw new PreexistingEntityException("Interviewer " + interviewer + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Interviewer interviewer) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Interviewer persistentInterviewer = em.find(Interviewer.class, interviewer.getId());
            Department idDepartmentOld = persistentInterviewer.getIdDepartment();
            Department idDepartmentNew = interviewer.getIdDepartment();
            List<Interview> interviewListOld = persistentInterviewer.getInterviewList();
            List<Interview> interviewListNew = interviewer.getInterviewList();
            if (idDepartmentNew != null) {
                idDepartmentNew = em.getReference(idDepartmentNew.getClass(), idDepartmentNew.getId());
                interviewer.setIdDepartment(idDepartmentNew);
            }
            List<Interview> attachedInterviewListNew = new ArrayList<Interview>();
            for (Interview interviewListNewInterviewToAttach : interviewListNew) {
                interviewListNewInterviewToAttach = em.getReference(interviewListNewInterviewToAttach.getClass(), interviewListNewInterviewToAttach.getId());
                attachedInterviewListNew.add(interviewListNewInterviewToAttach);
            }
            interviewListNew = attachedInterviewListNew;
            interviewer.setInterviewList(interviewListNew);
            interviewer = em.merge(interviewer);
            if (idDepartmentOld != null && !idDepartmentOld.equals(idDepartmentNew)) {
                idDepartmentOld.getInterviewerList().remove(interviewer);
                idDepartmentOld = em.merge(idDepartmentOld);
            }
            if (idDepartmentNew != null && !idDepartmentNew.equals(idDepartmentOld)) {
                idDepartmentNew.getInterviewerList().add(interviewer);
                idDepartmentNew = em.merge(idDepartmentNew);
            }
            for (Interview interviewListOldInterview : interviewListOld) {
                if (!interviewListNew.contains(interviewListOldInterview)) {
                    interviewListOldInterview.setIdInterviewer(null);
                    interviewListOldInterview = em.merge(interviewListOldInterview);
                }
            }
            for (Interview interviewListNewInterview : interviewListNew) {
                if (!interviewListOld.contains(interviewListNewInterview)) {
                    Interviewer oldIdInterviewerOfInterviewListNewInterview = interviewListNewInterview.getIdInterviewer();
                    interviewListNewInterview.setIdInterviewer(interviewer);
                    interviewListNewInterview = em.merge(interviewListNewInterview);
                    if (oldIdInterviewerOfInterviewListNewInterview != null && !oldIdInterviewerOfInterviewListNewInterview.equals(interviewer)) {
                        oldIdInterviewerOfInterviewListNewInterview.getInterviewList().remove(interviewListNewInterview);
                        oldIdInterviewerOfInterviewListNewInterview = em.merge(oldIdInterviewerOfInterviewListNewInterview);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = interviewer.getId();
                if (findInterviewer(id) == null) {
                    throw new NonexistentEntityException("The interviewer with id " + id + " no longer exists.");
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
            Interviewer interviewer;
            try {
                interviewer = em.getReference(Interviewer.class, id);
                interviewer.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The interviewer with id " + id + " no longer exists.", enfe);
            }
            Department idDepartment = interviewer.getIdDepartment();
            if (idDepartment != null) {
                idDepartment.getInterviewerList().remove(interviewer);
                idDepartment = em.merge(idDepartment);
            }
            List<Interview> interviewList = interviewer.getInterviewList();
            for (Interview interviewListInterview : interviewList) {
                interviewListInterview.setIdInterviewer(null);
                interviewListInterview = em.merge(interviewListInterview);
            }
            em.remove(interviewer);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Interviewer> findInterviewerEntities() {
        return findInterviewerEntities(true, -1, -1);
    }

    public List<Interviewer> findInterviewerEntities(int maxResults, int firstResult) {
        return findInterviewerEntities(false, maxResults, firstResult);
    }

    private List<Interviewer> findInterviewerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Interviewer.class));
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

    public Interviewer findInterviewer(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Interviewer.class, id);
        } finally {
            em.close();
        }
    }

    public int getInterviewerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Interviewer> rt = cq.from(Interviewer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
