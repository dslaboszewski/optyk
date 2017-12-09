package com.fukorsoft.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import com.fukorsoft.config.DBManager;
import com.fukorsoft.dao.SposobPlatnosciDao;
import com.fukorsoft.dao.SposobPlatnosciDaoImpl;
import com.fukorsoft.entity.SposobPlatnosci;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
//@SessionScoped
@ViewScoped
public class SposobPlatnosciBean implements Serializable { 
    
    private SposobPlatnosci sposobPlatnosci = new SposobPlatnosci();    
    private SposobPlatnosci selectedSposobPlatnosci = new SposobPlatnosci();
    private List<SposobPlatnosci> lista = new ArrayList<SposobPlatnosci>();
    private List<SposobPlatnosci> filteredSposobyPlatnosci = new ArrayList<SposobPlatnosci>();
            
    public SposobPlatnosciBean() {
    }
    
    public SposobPlatnosci getSposobPlatnosci() {
        return sposobPlatnosci;
    }
    
    public void setSposobPlatnosci(SposobPlatnosci sposobPlatnosci) {
        this.sposobPlatnosci = sposobPlatnosci;
    }
    
    public SposobPlatnosci getSelectedSposobPlatnosci() {
        return selectedSposobPlatnosci;
    }
    
    public void setSelectedSposobPlatnosci(SposobPlatnosci selectedSposobPlatnosci) {
        this.selectedSposobPlatnosci = selectedSposobPlatnosci;
    }
    
    public List<SposobPlatnosci> getFilteredSposobyPlatnosci() {
        return filteredSposobyPlatnosci;
    }

    public void setFilteredSposobyPlatnosci(List<SposobPlatnosci> filteredSposobyPlatnosci) {
        this.filteredSposobyPlatnosci = filteredSposobyPlatnosci;
    }
    
    public List<SposobPlatnosci> getLista() {
        EntityManager em = DBManager.getManager().createEntityManager();
        this.lista = em.createNamedQuery("SposobPlatnosci.findAll").getResultList();
        em.close();
        
        if (this.filteredSposobyPlatnosci == null) {
            this.filteredSposobyPlatnosci = this.lista;
        }
        
        if (this.filteredSposobyPlatnosci.isEmpty()) {
            this.filteredSposobyPlatnosci = this.lista;
        }        
        return this.lista;
    }
    
    public void btnCreateSposobPlatnosci() {
        SposobPlatnosciDao sposobPlatnosciDao = new SposobPlatnosciDaoImpl();
        String msg;
        if (sposobPlatnosciDao.create(this.sposobPlatnosci)) {
            msg = "Rekord został utworzony";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);            
        } else {
            msg = "Wystąpił błąd podczas tworzenia rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.sposobPlatnosci = new SposobPlatnosci();
    }

    public void btnUpdateSposobPlatnosci() {
        SposobPlatnosciDao sposobPlatnosciDao = new SposobPlatnosciDaoImpl();
        String msg;
        if (sposobPlatnosciDao.update(this.selectedSposobPlatnosci)) {
            msg = "Rekord został zaktualizowany";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Wystąpił błąd podczas aktualizacji rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.selectedSposobPlatnosci = new SposobPlatnosci();
    }
}