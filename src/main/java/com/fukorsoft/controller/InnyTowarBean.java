package com.fukorsoft.controller;

import java.io.Serializable;
import java.util.List;
import com.fukorsoft.config.DBManager;
import com.fukorsoft.dao.InnyTowarDao;
import com.fukorsoft.dao.InnyTowarDaoImpl;
import com.fukorsoft.entity.InnyTowar;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

@ManagedBean
//@SessionScoped
@ViewScoped
public class InnyTowarBean implements Serializable {

    private InnyTowar innyTowar = new InnyTowar();    
    private InnyTowar selectedInnyTowar = new InnyTowar();
    private List<InnyTowar> lista = new ArrayList<InnyTowar>();
    private List<InnyTowar> filteredInneTowary = new ArrayList<InnyTowar>();
            
    public InnyTowarBean() {
        this.filteredInneTowary = new ArrayList<InnyTowar>();     
    }
    
    public InnyTowar getInnyTowar() {
        return innyTowar;
    }
    
    public void setInnyTowar(InnyTowar innyTowar) {
        this.innyTowar = innyTowar;
    }
    
    public InnyTowar getSelectedInnyTowar() {
        return selectedInnyTowar;
    }
    
    public void setSelectedInnyTowar(InnyTowar selectedInnyTowar) {
        this.selectedInnyTowar = selectedInnyTowar;
    }
    
    public List<InnyTowar> getFilteredInneTowary() {
        return filteredInneTowary;
    }

    public void setFilteredInneTowary(List<InnyTowar> filteredInneTowary) {
        this.filteredInneTowary = filteredInneTowary;
    }
    
    public List<InnyTowar> getLista() {
        EntityManager em = DBManager.getManager().createEntityManager();
        this.lista = em.createNamedQuery("InnyTowar.findAll").getResultList();
        em.close();
        
        if (this.filteredInneTowary == null) {
            this.filteredInneTowary = this.lista;
        }
        
        if (this.filteredInneTowary.isEmpty()) {
            this.filteredInneTowary = this.lista;
        }        
        return this.lista;
    }
    
    public void btnCreateInnyTowar() {
        InnyTowarDao innyTowarDao = new InnyTowarDaoImpl();
        String msg;
        if (innyTowarDao.create(this.innyTowar)) {
            msg = "Rekord został utworzony";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);            
        } else {
            msg = "Wystąpił błąd podczas tworzenia rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.innyTowar = new InnyTowar();
    }

    public void btnUpdateInnyTowar() {
        InnyTowarDao innyTowarDao = new InnyTowarDaoImpl();
        String msg;
        if (innyTowarDao.update(this.selectedInnyTowar)) {
            msg = "Rekord został zaktualizowany";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Wystąpił błąd podczas aktualizacji rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.selectedInnyTowar = new InnyTowar();
    }
}