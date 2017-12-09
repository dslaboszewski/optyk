package com.fukorsoft.config;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import com.fukorsoft.entity.Wojewodztwo;

@FacesConverter("wojewodztwoConverter")
public class WojewodztwoConverter implements Converter {
    
    public String getAsString(FacesContext ctx, UIComponent c, Object o) {
        if (o == null)
            return "";
        if (! (o instanceof Wojewodztwo))
            throw new ConverterException(new FacesMessage("Nie udalo sie dokonac konwersji!"));
        Wojewodztwo p = (Wojewodztwo)o;
        return p.getId().toString();

    }
    public Object getAsObject(FacesContext ctx, UIComponent c, String s) {
        Integer i = Integer.valueOf(s);
        EntityManager em = DBManager.getManager().createEntityManager();
        Wojewodztwo p = em.find(Wojewodztwo.class, i);
        em.close();
        return p;
    }
}