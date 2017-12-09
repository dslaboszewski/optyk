package com.fukorsoft.dao;

import com.fukorsoft.config.DBManager;
import com.fukorsoft.entity.Sprzedaz;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SprzedazDaoImpl implements SprzedazDao {
    @Override
    public List<Sprzedaz> findAll() {
        List<Sprzedaz> listaSprzedazy = null;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Sprzedaz.findAll");
            listaSprzedazy = query.getResultList();
            em.getTransaction().commit();
        }
        catch (Exception e) {
            em.getTransaction().rollback();
        }        
        em.close();
        return listaSprzedazy;
    }  
    
    @Override
    public List<Sprzedaz> findAllByDate(String data_sprz) {
        List<Sprzedaz> listaSprzedazy = null;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Sprzedaz.findAllByDate");
            query.setParameter("data_sprz", data_sprz);
            listaSprzedazy = query.getResultList();
            em.getTransaction().commit();
        }
        catch (Exception e) {
            em.getTransaction().rollback();
        }        
        em.close();
        return listaSprzedazy;
    }    
    
    @Override
    public List<Sprzedaz> findAllFromZaklad(int zaklad_id, String data_sprz) {
        List<Sprzedaz> listaSprzedazy = null;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Sprzedaz.findByZakladId");
            query.setParameter("zaklad_id", zaklad_id);
            query.setParameter("data_sprz", data_sprz);
            listaSprzedazy = query.getResultList();
            em.getTransaction().commit();
        }
        catch (Exception e) {
            em.getTransaction().rollback();
        }        
        em.close();
        return listaSprzedazy;
    } 
    
    @Override
    public List<Sprzedaz> findByKlientId(int klient_id) {
        List<Sprzedaz> listaSprzedazy = null;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Sprzedaz.findByKlientId");
            query.setParameter("klient_id", klient_id);
            listaSprzedazy = query.getResultList();
            em.getTransaction().commit();
        }
        catch (Exception e) {
            em.getTransaction().rollback();
        }        
        em.close();
        return listaSprzedazy;
    } 
    
    @Override
    public long sumByZakladId(int zaklad_id, Date data_od, Date data_do) {
        long wartosc = 0;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Sprzedaz.sumByZakladId");
            query.setParameter("zaklad_id", zaklad_id);
            query.setParameter("data_od", data_od);
            query.setParameter("data_do", data_do);
            wartosc = (long) query.getSingleResult();
            em.getTransaction().commit();
        }
        catch (Exception e) {
            em.getTransaction().rollback();
        }        
        em.close();
        return wartosc;
    } 
    
    @Override
    public long sumBySprzedawcaId(int sprzedawca_id, Date data_od, Date data_do) {
        long wartosc = 0;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Sprzedaz.sumBySprzedawcaId");
            query.setParameter("sprzedawca_id", sprzedawca_id);
            query.setParameter("data_od", data_od);
            query.setParameter("data_do", data_do);
            wartosc = (long) query.getSingleResult();
            em.getTransaction().commit();
        }
        catch (Exception e) {
            em.getTransaction().rollback();
        }        
        em.close();
        return wartosc;
    } 
    
    @Override
    public boolean create(Sprzedaz sprzedaz) {
        boolean flag = false;        
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            sprzedaz.setId(null);                                         
            //sprzedaz.setSprzedawca(UzytkownikDaoImpl.getLoggedUzytkownik());       
            em.persist(sprzedaz);            
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
    public boolean update(Sprzedaz sprzedaz) {
        boolean flag = false;        
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();                    
            em.merge(sprzedaz);            
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
    public boolean delete(Integer sprzedazId) {
        boolean flag = false;        
        int result;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {                 
            em.getTransaction().begin();
            
            Query query = em.createQuery("delete from Sprzedaz where id = :sprzedazId");
            query.setParameter("sprzedazId", sprzedazId);
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