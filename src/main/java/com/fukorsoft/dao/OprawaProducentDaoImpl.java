package com.fukorsoft.dao;

import com.fukorsoft.config.DBManager;
import com.fukorsoft.entity.OprawaProducent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

public class OprawaProducentDaoImpl implements OprawaProducentDao {
    
    @Override
    public boolean create(OprawaProducent oprawaProducent) {
        boolean flag = false;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            oprawaProducent.setId(null);
            em.persist(oprawaProducent);
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
    public boolean update(OprawaProducent oprawaProducent) {
        boolean flag = false;        
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();                    
            em.merge(oprawaProducent);            
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