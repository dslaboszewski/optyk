package com.fukorsoft.controller;

import java.io.Serializable;
import com.fukorsoft.config.DBManager;
import com.fukorsoft.entity.Adres;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
//import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;

@ManagedBean
//@SessionScoped
//@RequestScoped
@ViewScoped
public class AdresBean implements Serializable {

    private Adres adres;
    
    public AdresBean() {
        this.adres = new Adres();
    }
    
    public Adres getAdres() {
        return adres;
    }
    
    public void setAdres(Adres adres) {
        this.adres = adres;
    }
    
    public String dodaj() {
        EntityManager em = DBManager.getManager().createEntityManager();
        em.getTransaction().begin();
        adres.setId(null);
        em.persist(adres);
        em.getTransaction().commit();
        em.close();
        this.adres = new Adres();
        return null;
    }   
}