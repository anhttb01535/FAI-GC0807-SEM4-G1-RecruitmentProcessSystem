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
import hrgroup.db.entities.Vacancy;
import java.util.ArrayList;
import java.util.List;
import hrgroup.db.entities.Administrator;
import hrgroup.db.entities.Department;
import hrgroup.db.entities.Interviewer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author tuananh
 */
public class DepartmentJpaController implements Serializable {

    public DepartmentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Department department) throws PreexistingEntityException, Exception {
        if (department.getVacancyList() == null) {
            department.setVacancyList(new ArrayList<Vacancy>());
        }
        if (department.getAdministratorList() == null) {
            department.setAdministratorList(new ArrayList<Administrator>());
        }
        if (department.getInterviewerList() == null) {
            department.setInterviewerList(new ArrayList<Interviewer>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Vacancy> attachedVacancyList = new ArrayList<Vacancy>();
            for (Vacancy vacancyListVacancyToAttach : department.getVacancyList()) {
                vacancyListVacancyToAttach = em.getReference(vacancyListVacancyToAttach.getClass(), vacancyListVacancyToAttach.getId());
                attachedVacancyList.add(vacancyListVacancyToAttach);
            }
            department.setVacancyList(attachedVacancyList);
            List<Administrator> attachedAdministratorList = new ArrayList<Administrator>();
            for (Administrator administratorListAdministratorToAttach : department.getAdministratorList()) {
                administratorListAdministratorToAttach = em.getReference(administratorListAdministratorToAttach.getClass(), administratorListAdministratorToAttach.getId());
                attachedAdministratorList.add(administratorListAdministratorToAttach);
            }
            department.setAdministratorList(attachedAdministratorList);
            List<Interviewer> attachedInterviewerList = new ArrayList<Interviewer>();
            for (Interviewer interviewerListInterviewerToAttach : department.getInterviewerList()) {
                interviewerListInterviewerToAttach = em.getReference(interviewerListInterviewerToAttach.getClass(), interviewerListInterviewerToAttach.getId());
                attachedInterviewerList.add(interviewerListInterviewerToAttach);
            }
            department.setInterviewerList(attachedInterviewerList);
            em.persist(department);
            for (Vacancy vacancyListVacancy : department.getVacancyList()) {
                Department oldIdDepartmentOfVacancyListVacancy = vacancyListVacancy.getIdDepartment();
                vacancyListVacancy.setIdDepartment(department);
                vacancyListVacancy = em.merge(vacancyListVacancy);
                if (oldIdDepartmentOfVacancyListVacancy != null) {
                    oldIdDepartmentOfVacancyListVacancy.getVacancyList().remove(vacancyListVacancy);
                    oldIdDepartmentOfVacancyListVacancy = em.merge(oldIdDepartmentOfVacancyListVacancy);
                }
            }
            for (Administrator administratorListAdministrator : department.getAdministratorList()) {
                Department oldIdDepartmentOfAdministratorListAdministrator = administratorListAdministrator.getIdDepartment();
                administratorListAdministrator.setIdDepartment(department);
                administratorListAdministrator = em.merge(administratorListAdministrator);
                if (oldIdDepartmentOfAdministratorListAdministrator != null) {
                    oldIdDepartmentOfAdministratorListAdministrator.getAdministratorList().remove(administratorListAdministrator);
                    oldIdDepartmentOfAdministratorListAdministrator = em.merge(oldIdDepartmentOfAdministratorListAdministrator);
                }
            }
            for (Interviewer interviewerListInterviewer : department.getInterviewerList()) {
                Department oldIdDepartmentOfInterviewerListInterviewer = interviewerListInterviewer.getIdDepartment();
                interviewerListInterviewer.setIdDepartment(department);
                interviewerListInterviewer = em.merge(interviewerListInterviewer);
                if (oldIdDepartmentOfInterviewerListInterviewer != null) {
                    oldIdDepartmentOfInterviewerListInterviewer.getInterviewerList().remove(interviewerListInterviewer);
                    oldIdDepartmentOfInterviewerListInterviewer = em.merge(oldIdDepartmentOfInterviewerListInterviewer);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDepartment(department.getId()) != null) {
                throw new PreexistingEntityException("Department " + department + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Department department) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Department persistentDepartment = em.find(Department.class, department.getId());
            List<Vacancy> vacancyListOld = persistentDepartment.getVacancyList();
            List<Vacancy> vacancyListNew = department.getVacancyList();
            List<Administrator> administratorListOld = persistentDepartment.getAdministratorList();
            List<Administrator> administratorListNew = department.getAdministratorList();
            List<Interviewer> interviewerListOld = persistentDepartment.getInterviewerList();
            List<Interviewer> interviewerListNew = department.getInterviewerList();
            List<Vacancy> attachedVacancyListNew = new ArrayList<Vacancy>();
            for (Vacancy vacancyListNewVacancyToAttach : vacancyListNew) {
                vacancyListNewVacancyToAttach = em.getReference(vacancyListNewVacancyToAttach.getClass(), vacancyListNewVacancyToAttach.getId());
                attachedVacancyListNew.add(vacancyListNewVacancyToAttach);
            }
            vacancyListNew = attachedVacancyListNew;
            department.setVacancyList(vacancyListNew);
            List<Administrator> attachedAdministratorListNew = new ArrayList<Administrator>();
            for (Administrator administratorListNewAdministratorToAttach : administratorListNew) {
                administratorListNewAdministratorToAttach = em.getReference(administratorListNewAdministratorToAttach.getClass(), administratorListNewAdministratorToAttach.getId());
                attachedAdministratorListNew.add(administratorListNewAdministratorToAttach);
            }
            administratorListNew = attachedAdministratorListNew;
            department.setAdministratorList(administratorListNew);
            List<Interviewer> attachedInterviewerListNew = new ArrayList<Interviewer>();
            for (Interviewer interviewerListNewInterviewerToAttach : interviewerListNew) {
                interviewerListNewInterviewerToAttach = em.getReference(interviewerListNewInterviewerToAttach.getClass(), interviewerListNewInterviewerToAttach.getId());
                attachedInterviewerListNew.add(interviewerListNewInterviewerToAttach);
            }
            interviewerListNew = attachedInterviewerListNew;
            department.setInterviewerList(interviewerListNew);
            department = em.merge(department);
            for (Vacancy vacancyListOldVacancy : vacancyListOld) {
                if (!vacancyListNew.contains(vacancyListOldVacancy)) {
                    vacancyListOldVacancy.setIdDepartment(null);
                    vacancyListOldVacancy = em.merge(vacancyListOldVacancy);
                }
            }
            for (Vacancy vacancyListNewVacancy : vacancyListNew) {
                if (!vacancyListOld.contains(vacancyListNewVacancy)) {
                    Department oldIdDepartmentOfVacancyListNewVacancy = vacancyListNewVacancy.getIdDepartment();
                    vacancyListNewVacancy.setIdDepartment(department);
                    vacancyListNewVacancy = em.merge(vacancyListNewVacancy);
                    if (oldIdDepartmentOfVacancyListNewVacancy != null && !oldIdDepartmentOfVacancyListNewVacancy.equals(department)) {
                        oldIdDepartmentOfVacancyListNewVacancy.getVacancyList().remove(vacancyListNewVacancy);
                        oldIdDepartmentOfVacancyListNewVacancy = em.merge(oldIdDepartmentOfVacancyListNewVacancy);
                    }
                }
            }
            for (Administrator administratorListOldAdministrator : administratorListOld) {
                if (!administratorListNew.contains(administratorListOldAdministrator)) {
                    administratorListOldAdministrator.setIdDepartment(null);
                    administratorListOldAdministrator = em.merge(administratorListOldAdministrator);
                }
            }
            for (Administrator administratorListNewAdministrator : administratorListNew) {
                if (!administratorListOld.contains(administratorListNewAdministrator)) {
                    Department oldIdDepartmentOfAdministratorListNewAdministrator = administratorListNewAdministrator.getIdDepartment();
                    administratorListNewAdministrator.setIdDepartment(department);
                    administratorListNewAdministrator = em.merge(administratorListNewAdministrator);
                    if (oldIdDepartmentOfAdministratorListNewAdministrator != null && !oldIdDepartmentOfAdministratorListNewAdministrator.equals(department)) {
                        oldIdDepartmentOfAdministratorListNewAdministrator.getAdministratorList().remove(administratorListNewAdministrator);
                        oldIdDepartmentOfAdministratorListNewAdministrator = em.merge(oldIdDepartmentOfAdministratorListNewAdministrator);
                    }
                }
            }
            for (Interviewer interviewerListOldInterviewer : interviewerListOld) {
                if (!interviewerListNew.contains(interviewerListOldInterviewer)) {
                    interviewerListOldInterviewer.setIdDepartment(null);
                    interviewerListOldInterviewer = em.merge(interviewerListOldInterviewer);
                }
            }
            for (Interviewer interviewerListNewInterviewer : interviewerListNew) {
                if (!interviewerListOld.contains(interviewerListNewInterviewer)) {
                    Department oldIdDepartmentOfInterviewerListNewInterviewer = interviewerListNewInterviewer.getIdDepartment();
                    interviewerListNewInterviewer.setIdDepartment(department);
                    interviewerListNewInterviewer = em.merge(interviewerListNewInterviewer);
                    if (oldIdDepartmentOfInterviewerListNewInterviewer != null && !oldIdDepartmentOfInterviewerListNewInterviewer.equals(department)) {
                        oldIdDepartmentOfInterviewerListNewInterviewer.getInterviewerList().remove(interviewerListNewInterviewer);
                        oldIdDepartmentOfInterviewerListNewInterviewer = em.merge(oldIdDepartmentOfInterviewerListNewInterviewer);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = department.getId();
                if (findDepartment(id) == null) {
                    throw new NonexistentEntityException("The department with id " + id + " no longer exists.");
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
            Department department;
            try {
                department = em.getReference(Department.class, id);
                department.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The department with id " + id + " no longer exists.", enfe);
            }
            List<Vacancy> vacancyList = department.getVacancyList();
            for (Vacancy vacancyListVacancy : vacancyList) {
                vacancyListVacancy.setIdDepartment(null);
                vacancyListVacancy = em.merge(vacancyListVacancy);
            }
            List<Administrator> administratorList = department.getAdministratorList();
            for (Administrator administratorListAdministrator : administratorList) {
                administratorListAdministrator.setIdDepartment(null);
                administratorListAdministrator = em.merge(administratorListAdministrator);
            }
            List<Interviewer> interviewerList = department.getInterviewerList();
            for (Interviewer interviewerListInterviewer : interviewerList) {
                interviewerListInterviewer.setIdDepartment(null);
                interviewerListInterviewer = em.merge(interviewerListInterviewer);
            }
            em.remove(department);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Department> findDepartmentEntities() {
        return findDepartmentEntities(true, -1, -1);
    }

    public List<Department> findDepartmentEntities(int maxResults, int firstResult) {
        return findDepartmentEntities(false, maxResults, firstResult);
    }

    private List<Department> findDepartmentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Department.class));
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

    public Department findDepartment(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Department.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepartmentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Department> rt = cq.from(Department.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
