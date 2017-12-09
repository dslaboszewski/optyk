package com.fukorsoft.dao;

import com.fukorsoft.config.DBManager;
import com.fukorsoft.entity.Miejscowosc;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

public class MiejscowoscDaoImpl implements MiejscowoscDao {
    
    @Override
    public boolean create(Miejscowosc miejscowosc) {
        boolean flag = false;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            miejscowosc.setId(null);
            em.persist(miejscowosc);
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
    public boolean update(Miejscowosc miejscowosc) {
        boolean flag = false;        
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();                    
            em.merge(miejscowosc);            
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