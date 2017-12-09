package com.fukorsoft.dao;

import com.fukorsoft.config.DBManager;
import com.fukorsoft.entity.SzklaProducent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

public class SzklaProducentDaoImpl implements SzklaProducentDao {
    
    @Override
    public boolean create(SzklaProducent szklaProducent) {
        boolean flag = false;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            szklaProducent.setId(null);
            em.persist(szklaProducent);
            em.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            em.getTransaction().rollback();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        em.close();
        return flag;
    }
    
    @Override
    public boolean update(SzklaProducent szklaProducent) {
        boolean flag = false;        
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();                    
            em.merge(szklaProducent);            
            em.getTransaction().commit();
            flag = true;
        }
        catch (Exception e) {
            flag = false;
            em.getTransaction().rollback();            
        }       
        em.close();
        return flag;
    }
}