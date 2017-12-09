package com.fukorsoft.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import com.fukorsoft.config.DBManager;
import com.fukorsoft.dao.SzklaProducentDao;
import com.fukorsoft.dao.SzklaProducentDaoImpl;
import com.fukorsoft.entity.SzklaProducent;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
//@SessionScoped
@ViewScoped
public class SzklaProducentBean implements Serializable {
    
    private SzklaProducent szklaProducent = new SzklaProducent();    
    private SzklaProducent selectedSzklaProducent = new SzklaProducent();
    private List<SzklaProducent> lista = new ArrayList<SzklaProducent>();
    private List<SzklaProducent> filteredSzklaProducenci = new ArrayList<SzklaProducent>();
            
    public SzklaProducentBean() {
    }
    
    public SzklaProducent getSzklaProducent() {
        return szklaProducent;
    }
    
    public void setSzklaProducent(SzklaProducent szklaProducent) {
        this.szklaProducent = szklaProducent;
    }
    
    public SzklaProducent getSelectedSzklaProducent() {
        return selectedSzklaProducent;
    }
    
    public void setSelectedSzklaProducent(SzklaProducent selectedSzklaProducent) {
        this.selectedSzklaProducent = selectedSzklaProducent;
    }
    
    public List<SzklaProducent> getFilteredSzklaProducenci() {
        return filteredSzklaProducenci;
    }

    public void setFilteredSzklaProducenci(List<SzklaProducent> filteredSzklaProducenci) {
        this.filteredSzklaProducenci = filteredSzklaProducenci;
    }
    
    public List<SzklaProducent> getLista() {
        EntityManager em = DBManager.getManager().createEntityManager();
        this.lista = em.createNamedQuery("SzklaProducent.findAll").getResultList();
        em.close();
        
        if (this.filteredSzklaProducenci == null) {
            this.filteredSzklaProducenci = this.lista;
        }
        
        if (this.filteredSzklaProducenci.isEmpty()) {
            this.filteredSzklaProducenci = this.lista;
        }        
        return this.lista;
    }
    
    public void btnCreateSzklaProducent() {
        SzklaProducentDao szklaProducentDao = new SzklaProducentDaoImpl();
        String msg;
        if (szklaProducentDao.create(this.szklaProducent)) {
            msg = "Rekord został utworzony";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);            
        } else {
            msg = "Wystąpił błąd podczas tworzenia rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.szklaProducent = new SzklaProducent();
    }

    public void btnUpdateSzklaProducent() {
        SzklaProducentDao szklaProducentDao = new SzklaProducentDaoImpl();
        String msg;
        if (szklaProducentDao.update(this.selectedSzklaProducent)) {
            msg = "Rekord został zaktualizowany";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Wystąpił błąd podczas aktualizacji rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.selectedSzklaProducent = new SzklaProducent();
    }
}