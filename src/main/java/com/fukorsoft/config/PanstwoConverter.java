package com.fukorsoft.config;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import com.fukorsoft.entity.Panstwo;

@FacesConverter("panstwoConverter")
public class PanstwoConverter implements Converter {
    
    public String getAsString(FacesContext ctx, UIComponent c, Object o) {
        if (o == null)
            return "";
        if (! (o instanceof Panstwo))
            throw new ConverterException(new FacesMessage("Nie udalo sie dokonac konwersji!"));
        Panstwo p = (Panstwo)o;
        return p.getId().toString();

    }
    public Object getAsObject(FacesContext ctx, UIComponent c, String s) {
        Integer i = Integer.valueOf(s);
        EntityManager em = DBManager.getManager().createEntityManager();
        Panstwo p = em.find(Panstwo.class, i);
        em.close();
        return p;
    }
}