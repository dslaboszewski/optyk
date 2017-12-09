package com.fukorsoft.controller;

import com.fukorsoft.config.MyUtil;
import com.fukorsoft.dao.UzytkownikDao;
import com.fukorsoft.dao.UzytkownikDaoImpl;
import com.fukorsoft.entity.Uzytkownik;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private Uzytkownik uzytkownik;
    private transient UzytkownikDao uzytkownikDao;
    private List<Uzytkownik> uzytkownicy;

    private String stareHaslo;
    private String noweHaslo;
    private String potwNoweHaslo;

    public String getStareHaslo() {
        return stareHaslo;
    }

    public void setStareHaslo(String stareHaslo) {
        this.stareHaslo = stareHaslo;
    }

    public String getNoweHaslo() {
        return noweHaslo;
    }

    public void setNoweHaslo(String noweHaslo) {
        this.noweHaslo = noweHaslo;
    }

    public String getPotwNoweHaslo() {
        return potwNoweHaslo;
    }

    public void setPotwNoweHaslo(String potwNoweHaslo) {
        this.potwNoweHaslo = potwNoweHaslo;
    }

    public LoginBean() {
        this.uzytkownikDao = new UzytkownikDaoImpl();
        if (this.uzytkownik == null) {
            this.uzytkownik = new Uzytkownik();
        }
    }

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public UzytkownikDao getUzytkownikDao() {
        return uzytkownikDao;
    }

    public void setUzytkownikDao(UzytkownikDao uzytkownikDao) {
        this.uzytkownikDao = uzytkownikDao;
    }

    public List<Uzytkownik> getUzytkownicy() {
        UzytkownikDao uzytkownikDao = new UzytkownikDaoImpl();
        this.uzytkownicy = uzytkownikDao.findAll();
        return uzytkownicy;
    }

    public void login(ActionEvent event) throws NoSuchAlgorithmException, InvalidKeySpecException {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
        String path = "";

        this.uzytkownik = this.uzytkownikDao.login(this.uzytkownik);

        if (this.uzytkownik != null) {
            loggedIn = true;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("uzytkownik", this.uzytkownik.getLogin());
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Witaj", this.uzytkownik.getImie());
            path = MyUtil.baseurl() + MyUtil.basepath() + "index.xhtml";
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Błąd logowania", "Błędny login i/lub hasło");
            if (this.uzytkownik == null) {
                this.uzytkownik = new Uzytkownik();
            }
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("path", path);
    }

    public void logout(ActionEvent event) {
        boolean loggedOut = true;
        String path = MyUtil.baseurl();
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext facesContext = FacesContext.getCurrentInstance();

        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        session.invalidate();

        context.addCallbackParam("loggedOut", loggedOut);
        context.addCallbackParam("path", path);
    }

    public void zmienHaslo() {
        String msg;
        
        if (this.noweHaslo.equals(this.potwNoweHaslo)) {            
            UzytkownikDao uzytkownikDao = new UzytkownikDaoImpl();

            if (uzytkownikDao.zmienHaslo(this.uzytkownik, this.stareHaslo, this.noweHaslo)) {
                msg = "Hasło dla zalogowanego użytkownika zostało zmienione.";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                msg = "Wystąpił błąd podczas zmiany hasła dla zalogowanego użytkownika. Hasło dla zalogowanego użytkownika nie zostało zmienione.";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            msg = "Potwierdzenie hasła nie zgadza się z nowym hasłem. Hasło dla zalogowanego użytkownika nie zostało zmienione.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
