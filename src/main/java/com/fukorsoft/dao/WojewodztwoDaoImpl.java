package com.fukorsoft.dao;

import com.fukorsoft.config.DBManager;
import com.fukorsoft.entity.Wojewodztwo;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

public class WojewodztwoDaoImpl implements WojewodztwoDao {
    
    @Override
    public boolean create(Wojewodztwo wojewodztwo) {
        boolean flag = false;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            wojewodztwo.setId(null);
            em.persist(wojewodztwo);
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
    public boolean update(Wojewodztwo wojewodztwo) {
        boolean flag = false;        
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();                    
            em.merge(wojewodztwo);            
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