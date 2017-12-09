package com.fukorsoft.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import com.fukorsoft.config.DBManager;
import com.fukorsoft.dao.SoczewkiProducentDao;
import com.fukorsoft.dao.SoczewkiProducentDaoImpl;
import com.fukorsoft.entity.SoczewkiProducent;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
//@SessionScoped
@ViewScoped
public class SoczewkiProducentBean implements Serializable {

    private SoczewkiProducent soczewkiProducent = new SoczewkiProducent();    
    private SoczewkiProducent selectedSoczewkiProducent = new SoczewkiProducent();
    private List<SoczewkiProducent> lista = new ArrayList<SoczewkiProducent>();
    private List<SoczewkiProducent> filteredSoczewkiProducenci = new ArrayList<SoczewkiProducent>();
            
    public SoczewkiProducentBean() {
    }
    
    public SoczewkiProducent getSoczewkiProducent() {
        return soczewkiProducent;
    }
    
    public void setSoczewkiProducent(SoczewkiProducent soczewkiProducent) {
        this.soczewkiProducent = soczewkiProducent;
    }
    
    public SoczewkiProducent getSelectedSoczewkiProducent() {
        return selectedSoczewkiProducent;
    }
    
    public void setSelectedSoczewkiProducent(SoczewkiProducent selectedSoczewkiProducent) {
        this.selectedSoczewkiProducent = selectedSoczewkiProducent;
    }
    
    public List<SoczewkiProducent> getFilteredSoczewkiProducenci() {
        return filteredSoczewkiProducenci;
    }

    public void setFilteredSoczewkiProducenci(List<SoczewkiProducent> filteredSoczewkiProducenci) {
        this.filteredSoczewkiProducenci = filteredSoczewkiProducenci;
    }
    
    public List<SoczewkiProducent> getLista() {
        EntityManager em = DBManager.getManager().createEntityManager();
        this.lista = em.createNamedQuery("SoczewkiProducent.findAll").getResultList();
        em.close();
        
        if (this.filteredSoczewkiProducenci == null) {
            this.filteredSoczewkiProducenci = this.lista;
        }
        
        if (this.filteredSoczewkiProducenci.isEmpty()) {
            this.filteredSoczewkiProducenci = this.lista;
        }        
        return this.lista;
    }
    
    public void btnCreateSoczewkiProducent() {
        SoczewkiProducentDao soczewkiProducentDao = new SoczewkiProducentDaoImpl();
        String msg;
        if (soczewkiProducentDao.create(this.soczewkiProducent)) {
            msg = "Rekord został utworzony";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);            
        } else {
            msg = "Wystąpił błąd podczas tworzenia rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.soczewkiProducent = new SoczewkiProducent();
    }

    public void btnUpdateSoczewkiProducent() {
        SoczewkiProducentDao soczewkiProducentDao = new SoczewkiProducentDaoImpl();
        String msg;
        if (soczewkiProducentDao.update(this.selectedSoczewkiProducent)) {
            msg = "Rekord został zaktualizowany";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Wystąpił błąd podczas aktualizacji rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.selectedSoczewkiProducent = new SoczewkiProducent();
    }
}