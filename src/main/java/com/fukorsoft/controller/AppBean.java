package com.fukorsoft.controller;

import com.fukorsoft.config.MyUtil;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class AppBean {
    public AppBean() {
    }
    
    public String getBaseUrl() {
        return MyUtil.baseurl();
    }
    
    public String getBasePath() {
        return MyUtil.basepath();
    }
}