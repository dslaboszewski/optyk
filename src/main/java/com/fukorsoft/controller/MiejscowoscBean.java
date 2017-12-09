package com.fukorsoft.controller;

import com.fukorsoft.config.DBManager;
import com.fukorsoft.dao.MiejscowoscDao;
import com.fukorsoft.dao.MiejscowoscDaoImpl;
import com.fukorsoft.entity.Miejscowosc;
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
public class MiejscowoscBean implements Serializable {

    private Miejscowosc miejscowosc = new Miejscowosc();   
    private Miejscowosc selectedMiejscowosc = new Miejscowosc();
    private List<Miejscowosc> lista = new ArrayList<Miejscowosc>();
    private List<Miejscowosc> filteredMiejscowosci = new ArrayList<Miejscowosc>();
    
    public MiejscowoscBean() {
        this.filteredMiejscowosci = new ArrayList<Miejscowosc>();     
    }                    
    
    public Miejscowosc getMiejscowosc() {
        return miejscowosc;
    }
    
    public void setMiejscowosc(Miejscowosc miejscowosc) {
        this.miejscowosc = miejscowosc;
    }
    
    public Miejscowosc getSelectedMiejscowosc() {
        return selectedMiejscowosc;
    }
    
    public void setSelectedMiejscowosc(Miejscowosc selectedMiejscowosc) {
        this.selectedMiejscowosc = selectedMiejscowosc;
    }
    
    public List<Miejscowosc> getFilteredMiejscowosci() {
        return filteredMiejscowosci;
    }

    public void setFilteredMiejscowosci(List<Miejscowosc> filteredMiejscowosci) {
        this.filteredMiejscowosci = filteredMiejscowosci;
    }
    
    public List<Miejscowosc> getLista() {
        EntityManager em = DBManager.getManager().createEntityManager();
        this.lista = em.createNamedQuery("Miejscowosc.findAll").getResultList();
        em.close();
        
        if (this.filteredMiejscowosci == null) {
            this.filteredMiejscowosci = this.lista;
        }
        
        if (this.filteredMiejscowosci.isEmpty()) {
            this.filteredMiejscowosci = this.lista;
        }   
        return this.lista;
    }
    
    public void btnCreateMiejscowosc() {
        MiejscowoscDao miejscowoscDao = new MiejscowoscDaoImpl();
        String msg;
        if (miejscowoscDao.create(this.miejscowosc)) {
            msg = "Rekord został utworzony";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Wystąpił błąd podczas tworzenia rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.miejscowosc = new Miejscowosc();
    }

    public void btnUpdateMiejscowosc() {
        MiejscowoscDao miejscowoscDao = new MiejscowoscDaoImpl();
        String msg;
        if (miejscowoscDao.update(this.selectedMiejscowosc)) {
            msg = "Rekord został zaktualizowany";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Wystąpił błąd podczas aktualizacji rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.selectedMiejscowosc = new Miejscowosc();
    }
}