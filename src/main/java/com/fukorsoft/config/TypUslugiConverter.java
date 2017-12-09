package com.fukorsoft.config;

import com.fukorsoft.entity.TypUslugi;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

@FacesConverter("typUslugiConverter")
public class TypUslugiConverter implements Converter {
    public String getAsString(FacesContext ctx, UIComponent c, Object o) {
        if (o == null)
            return "";
        if (! (o instanceof TypUslugi))
            throw new ConverterException(new FacesMessage("Nie udalo sie dokonac konwersji!"));
        TypUslugi p = (TypUslugi) o;
        return p.getId().toString();

    }
    public Object getAsObject(FacesContext ctx, UIComponent c, String s) {
        Integer i = Integer.valueOf(s);
        EntityManager em = DBManager.getManager().createEntityManager();
        TypUslugi p = em.find(TypUslugi.class, i);
        em.close();
        return p;
    }
}