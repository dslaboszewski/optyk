package com.fukorsoft.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import com.fukorsoft.config.DBManager;
import com.fukorsoft.dao.TypUslugiDao;
import com.fukorsoft.dao.TypUslugiDaoImpl;
import com.fukorsoft.entity.TypUslugi;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
//@SessionScoped
@ViewScoped
public class TypUslugiBean implements Serializable {
    
    private TypUslugi typUslugi = new TypUslugi();   
    private TypUslugi selectedTypUslugi = new TypUslugi();
    private List<TypUslugi> lista = new ArrayList<TypUslugi>();
    private List<TypUslugi> filteredTypyUslug = new ArrayList<TypUslugi>();
            
    public TypUslugiBean() {
    }
    
    public TypUslugi getTypUslugi() {
        return typUslugi;
    }
    
    public void setTypUslugi(TypUslugi typUslugi) {
        this.typUslugi = typUslugi;
    }
    
    public TypUslugi getSelectedTypUslugi() {
        return selectedTypUslugi;
    }
    
    public void setSelectedTypUslugi(TypUslugi selectedTypUslugi) {
        this.selectedTypUslugi = selectedTypUslugi;
    }
    
    public List<TypUslugi> getFilteredTypyUslug() {
        return filteredTypyUslug;
    }

    public void setFilteredTypyUslug(List<TypUslugi> filteredTypyUslug) {
        this.filteredTypyUslug = filteredTypyUslug;
    }   
    
    public List<TypUslugi> getLista() {
        EntityManager em = DBManager.getManager().createEntityManager();
        this.lista = em.createNamedQuery("TypUslugi.findAll").getResultList();
        em.close();
        
        if (this.filteredTypyUslug == null) {
            this.filteredTypyUslug = this.lista;
        }
        
        if (this.filteredTypyUslug.isEmpty()) {
            this.filteredTypyUslug = this.lista;
        }        
        return this.lista;
    }
    
    public void btnCreateTypUslugi() {
        TypUslugiDao typUslugiDao = new TypUslugiDaoImpl();
        String msg;
        if (typUslugiDao.create(this.typUslugi)) {
            msg = "Rekord został utworzony";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);            
        } else {
            msg = "Wystąpił błąd podczas tworzenia rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.typUslugi = new TypUslugi();
    }

    public void btnUpdateTypUslugi() {
        TypUslugiDao typUslugiDao = new TypUslugiDaoImpl();
        String msg;
        if (typUslugiDao.update(this.selectedTypUslugi)) {
            msg = "Rekord został zaktualizowany";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Wystąpił błąd podczas aktualizacji rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.selectedTypUslugi = new TypUslugi();
    }
}