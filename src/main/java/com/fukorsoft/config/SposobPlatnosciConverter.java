package com.fukorsoft.config;

import com.fukorsoft.entity.SposobPlatnosci;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

@FacesConverter("sposobPlatnosciConverter")
public class SposobPlatnosciConverter implements Converter {
    public String getAsString(FacesContext ctx, UIComponent c, Object o) {
        if (o == null)
            return "";
        if (! (o instanceof SposobPlatnosci))
            throw new ConverterException(new FacesMessage("Nie udalo sie dokonac konwersji!"));
        SposobPlatnosci p = (SposobPlatnosci) o;
        return p.getId().toString();

    }
    public Object getAsObject(FacesContext ctx, UIComponent c, String s) {
        Integer i = Integer.valueOf(s);
        EntityManager em = DBManager.getManager().createEntityManager();
        SposobPlatnosci p = em.find(SposobPlatnosci.class, i);
        em.close();
        return p;
    }
}