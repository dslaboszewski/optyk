package com.fukorsoft.controller;

import com.fukorsoft.config.DBManager;
import com.fukorsoft.dao.SprzedazDao;
import com.fukorsoft.dao.SprzedazDaoImpl;
import com.fukorsoft.dao.UzytkownikDaoImpl;
//import com.fukorsoft.dao.UzytkownikDaoImpl;
import com.fukorsoft.entity.Klient;
import com.fukorsoft.entity.Sprzedaz;
import com.fukorsoft.entity.Zaklad;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.Integer.parseInt;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
//import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
//@SessionScoped
@ViewScoped
public class SprzedazBean implements Serializable {

    //private Sprzedaz sprzedaz = new Sprzedaz(new Date(), UzytkownikDaoImpl.getLoggedUzytkownik().getZaklad());
    private Sprzedaz sprzedaz = new Sprzedaz(UzytkownikDaoImpl.getLoggedUzytkownik());
    private Sprzedaz selectedSprzedaz = new Sprzedaz(UzytkownikDaoImpl.getLoggedUzytkownik());
    private List<Sprzedaz> sprzedaze;
    private List<Sprzedaz> filteredSprzedaze;
    private String zakladNav;
    private int zaklad_id;
    private String dtSprzed;
    private String operacja = "";

    public SprzedazBean() {
        this.sprzedaze = new ArrayList<Sprzedaz>();
        this.filteredSprzedaze = new ArrayList<Sprzedaz>();
    }

    public void setOperacja(String operacja) {
        this.operacja = operacja;
        int zaklad_id = 0;

        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            Klient klient = (Klient) request.getSession().getAttribute("ctxKlient");

            if (klient != null) {
                ustawKlienta(klient);
            }

            if (request.getSession().getAttribute("zakladSprzedaze") != null) {
                zaklad_id = (int) request.getSession().getAttribute("zakladSprzedaze");
            }

            if (zaklad_id > 0) {
                ustawZaklad(new Zaklad(zaklad_id));
            }
        } catch (Exception e) {
            System.out.println("SprzedazBean > setOperacja: " + e.toString());
        }
    }

    public void ustawKlienta(Klient klient) {
        if (this.operacja.equals("I")) {
            this.sprzedaz.setKlient(klient);
        } else if (this.operacja.equals("U")) {
            this.selectedSprzedaz.setKlient(klient);
        }
    }

    public void ustawZaklad(Zaklad zaklad) {
        if (this.operacja.equals("I")) {
            this.sprzedaz.setZaklad(zaklad);
        }
    }

    public String getKartotekaSprzedazNaglowek() {
        Zaklad zaklad;
        String zaklad_nazwa = "Wszystkie salony";

        if (this.zaklad_id > 0) {
            EntityManager em = DBManager.getManager().createEntityManager();
            try {
                em.getTransaction().begin();
                Query query = em.createNamedQuery("Zaklad.findById");
                query.setParameter("id", this.zaklad_id);
                zaklad = (Zaklad) query.getSingleResult();
                zaklad_nazwa = zaklad.getNazwa();
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
            }
        }
        return zaklad_nazwa + " - " + this.dtSprzed;
    }

    public String getDtSprzed() {
        return dtSprzed;
    }

    public void setZakladNav(String zakladNav) {
        this.zakladNav = zakladNav;
    }

    public String getZakladNav() {
        return zakladNav;
    }

    public Sprzedaz getSprzedaz() {
        return sprzedaz;
    }

    public void setSprzedaz(Sprzedaz sprzedaz) {
        this.sprzedaz = sprzedaz;
    }

    public Sprzedaz getSelectedSprzedaz() {
        return selectedSprzedaz;
    }

    public void setSelectedSprzedaz(Sprzedaz selectedSprzedaz) {
        this.selectedSprzedaz = selectedSprzedaz;
    }

    public List<Sprzedaz> getFilteredSprzedaze() {
        if (this.filteredSprzedaze.isEmpty()) {
            this.filteredSprzedaze = getSprzedaze();
        }
        return this.filteredSprzedaze;
    }

    public void setFilteredSprzedaze(List<Sprzedaz> filteredSprzedaze) {
        this.filteredSprzedaze = filteredSprzedaze;
    }

    public List<Sprzedaz> getSprzedaze() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int klient = 0;
        String dataSprz = "";

        SprzedazDao sprzedazDao = new SprzedazDaoImpl();

        if (request.getParameter("zaklad") != null) {
            this.zaklad_id = parseInt(request.getParameter("zaklad"));
            request.getSession().setAttribute("zakladSprzedaze", this.zaklad_id);
        }

