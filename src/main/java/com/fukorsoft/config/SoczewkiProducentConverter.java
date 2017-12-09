package com.fukorsoft.config;

import com.fukorsoft.entity.SoczewkiProducent;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

@FacesConverter("soczewkiProducentConverter")
public class SoczewkiProducentConverter implements Converter {
    public String getAsString(FacesContext ctx, UIComponent c, Object o) {
        if (o == null)
            return "";
        if (! (o instanceof SoczewkiProducent))
            throw new ConverterException(new FacesMessage("Nie udalo sie dokonac konwersji!"));
        SoczewkiProducent p = (SoczewkiProducent) o;
        return p.getId().toString();

    }
    public Object getAsObject(FacesContext ctx, UIComponent c, String s) {
        Integer i = Integer.valueOf(s);
        EntityManager em = DBManager.getManager().createEntityManager();
        SoczewkiProducent p = em.find(SoczewkiProducent.class, i);
        em.close();
        return p;
    }
}