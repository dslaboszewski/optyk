package com.fukorsoft.controller;

import com.fukorsoft.config.DBManager;
import com.fukorsoft.dao.PanstwoDao;
import com.fukorsoft.dao.PanstwoDaoImpl;
import com.fukorsoft.entity.Panstwo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
//import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import org.primefaces.context.RequestContext;

@ManagedBean
//@SessionScoped
@ViewScoped
public class PanstwoBean implements Serializable {

    private Panstwo panstwo = new Panstwo();   
    private Panstwo selectedPanstwo = new Panstwo();
    private List<Panstwo> lista = new ArrayList<Panstwo>();
    private List<Panstwo> filteredPanstwa = new ArrayList<Panstwo>();
    
    public PanstwoBean() {
        this.filteredPanstwa = new ArrayList<Panstwo>();        
    }                    
    
    public Panstwo getPanstwo() {
        return panstwo;
    }
    
    public void setPanstwo(Panstwo panstwo) {
        this.panstwo = panstwo;
    }
    
    public Panstwo getSelectedPanstwo() {
        return selectedPanstwo;
    }
    
    public void setSelectedPanstwo(Panstwo selectedPanstwo) {
        this.selectedPanstwo = selectedPanstwo;
    }
    
    public List<Panstwo> getFilteredPanstwa() {
        return filteredPanstwa;
    }

    public void setFilteredPanstwa(List<Panstwo> filteredPanstwa) {
        this.filteredPanstwa = filteredPanstwa;
    }
    
    public List<Panstwo> getLista() {
        EntityManager em = DBManager.getManager().createEntityManager();
        this.lista = em.createNamedQuery("Panstwo.findAll").getResultList();
        em.close();
        
        if (this.filteredPanstwa == null) {
            this.filteredPanstwa = this.lista;
        }
        
        if (this.filteredPanstwa.isEmpty()) {
            this.filteredPanstwa = this.lista;
        }        
        return this.lista;
    }
    
    public void btnCreatePanstwo() {
        PanstwoDao panstwoDao = new PanstwoDaoImpl();
        String msg;
        if (panstwoDao.create(this.panstwo)) {
            msg = "Rekord został utworzony";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);            
        } else {
            msg = "Wystąpił błąd podczas tworzenia rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.panstwo = new Panstwo();
    }

    public void btnUpdatePanstwo() {
        PanstwoDao panstwoDao = new PanstwoDaoImpl();
        String msg;
        if (panstwoDao.update(this.selectedPanstwo)) {
            msg = "Rekord został zaktualizowany";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Wystąpił błąd podczas aktualizacji rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.selectedPanstwo = new Panstwo();
    }
}