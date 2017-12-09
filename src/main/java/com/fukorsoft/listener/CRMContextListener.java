package com.fukorsoft.listener;

import com.fukorsoft.config.DBManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CRMContextListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DBManager.getManager().createEntityManagerFactory();
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DBManager.getManager().closeEntityManagerFactory();
    }
}