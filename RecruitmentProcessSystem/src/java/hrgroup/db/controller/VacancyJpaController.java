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
import hrgroup.db.entities.Vacancy;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author tuananh
 */
public class VacancyJpaController implements Serializable {

    public VacancyJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vacancy vacancy) throws PreexistingEntityException, Exception {
        if (vacancy.getInterviewList() == null) {
            vacancy.setInterviewList(new ArrayList<Interview>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Department idDepartment = vacancy.getIdDepartment();
            if (idDepartment != null) {
                idDepartment = em.getReference(idDepartment.getClass(), idDepartment.getId());
                vacancy.setIdDepartment(idDepartment);
            }
            List<Interview> attachedInterviewList = new ArrayList<Interview>();
            for (Interview interviewListInterviewToAttach : vacancy.getInterviewList()) {
                interviewListInterviewToAttach = em.getReference(interviewListInterviewToAttach.getClass(), interviewListInterviewToAttach.getId());
                attachedInterviewList.add(interviewListInterviewToAttach);
            }
            vacancy.setInterviewList(attachedInterviewList);
            em.persist(vacancy);
            if (idDepartment != null) {
                idDepartment.getVacancyList().add(vacancy);
                idDepartment = em.merge(idDepartment);
            }
            for (Interview interviewListInterview : vacancy.getInterviewList()) {
                Vacancy oldIdVacancyOfInterviewListInterview = interviewListInterview.getIdVacancy();
                interviewListInterview.setIdVacancy(vacancy);
                interviewListInterview = em.merge(interviewListInterview);
                if (oldIdVacancyOfInterviewListInterview != null) {
                    oldIdVacancyOfInterviewListInterview.getInterviewList().remove(interviewListInterview);
                    oldIdVacancyOfInterviewListInterview = em.merge(oldIdVacancyOfInterviewListInterview);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findVacancy(vacancy.getId()) != null) {
                throw new PreexistingEntityException("Vacancy " + vacancy + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vacancy vacancy) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vacancy persistentVacancy = em.find(Vacancy.class, vacancy.getId());
            Department idDepartmentOld = persistentVacancy.getIdDepartment();
            Department idDepartmentNew = vacancy.getIdDepartment();
            List<Interview> interviewListOld = persistentVacancy.getInterviewList();
            List<Interview> interviewListNew = vacancy.getInterviewList();
            if (idDepartmentNew != null) {
                idDepartmentNew = em.getReference(idDepartmentNew.getClass(), idDepartmentNew.getId());
                vacancy.setIdDepartment(idDepartmentNew);
            }
            List<Interview> attachedInterviewListNew = new ArrayList<Interview>();
            for (Interview interviewListNewInterviewToAttach : interviewListNew) {
                interviewListNewInterviewToAttach = em.getReference(interviewListNewInterviewToAttach.getClass(), interviewListNewInterviewToAttach.getId());
                attachedInterviewListNew.add(interviewListNewInterviewToAttach);
            }
            interviewListNew = attachedInterviewListNew;
            vacancy.setInterviewList(interviewListNew);
            vacancy = em.merge(vacancy);
            if (idDepartmentOld != null && !idDepartmentOld.equals(idDepartmentNew)) {
                idDepartmentOld.getVacancyList().remove(vacancy);
                idDepartmentOld = em.merge(idDepartmentOld);
            }
            if (idDepartmentNew != null && !idDepartmentNew.equals(idDepartmentOld)) {
                idDepartmentNew.getVacancyList().add(vacancy);
                idDepartmentNew = em.merge(idDepartmentNew);
            }
            for (Interview interviewListOldInterview : interviewListOld) {
                if (!interviewListNew.contains(interviewListOldInterview)) {
                    interviewListOldInterview.setIdVacancy(null);
                    interviewListOldInterview = em.merge(interviewListOldInterview);
                }
            }
            for (Interview interviewListNewInterview : interviewListNew) {
                if (!interviewListOld.contains(interviewListNewInterview)) {
                    Vacancy oldIdVacancyOfInterviewListNewInterview = interviewListNewInterview.getIdVacancy();
                    interviewListNewInterview.setIdVacancy(vacancy);
                    interviewListNewInterview = em.merge(interviewListNewInterview);
                    if (oldIdVacancyOfInterviewListNewInterview != null && !oldIdVacancyOfInterviewListNewInterview.equals(vacancy)) {
                        oldIdVacancyOfInterviewListNewInterview.getInterviewList().remove(interviewListNewInterview);
                        oldIdVacancyOfInterviewListNewInterview = em.merge(oldIdVacancyOfInterviewListNewInterview);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = vacancy.getId();
                if (findVacancy(id) == null) {
                    throw new NonexistentEntityException("The vacancy with id " + id + " no longer exists.");
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
            Vacancy vacancy;
            try {
                vacancy = em.getReference(Vacancy.class, id);
                vacancy.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vacancy with id " + id + " no longer exists.", enfe);
            }
            Department idDepartment = vacancy.getIdDepartment();
            if (idDepartment != null) {
                idDepartment.getVacancyList().remove(vacancy);
                idDepartment = em.merge(idDepartment);
            }
            List<Interview> interviewList = vacancy.getInterviewList();
            for (Interview interviewListInterview : interviewList) {
                interviewListInterview.setIdVacancy(null);
                interviewListInterview = em.merge(interviewListInterview);
            }
            em.remove(vacancy);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vacancy> findVacancyEntities() {
        return findVacancyEntities(true, -1, -1);
    }

    public List<Vacancy> findVacancyEntities(int maxResults, int firstResult) {
        return findVacancyEntities(false, maxResults, firstResult);
    }

    private List<Vacancy> findVacancyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vacancy.class));
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

    public Vacancy findVacancy(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vacancy.class, id);
        } finally {
            em.close();
        }
    }

    public int getVacancyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vacancy> rt = cq.from(Vacancy.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
