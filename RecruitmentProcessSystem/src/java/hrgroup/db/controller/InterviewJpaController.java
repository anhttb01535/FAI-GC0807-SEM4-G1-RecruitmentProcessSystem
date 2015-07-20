/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrgroup.db.controller;

import hrgroup.db.controller.exceptions.NonexistentEntityException;
import hrgroup.db.controller.exceptions.PreexistingEntityException;
import hrgroup.db.entities.Interview;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import hrgroup.db.entities.Vacancy;
import hrgroup.db.entities.Interviewer;
import hrgroup.db.entities.InterviewResult;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author tuananh
 */
public class InterviewJpaController implements Serializable {

    public InterviewJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Interview interview) throws PreexistingEntityException, Exception {
        if (interview.getInterviewResultList() == null) {
            interview.setInterviewResultList(new ArrayList<InterviewResult>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vacancy idVacancy = interview.getIdVacancy();
            if (idVacancy != null) {
                idVacancy = em.getReference(idVacancy.getClass(), idVacancy.getId());
                interview.setIdVacancy(idVacancy);
            }
            Interviewer idInterviewer = interview.getIdInterviewer();
            if (idInterviewer != null) {
                idInterviewer = em.getReference(idInterviewer.getClass(), idInterviewer.getId());
                interview.setIdInterviewer(idInterviewer);
            }
            List<InterviewResult> attachedInterviewResultList = new ArrayList<InterviewResult>();
            for (InterviewResult interviewResultListInterviewResultToAttach : interview.getInterviewResultList()) {
                interviewResultListInterviewResultToAttach = em.getReference(interviewResultListInterviewResultToAttach.getClass(), interviewResultListInterviewResultToAttach.getId());
                attachedInterviewResultList.add(interviewResultListInterviewResultToAttach);
            }
            interview.setInterviewResultList(attachedInterviewResultList);
            em.persist(interview);
            if (idVacancy != null) {
                idVacancy.getInterviewList().add(interview);
                idVacancy = em.merge(idVacancy);
            }
            if (idInterviewer != null) {
                idInterviewer.getInterviewList().add(interview);
                idInterviewer = em.merge(idInterviewer);
            }
            for (InterviewResult interviewResultListInterviewResult : interview.getInterviewResultList()) {
                Interview oldIdInterviewOfInterviewResultListInterviewResult = interviewResultListInterviewResult.getIdInterview();
                interviewResultListInterviewResult.setIdInterview(interview);
                interviewResultListInterviewResult = em.merge(interviewResultListInterviewResult);
                if (oldIdInterviewOfInterviewResultListInterviewResult != null) {
                    oldIdInterviewOfInterviewResultListInterviewResult.getInterviewResultList().remove(interviewResultListInterviewResult);
                    oldIdInterviewOfInterviewResultListInterviewResult = em.merge(oldIdInterviewOfInterviewResultListInterviewResult);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInterview(interview.getId()) != null) {
                throw new PreexistingEntityException("Interview " + interview + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Interview interview) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Interview persistentInterview = em.find(Interview.class, interview.getId());
            Vacancy idVacancyOld = persistentInterview.getIdVacancy();
            Vacancy idVacancyNew = interview.getIdVacancy();
            Interviewer idInterviewerOld = persistentInterview.getIdInterviewer();
            Interviewer idInterviewerNew = interview.getIdInterviewer();
            List<InterviewResult> interviewResultListOld = persistentInterview.getInterviewResultList();
            List<InterviewResult> interviewResultListNew = interview.getInterviewResultList();
            if (idVacancyNew != null) {
                idVacancyNew = em.getReference(idVacancyNew.getClass(), idVacancyNew.getId());
                interview.setIdVacancy(idVacancyNew);
            }
            if (idInterviewerNew != null) {
                idInterviewerNew = em.getReference(idInterviewerNew.getClass(), idInterviewerNew.getId());
                interview.setIdInterviewer(idInterviewerNew);
            }
            List<InterviewResult> attachedInterviewResultListNew = new ArrayList<InterviewResult>();
            for (InterviewResult interviewResultListNewInterviewResultToAttach : interviewResultListNew) {
                interviewResultListNewInterviewResultToAttach = em.getReference(interviewResultListNewInterviewResultToAttach.getClass(), interviewResultListNewInterviewResultToAttach.getId());
                attachedInterviewResultListNew.add(interviewResultListNewInterviewResultToAttach);
            }
            interviewResultListNew = attachedInterviewResultListNew;
            interview.setInterviewResultList(interviewResultListNew);
            interview = em.merge(interview);
            if (idVacancyOld != null && !idVacancyOld.equals(idVacancyNew)) {
                idVacancyOld.getInterviewList().remove(interview);
                idVacancyOld = em.merge(idVacancyOld);
            }
            if (idVacancyNew != null && !idVacancyNew.equals(idVacancyOld)) {
                idVacancyNew.getInterviewList().add(interview);
                idVacancyNew = em.merge(idVacancyNew);
            }
            if (idInterviewerOld != null && !idInterviewerOld.equals(idInterviewerNew)) {
                idInterviewerOld.getInterviewList().remove(interview);
                idInterviewerOld = em.merge(idInterviewerOld);
            }
            if (idInterviewerNew != null && !idInterviewerNew.equals(idInterviewerOld)) {
                idInterviewerNew.getInterviewList().add(interview);
                idInterviewerNew = em.merge(idInterviewerNew);
            }
            for (InterviewResult interviewResultListOldInterviewResult : interviewResultListOld) {
                if (!interviewResultListNew.contains(interviewResultListOldInterviewResult)) {
                    interviewResultListOldInterviewResult.setIdInterview(null);
                    interviewResultListOldInterviewResult = em.merge(interviewResultListOldInterviewResult);
                }
            }
            for (InterviewResult interviewResultListNewInterviewResult : interviewResultListNew) {
                if (!interviewResultListOld.contains(interviewResultListNewInterviewResult)) {
                    Interview oldIdInterviewOfInterviewResultListNewInterviewResult = interviewResultListNewInterviewResult.getIdInterview();
                    interviewResultListNewInterviewResult.setIdInterview(interview);
                    interviewResultListNewInterviewResult = em.merge(interviewResultListNewInterviewResult);
                    if (oldIdInterviewOfInterviewResultListNewInterviewResult != null && !oldIdInterviewOfInterviewResultListNewInterviewResult.equals(interview)) {
                        oldIdInterviewOfInterviewResultListNewInterviewResult.getInterviewResultList().remove(interviewResultListNewInterviewResult);
                        oldIdInterviewOfInterviewResultListNewInterviewResult = em.merge(oldIdInterviewOfInterviewResultListNewInterviewResult);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = interview.getId();
                if (findInterview(id) == null) {
                    throw new NonexistentEntityException("The interview with id " + id + " no longer exists.");
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
            Interview interview;
            try {
                interview = em.getReference(Interview.class, id);
                interview.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The interview with id " + id + " no longer exists.", enfe);
            }
            Vacancy idVacancy = interview.getIdVacancy();
            if (idVacancy != null) {
                idVacancy.getInterviewList().remove(interview);
                idVacancy = em.merge(idVacancy);
            }
            Interviewer idInterviewer = interview.getIdInterviewer();
            if (idInterviewer != null) {
                idInterviewer.getInterviewList().remove(interview);
                idInterviewer = em.merge(idInterviewer);
            }
            List<InterviewResult> interviewResultList = interview.getInterviewResultList();
            for (InterviewResult interviewResultListInterviewResult : interviewResultList) {
                interviewResultListInterviewResult.setIdInterview(null);
                interviewResultListInterviewResult = em.merge(interviewResultListInterviewResult);
            }
            em.remove(interview);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Interview> findInterviewEntities() {
        return findInterviewEntities(true, -1, -1);
    }

    public List<Interview> findInterviewEntities(int maxResults, int firstResult) {
        return findInterviewEntities(false, maxResults, firstResult);
    }

    private List<Interview> findInterviewEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Interview.class));
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

    public Interview findInterview(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Interview.class, id);
        } finally {
            em.close();
        }
    }

    public int getInterviewCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Interview> rt = cq.from(Interview.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
