package com.fukorsoft.config;

import javax.faces.context.FacesContext;

public class MyUtil {
    public static String baseurl() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        String produkcja = ctx.getExternalContext().getInitParameter("produkcja");
        
//        if (produkcja == "1") {
//            return "http://optica-env.exissuijnp.us-east-2.elasticbeanstalk.com/Optyk/";
//        } else {
//            return "http://localhost:8080/Optyk/";
//        }
        
        return "http://optica-env.exissuijnp.us-east-2.elasticbeanstalk.com/";
    }
    
    public static String basepath() {
        return "views/";
    }
}