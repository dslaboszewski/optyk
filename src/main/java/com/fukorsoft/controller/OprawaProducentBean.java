package com.fukorsoft.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import com.fukorsoft.config.DBManager;
import com.fukorsoft.dao.OprawaProducentDao;
import com.fukorsoft.dao.OprawaProducentDaoImpl;
import com.fukorsoft.entity.OprawaProducent;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
//@SessionScoped
@ViewScoped
public class OprawaProducentBean implements Serializable {

    private OprawaProducent oprawaProducent = new OprawaProducent();    
    private OprawaProducent selectedOprawaProducent = new OprawaProducent();
    private List<OprawaProducent> lista = new ArrayList<OprawaProducent>();
    private List<OprawaProducent> filteredOprawaProducenci = new ArrayList<OprawaProducent>();
            
    public OprawaProducentBean() {
    }
    
    public OprawaProducent getOprawaProducent() {
        return oprawaProducent;
    }
    
    public void setOprawaProducent(OprawaProducent oprawaProducent) {
        this.oprawaProducent = oprawaProducent;
    }
    
    public OprawaProducent getSelectedOprawaProducent() {
        return selectedOprawaProducent;
    }
    
    public void setSelectedOprawaProducent(OprawaProducent selectedOprawaProducent) {
        this.selectedOprawaProducent = selectedOprawaProducent;
    }
    
    public List<OprawaProducent> getFilteredOprawaProducenci() {
        return filteredOprawaProducenci;
    }

    public void setFilteredOprawaProducenci(List<OprawaProducent> filteredOprawaProducenci) {
        this.filteredOprawaProducenci = filteredOprawaProducenci;
    }
    
    public List<OprawaProducent> getLista() {
        EntityManager em = DBManager.getManager().createEntityManager();
        this.lista = em.createNamedQuery("OprawaProducent.findAll").getResultList();
        em.close();
        
        if (this.filteredOprawaProducenci == null) {
            this.filteredOprawaProducenci = this.lista;
        }
        
        if (this.filteredOprawaProducenci.isEmpty()) {
            this.filteredOprawaProducenci = this.lista;
        }        
        return this.lista;
    }
    
    public void btnCreateOprawaProducent() {
        OprawaProducentDao oprawaProducentDao = new OprawaProducentDaoImpl();
        String msg;
        if (oprawaProducentDao.create(this.oprawaProducent)) {
            msg = "Rekord został utworzony";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);            
        } else {
            msg = "Wystąpił błąd podczas tworzenia rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.oprawaProducent = new OprawaProducent();
    }

    public void btnUpdateOprawaProducent() {
        OprawaProducentDao oprawaProducentDao = new OprawaProducentDaoImpl();
        String msg;
        if (oprawaProducentDao.update(this.selectedOprawaProducent)) {
            msg = "Rekord został zaktualizowany";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Wystąpił błąd podczas aktualizacji rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.selectedOprawaProducent = new OprawaProducent();
    }
}