package com.fukorsoft.config;

import com.fukorsoft.entity.InnyTowar;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

@FacesConverter("innyTowarConverter")
public class InnyTowarConverter implements Converter {
    
    public String getAsString(FacesContext ctx, UIComponent c, Object o) {
        if (o == null)
            return "";
        if (! (o instanceof InnyTowar))
            throw new ConverterException(new FacesMessage("Nie udalo sie dokonac konwersji!"));
        InnyTowar p = (InnyTowar)o;
        return p.getId().toString();

    }
    public Object getAsObject(FacesContext ctx, UIComponent c, String s) {
        Integer i = Integer.valueOf(s);
        EntityManager em = DBManager.getManager().createEntityManager();
        InnyTowar p = em.find(InnyTowar.class, i);
        em.close();
        return p;
    }
}