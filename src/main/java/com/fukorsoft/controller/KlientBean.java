package com.fukorsoft.controller;

import com.fukorsoft.config.DBManager;
import com.fukorsoft.dao.KlientDao;
import com.fukorsoft.dao.KlientDaoImpl;
import com.fukorsoft.entity.Adres;
import com.fukorsoft.entity.Klient;
import com.fukorsoft.entity.Zaklad;
import java.io.Serializable;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
//import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.SelectEvent;
//import org.primefaces.context.RequestContext;

@ManagedBean
//@SessionScoped
//@RequestScoped
@ViewScoped
public class KlientBean implements Serializable {

    private Klient klient;
    private Adres klientAdres;
    private List<Klient> klienci;
    private List<Klient> filteredKlienci;
    private Klient selectedKlient;
    private Adres selectedKlientAdres = new Adres();

    public KlientBean() {
        this.klient = new Klient();
        this.klientAdres = new Adres();
        this.klienci = new ArrayList<Klient>();
        this.filteredKlienci = new ArrayList<Klient>();
    }
    
    public void ustawCtxKlienta(Klient klient) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();        
        request.getSession().setAttribute("ctxKlient", klient);
    }
    
    public void setKlientZaklad() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int zaklad_id;
        
        if (request.getSession().getAttribute("zakladKlienci") != null) {
            zaklad_id = (int) request.getSession().getAttribute("zakladKlienci");
            Zaklad zaklad = new Zaklad(zaklad_id);
            this.klient.setZaklad(zaklad);
        }                     
    }
    
    public String getKartotekaKlientNaglowek() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int zaklad_id;
        Zaklad zaklad;
        String zaklad_nazwa = "Wszystkie salony";
        
        if (request.getSession().getAttribute("zakladKlienci") != null) {
            zaklad_id = (int) request.getSession().getAttribute("zakladKlienci");
            
            EntityManager em = DBManager.getManager().createEntityManager();
            try {
                em.getTransaction().begin();
                Query query = em.createNamedQuery("Zaklad.findById");
                query.setParameter("id", zaklad_id);
                zaklad = (Zaklad) query.getSingleResult();         
                zaklad_nazwa = zaklad.getNazwa();
                em.getTransaction().commit();
            }
            catch (Exception e) {
                em.getTransaction().rollback();
            }                         
        }  
        return zaklad_nazwa;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public Adres getKlientAdres() {
        return klientAdres;
    }

    public void setKlientAdres(Adres klientAdres) {
        this.klientAdres = klientAdres;
    }

    public Adres getSelectedKlientAdres() {
        if (selectedKlient != null) {
            if (selectedKlient.getAdresSet() != null) {
                if (selectedKlient.getAdresSet().toArray()[0] != null) {
                    selectedKlientAdres = (Adres) selectedKlient.getAdresSet().toArray()[0];
                }
            }
        }
        return selectedKlientAdres;
    }

    public void setSelectedKlientAdres(Adres selectedKlientAdres) {
        if (selectedKlientAdres != null) {
            if (this.selectedKlient != null) {
                selectedKlientAdres.setKlient(this.selectedKlient);
                this.selectedKlientAdres = selectedKlientAdres;
            }
        }
    }

    public List<Klient> getFilteredKlienci() {
        return filteredKlienci;
    }

    public void setFilteredKlienci(List<Klient> filteredKlienci) {
        this.filteredKlienci = filteredKlienci;
    }

    public List<Klient> getKlienci() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int zaklad = 0;
        KlientDao klientDao = new KlientDaoImpl();

//        if (request.getParameter("zaklad") == null)
//            zaklad = 0;
//        else {
//            zaklad = parseInt(request.getParameter("zaklad"));                        
//            request.getSession().setAttribute("zaklad", zaklad);
//        }
        if (request.getParameter("zaklad") != null) {
            zaklad = parseInt(request.getParameter("zaklad"));
            request.getSession().setAttribute("zakladKlienci", zaklad);
        }

        if (request.getSession().getAttribute("zakladKlienci") != null) {
            zaklad = (int) request.getSession().getAttribute("zakladKlienci");
        }

        if (zaklad > 0) {
            this.klienci = klientDao.findAllFromZaklad(zaklad);
        } else {
            this.klienci = klientDao.findAll();
        }

        //request.getSession().removeAttribute("zakladKlienci");
        
        if (this.filteredKlienci == null) {
            this.filteredKlienci = this.klienci;
        }

        if (this.filteredKlienci.isEmpty()) {
            this.filteredKlienci = this.klienci;
        }

        return klienci;
    }

    public boolean filterByDataWpr(Object value, Object filter, Locale locale) {
        boolean retVal;
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }

        if (value == null) {
            return false;
        }

        if (((java.util.Date) value).compareTo((java.util.Date) filter) > 0) {
            retVal = true;
        } else {
            retVal = false;
        }

        return retVal;
    }

    public Klient getSelectedKlient() {
        return selectedKlient;
    }

    public void setSelectedKlient(Klient selectedKlient) {
        if (selectedKlient != null) {
            this.selectedKlient = selectedKlient;
            if (this.selectedKlient.getAdresSet().size() == 0) {
                Set<Adres> adresSet = new HashSet<>();

                Adres adres = new Adres();
                adres.setKlient(this.selectedKlient);
                adresSet.add(adres);

                this.selectedKlient.setAdresSet(adresSet);
            }
        }
    }

    public void btnCreateKlient() {
        KlientDao klientDao = new KlientDaoImpl();
        String msg;
        if (klientDao.create(this.klient, this.klientAdres)) {
            msg = "Rekord został utworzony";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            //System.out.println("Błąd przy dodawaniu klienta");
            msg = "Wystąpił błąd podczas tworzenia rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.klient = new Klient();
        this.klientAdres = new Adres();
    }

//    public void btnUpdateKlient(AdresBean ab) {
//        KlientDao klientDao = new KlientDaoImpl();
//        this.klientAdres = ab.getAdres();
//        String msg;
//        if (klientDao.update(this.klient, this.klientAdres)) {
//            msg = "Klient został zaktualizowany";
//        } else {
//            msg = "Wystąpił błąd podczas aktualizacji klienta";
//        }
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
//        FacesContext.getCurrentInstance().addMessage(null, message);
//    }
    public void btnUpdateKlient() {
        KlientDao klientDao = new KlientDaoImpl();
        String msg;
        if (klientDao.update(this.selectedKlient)) {
            msg = "Rekord został zaktualizowany";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Wystąpił błąd podczas aktualizacji rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.selectedKlient = new Klient();
    }

    public void btnDeleteKlient() {
        KlientDao klientDao = new KlientDaoImpl();
        String msg;
        if (klientDao.delete(this.selectedKlient.getId())) {
            msg = "Rekord został usunięty";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Wystąpił błąd podczas usuwania rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.selectedKlient = new Klient();
    }
    
    public void rowSelectListener(SelectEvent event) {  
        this.klient = (Klient) event.getObject();
    }  
    
    public void clearAttributes() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().removeAttribute("zakladKlienci");
    }
}
