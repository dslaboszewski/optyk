package com.fukorsoft.controller;

import com.fukorsoft.config.DBManager;
import com.fukorsoft.dao.NaprawaDao;
import com.fukorsoft.dao.NaprawaDaoImpl;
//import com.fukorsoft.dao.UzytkownikDaoImpl;
import com.fukorsoft.entity.Klient;
import com.fukorsoft.entity.Naprawa;
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
public class NaprawaBean implements Serializable {

    private Naprawa naprawa = new Naprawa();
    private Naprawa selectedNaprawa;
    private List<Naprawa> naprawy;
    private List<Naprawa> filteredNaprawy;
    private String zakladNav;
    private String dtNaprawy;
    private int zaklad_id;
    private String operacja;
    private String dataNaprawy;

    public NaprawaBean() {
        this.naprawy = new ArrayList<Naprawa>();
        this.filteredNaprawy = new ArrayList<Naprawa>();
        this.dataNaprawy = "";
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

            if (request.getSession().getAttribute("zakladNaprawy") != null) {
                zaklad_id = (int) request.getSession().getAttribute("zakladNaprawy");
            }

            if (zaklad_id > 0) {
                ustawZaklad(new Zaklad(zaklad_id));
            }
        } catch (Exception e) {
            System.out.println("NaprawaBean > setOperacja: " + e.toString());
        }
    }

    public void ustawKlienta(Klient klient) {
        if (this.operacja.equals("I")) {
            this.naprawa.setKlient(klient);
        } else if (this.operacja.equals("U")) {
            this.selectedNaprawa.setKlient(klient);
        }
    }

    public void ustawZaklad(Zaklad zaklad) {
        if (this.operacja.equals("I")) {
            this.naprawa.setZaklad(zaklad);
        }
    }

    public String getKartotekaNaprawazNaglowek() {
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
        return zaklad_nazwa + " - " + this.dtNaprawy;
    }

    public String getDtNaprawy() {
        return dtNaprawy;
    }

    public String getZakladNav() {
        return zakladNav;
    }

    public void setZakladNav(String zakladNav) {
        this.zakladNav = zakladNav;
    }

    public Naprawa getNaprawa() {
        return naprawa;
    }

    public void setNaprawa(Naprawa naprawa) {
        this.naprawa = naprawa;
    }

    public Naprawa getSelectedNaprawa() {
        return selectedNaprawa;
    }

    public void setSelectedNaprawa(Naprawa selectedNaprawa) {
        this.selectedNaprawa = selectedNaprawa;
    }

    public List<Naprawa> getFilteredNaprawy() {
        if (this.filteredNaprawy.isEmpty()) {
            this.filteredNaprawy = getNaprawy();
        }
        return filteredNaprawy;
    }

    public void setFilteredNaprawy(List<Naprawa> filteredNaprawy) {
        this.filteredNaprawy = filteredNaprawy;
    }

    public List<Naprawa> getNaprawy() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        int klient = 0;
        //String dataNaprawy = "";

        NaprawaDao naprawaDao = new NaprawaDaoImpl();

        if (request.getParameter("zaklad") != null) {
            this.zaklad_id = parseInt(request.getParameter("zaklad"));
            request.getSession().setAttribute("zakladNaprawy", this.zaklad_id);
        }

//        if (request.getSession().getAttribute("zakladNaprawy") != null) {
//            this.zaklad_id = (int) request.getSession().getAttribute("zakladNaprawy");
//        }           
        if (request.getParameter("datanaprawy") != null) {
            this.dataNaprawy = request.getParameter("datanaprawy");
            request.getSession().setAttribute("datanaprawyNaprawa", this.dataNaprawy);
        }

        if (request.getSession().getAttribute("datanaprawyNaprawa") != null) {
            this.dataNaprawy = (String) request.getSession().getAttribute("datanaprawyNaprawa");
        }

        if (this.dataNaprawy != "") {
            SimpleDateFormat fromUser = new SimpleDateFormat("ddMMyyyy");
            SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
            String reformattedStr = "";
            try {
                reformattedStr = myFormat.format(fromUser.parse(this.dataNaprawy));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.dtNaprawy = reformattedStr;
        } else {
            this.dtNaprawy = "Wszystkie dni";
        }

        if (request.getParameter("klient") != null) {
            klient = parseInt(request.getParameter("klient"));
            request.getSession().setAttribute("klientNaprawa", klient);
        }

        if (request.getSession().getAttribute("klientNaprawa") != null) {
            klient = (int) request.getSession().getAttribute("klientNaprawa");
        }

        if (this.zaklad_id > 0 && this.dataNaprawy != "") {
            this.naprawy = naprawaDao.findAllFromZaklad(this.zaklad_id, this.dataNaprawy);
        } else if (this.zaklad_id == 0 && this.dataNaprawy != "") {
            this.naprawy = naprawaDao.findAllByDate(this.dataNaprawy);
        } else if (klient > 0) {
            this.naprawy = naprawaDao.findByKlientId(klient);
        } else {
            this.naprawy = naprawaDao.findAll();
        }

        //request.getSession().removeAttribute("zakladNaprawy");
        //request.getSession().removeAttribute("datanaprawyNaprawa");
        //zaremowano z powodu: Po dodaniu sprzedaży w kontekście klienta pojawia się sprzedaż wszystkich klientów
        //request.getSession().removeAttribute("klientNaprawa");   
        //this.filteredNaprawy = naprawy;
        return naprawy;
    }

    public boolean filterByDataNaprawy(Object value, Object filter, Locale locale) {
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

    public int getWartoscNaprawRazem() {
        int wartosc = 0;
        if (this.filteredNaprawy.isEmpty()) {
            this.filteredNaprawy = this.naprawy;
        }

        for (Naprawa n : this.filteredNaprawy) {
            wartosc += n.getWartoscNaprawy();
        }
        this.filteredNaprawy = new ArrayList<Naprawa>();
        return wartosc;
    }

    public void btnCreateNaprawa() throws IOException {
        NaprawaDao naprawaDao = new NaprawaDaoImpl();
        String msg;
        if (naprawaDao.create(this.naprawa)) {
            msg = "Rekord został utworzony";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Wystąpił błąd podczas tworzenia rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.naprawa = new Naprawa();
        //this.naprawa = new Naprawa(new Date(), UzytkownikDaoImpl.getLoggedUzytkownik().getZaklad());
        //ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        //ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    public void btnUpdateNaprawa() {
        NaprawaDao naprawaDao = new NaprawaDaoImpl();
        String msg;
        if (naprawaDao.update(this.selectedNaprawa)) {
            msg = "Rekord został zaktualizowany";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Wystąpił błąd podczas aktualizacji rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.selectedNaprawa = new Naprawa();
    }

    public void btnDeleteNaprawa() {
        NaprawaDao naprawaDao = new NaprawaDaoImpl();
        String msg;
        if (naprawaDao.delete(this.selectedNaprawa.getId())) {
            msg = "Rekord został usunięty";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Wystąpił błąd podczas usuwania rekordu";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        this.selectedNaprawa = new Naprawa();
    }

    public void clearAttributes() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().removeAttribute("zakladNaprawy");
        request.getSession().removeAttribute("datanaprawyNaprawa");
        request.getSession().removeAttribute("klientNaprawa");
        request.getSession().removeAttribute("ctxKlient");
    }
}
