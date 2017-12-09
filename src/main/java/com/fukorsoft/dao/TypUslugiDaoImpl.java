package com.fukorsoft.dao;

import com.fukorsoft.config.DBManager;
import com.fukorsoft.entity.TypUslugi;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

public class TypUslugiDaoImpl implements TypUslugiDao {
    
    @Override
    public boolean create(TypUslugi typUslugi) {
        boolean flag = false;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            typUslugi.setId(null);
            em.persist(typUslugi);
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
    public boolean update(TypUslugi typUslugi) {
        boolean flag = false;        
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();                    
            em.merge(typUslugi);            
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