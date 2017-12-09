package com.fukorsoft.config;

import com.fukorsoft.entity.Klient;
import com.fukorsoft.entity.Uzytkownik;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

@FacesConverter("uzytkownikConverter")
public class UzytkownikConverter implements Converter {
    
    public String getAsString(FacesContext ctx, UIComponent c, Object o) {
        if (o == null)
            return "";
        if (! (o instanceof Uzytkownik))
            throw new ConverterException(new FacesMessage("Nie udalo sie dokonac konwersji!"));
        Uzytkownik p = (Uzytkownik) o;
        return p.getId().toString();

    }
    public Object getAsObject(FacesContext ctx, UIComponent c, String s) {
        Integer i = Integer.valueOf(s);
        EntityManager em = DBManager.getManager().createEntityManager();
        Uzytkownik p = em.find(Uzytkownik.class, i);
        em.close();
        return p;
    }
}