package com.fukorsoft.controller;

import java.io.Serializable;
import java.util.List;
import com.fukorsoft.entity.Zaklad;
import javax.persistence.EntityManager;
import com.fukorsoft.config.DBManager;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ZakladBean implements Serializable {

    private Zaklad zaklad = new Zaklad();    
            
    public ZakladBean() {
    }
    
    public Zaklad getZaklad() {
        return zaklad;
    }
    
    public void setZaklad(Zaklad zaklad) {
        this.zaklad = zaklad;
    }
    
    public List<Zaklad> getLista() {
        EntityManager em = DBManager.getManager().createEntityManager();
        List list = em.createNamedQuery("Zaklad.findAll").getResultList();
        em.close();
        return list;
    }
}