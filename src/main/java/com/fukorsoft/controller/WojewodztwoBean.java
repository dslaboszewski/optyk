package com.fukorsoft.controller;

import com.fukorsoft.config.DBManager;
import com.fukorsoft.dao.WojewodztwoDao;
import com.fukorsoft.dao.WojewodztwoDaoImpl;
import com.fukorsoft.entity.Wojewodztwo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

@ManagedBean
//@SessionScoped
@ViewScoped
public class WojewodztwoBean implements Serializable {

    private Wojewodztwo wojewodztwo = new Wojewodztwo();   
    private Wojewodztwo selectedWojewodztwo = new Wojewodztwo();
    private List<Wojewodztwo> lista = new ArrayList<Wojewodztwo>();
    private List<Wojewodztwo> filteredWojewodztwa = new ArrayList<Wojewodztwo>();
    
    public WojewodztwoBean() {
        this.filteredWojewodztwa = new ArrayList<Wojewodztwo>();     
    }                    
    
    public Wojewodztwo getWojewodztwo() {
        return wojewodztwo;
    }
    
    public void setWojewodztwo(Wojewodztwo wojewodztwo) {
        this.wojewodztwo = wojewodztwo;
    }
    
    public Wojewodztwo getSelectedWojewodztwo() {
        return selectedWojewodztwo;
    }
    
    public void setSelectedWojewodztwo(Wojewodztwo selectedWojewodztwo) {
        this.selectedWojewodztwo = selectedWojewodztwo;
    }
    
    public List<Wojewodztwo> getFilteredWojewodztwa() {
        return filteredWojewodztwa;
    }

    public void setFilteredWojewodztwa(List<Wojewodztwo> filteredWojewodztwa) {
        this.filteredWojewodztwa = filteredWojewodztwa;
    }
    
    public List<Wojewodztwo> getLista() {
        EntityManager em = DBManager.getManager().createEntityManager();
        this.lista = em.createNamedQuery("Wojewodztwo.findAll").getResultList();
        em.close();
        
        if (this.filteredWojewodztwa == null) {
            this.filteredWojewodztwa = this.lista;
        }
        
        if (this.filteredWojewodztwa.isEmpty()) {
            this.filteredWojewodztwa = this.lista;
        }   
        return this.lista;
    }
    
    public void btnCreateWojewodztwo() {
        WojewodztwoDao wojewodztwoDao = new WojewodztwoDaoImpl();
        String msg;
        if (wojewodztwoDao.create(this.wojewodztwo)) {
            msg = "Rekord został utworzony";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Wystąpił błąd podczas tworzenia rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.wojewodztwo = new Wojewodztwo();
    }

    public void btnUpdateWojewodztwo() {
        WojewodztwoDao wojewodztwoDao = new WojewodztwoDaoImpl();
        String msg;
        if (wojewodztwoDao.update(this.selectedWojewodztwo)) {
            msg = "Rekord został zaktualizowany";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Wystąpił błąd podczas aktualizacji rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.selectedWojewodztwo = new Wojewodztwo();
    }
}