//        if (request.getSession().getAttribute("zakladSprzedaze") != null) {
//            this.zaklad_id = (int) request.getSession().getAttribute("zakladSprzedaze");
//        }
        if (request.getParameter("datasprz") != null) {
            dataSprz = request.getParameter("datasprz");
            request.getSession().setAttribute("datasprzSprzedaz", dataSprz);
        }

        if (request.getSession().getAttribute("datasprzSprzedaz") != null) {
            dataSprz = (String) request.getSession().getAttribute("datasprzSprzedaz");
        }

        if (dataSprz != "") {
            SimpleDateFormat fromUser = new SimpleDateFormat("ddMMyyyy");
            SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
            String reformattedStr = "";
            try {
                reformattedStr = myFormat.format(fromUser.parse(dataSprz));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.dtSprzed = reformattedStr;
        } else {
            this.dtSprzed = "Wszystkie dni";
        }

        if (request.getParameter("klient") != null) {
            klient = parseInt(request.getParameter("klient"));
            request.getSession().setAttribute("klientSprzedaz", klient);
        }

        if (request.getSession().getAttribute("klientSprzedaz") != null) {
            klient = (int) request.getSession().getAttribute("klientSprzedaz");
        }

        if (this.zaklad_id > 0 && dataSprz != "") {
            this.sprzedaze = sprzedazDao.findAllFromZaklad(this.zaklad_id, dataSprz);
        } else if (this.zaklad_id == 0 && dataSprz != "") {
            this.sprzedaze = sprzedazDao.findAllByDate(dataSprz);
        } else if (klient > 0) {
            this.sprzedaze = sprzedazDao.findByKlientId(klient);
            //this.sprzedaz.setKlient(new Klient(klient));
        } else {
            this.sprzedaze = sprzedazDao.findAll();
        }

        //request.getSession().removeAttribute("zakladSprzedaz");
        //request.getSession().removeAttribute("datasprzSprzedaz");
        //zaremowano z powodu: Po dodaniu sprzedaży w kontekście klienta pojawia się sprzedaż wszystkich klientów
        //request.getSession().removeAttribute("klientSprzedaz");
        //this.filteredSprzedaze = sprzedaze;
        return sprzedaze;
    }

    public boolean filterByDataSprzed(Object value, Object filter, Locale locale) {
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

    public int getWartoscSprzedazyRazem() {
        int wartosc = 0;
        if (this.filteredSprzedaze.isEmpty()) {
            this.filteredSprzedaze = this.sprzedaze;
        }

        for (Sprzedaz s : this.filteredSprzedaze) {
            wartosc += s.getWartoscSprzedazy();
        }
        
        //this.filteredSprzedaze = new ArrayList<Sprzedaz>();
        
        return wartosc;
    }

    public void btnCreateSprzedaz() throws IOException {
        SprzedazDao sprzedazDao = new SprzedazDaoImpl();
        String msg;
        if (sprzedazDao.create(this.sprzedaz)) {
            msg = "Rekord został utworzony";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Wystąpił błąd podczas tworzenia rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.sprzedaz = new Sprzedaz(UzytkownikDaoImpl.getLoggedUzytkownik());
        //this.sprzedaz = new Sprzedaz(new Date(), UzytkownikDaoImpl.getLoggedUzytkownik().getZaklad());
        //ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        //ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    public void btnUpdateSprzedaz() {
        SprzedazDao sprzedazDao = new SprzedazDaoImpl();
        String msg;
        if (sprzedazDao.update(this.selectedSprzedaz)) {
            msg = "Rekord został zaktualizowany";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Wystąpił błąd podczas aktualizacji rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.selectedSprzedaz = new Sprzedaz(UzytkownikDaoImpl.getLoggedUzytkownik());
    }

    public void btnDeleteSprzedaz() {
        SprzedazDao sprzedazDao = new SprzedazDaoImpl();
        String msg;
        if (sprzedazDao.delete(this.selectedSprzedaz.getId())) {
            msg = "Rekord został usunięty";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Wystąpił błąd podczas usuwania rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.selectedSprzedaz = new Sprzedaz(UzytkownikDaoImpl.getLoggedUzytkownik());
    }

    public void clearAttributes() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().removeAttribute("zakladSprzedaze");
        request.getSession().removeAttribute("datasprzSprzedaz");
        request.getSession().removeAttribute("klientSprzedaz");
        request.getSession().removeAttribute("ctxKlient");
    }
}
