package com.fukorsoft.dao;

import com.fukorsoft.config.DBManager;
import com.fukorsoft.entity.Naprawa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class NaprawaDaoImpl implements NaprawaDao {
    
    @Override
    public List<Naprawa> findAll() {
        List<Naprawa> listaNapraw = null;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Naprawa.findAll");
            listaNapraw = query.getResultList();
            em.getTransaction().commit();
        }
        catch (Exception e) {
            em.getTransaction().rollback();
        }        
        em.close();
        return listaNapraw;
    }  
    
    @Override
    public List<Naprawa> findAllByDate(String data_naprawy) {
        List<Naprawa> listaNapraw = null;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Naprawa.findAllByDate");
            query.setParameter("data_naprawy", data_naprawy);
            listaNapraw = query.getResultList();
            em.getTransaction().commit();
        }
        catch (Exception e) {
            em.getTransaction().rollback();
        }        
        em.close();
        return listaNapraw;
    }    
    
    @Override
    public List<Naprawa> findAllFromZaklad(int zaklad_id, String data_naprawy) {
        List<Naprawa> listaNapraw = null;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Naprawa.findByZakladId");
            query.setParameter("zaklad_id", zaklad_id);
            query.setParameter("data_naprawy", data_naprawy);
            listaNapraw = query.getResultList();
            em.getTransaction().commit();
        }
        catch (Exception e) {
            em.getTransaction().rollback();
        }        
        em.close();
        return listaNapraw;
    }
    
    @Override
    public List<Naprawa> findByKlientId(int klient_id) {
        List<Naprawa> listaNapraw = null;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Naprawa.findByKlientId");
            query.setParameter("klient_id", klient_id);
            listaNapraw = query.getResultList();
            em.getTransaction().commit();
        }
        catch (Exception e) {
            em.getTransaction().rollback();
        }        
        em.close();
        return listaNapraw;
    } 
    
    @Override
    public boolean create(Naprawa naprawa) {
        boolean flag = false;        
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            naprawa.setId(null);                                         
            //naprawa.setNaprawiajacy(UzytkownikDaoImpl.getLoggedUzytkownik());       
            em.persist(naprawa);            
            em.getTransaction().commit();
            flag = true;
        }
        catch (Exception e) {
            flag = false;
            em.getTransaction().rollback();            
        }    
        em.close();
        return flag;
    }
    
    @Override
    public boolean update(Naprawa naprawa) {
        boolean flag = false;        
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();                    
            em.merge(naprawa);            
            em.getTransaction().commit();
            flag = true;
        }
        catch (Exception e) {
            flag = false;
            em.getTransaction().rollback();            
        }       
        em.close();
        return flag;
    }
    
    @Override
    public boolean delete(Integer naprawaId) {
        boolean flag = false;        
        int result;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {                 
            em.getTransaction().begin();
            
            Query query = em.createQuery("delete from Naprawa where id = :naprawaId");
            query.setParameter("naprawaId", naprawaId);
            result = query.executeUpdate();            
            
            em.getTransaction().commit();

            if (result > 0) {
                flag = true;
            }
        }
        catch (Exception e) {
            flag = false;
            em.getTransaction().rollback();            
        }         
        em.close();
        return flag;
    }
}