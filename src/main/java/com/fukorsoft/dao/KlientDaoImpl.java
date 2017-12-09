package com.fukorsoft.dao;

import com.fukorsoft.config.DBManager;
import com.fukorsoft.entity.Adres;
import com.fukorsoft.entity.Klient;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class KlientDaoImpl implements KlientDao {

    @Override
    public List<Klient> findAll() {
        List<Klient> listaKlientow = null;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Klient.findAll");
            listaKlientow = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        em.close();
        return listaKlientow;
    }

    @Override
    public List<Klient> findAllFromZaklad(int zaklad_id) {
        List<Klient> listaKlientow = null;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Klient.findByZakladId");
            query.setParameter("zaklad_id", zaklad_id);
            listaKlientow = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        em.close();
        return listaKlientow;
    }

    @Override
    public boolean create(Klient klient, Adres adres) {
        boolean flag = false;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            klient.setId(null);
            adres.setId(null);
            Set<Adres> adresSet = new HashSet<>();
            adres.setKlient(klient);
            adresSet.add(adres);
            klient.setAdresSet(adresSet);
            klient.setUzytkownik(UzytkownikDaoImpl.getLoggedUzytkownik());
            em.persist(klient);
            em.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            em.getTransaction().rollback();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        em.close();
        return flag;
    }

    @Override
    public boolean update(Klient klient) {
        boolean flag = false;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
//            Set<Adres> adresSet = new HashSet<>();
//            adres.setKlient(klient);
//            adresSet.add(adres);        
//            klient.getAdresSet().clear();
//            klient.setAdresSet(adresSet);      
            //Adres adres = (Adres) klient.getAdresSet().toArray()[0];
            //em.merge(adres);
            em.merge(klient);
            em.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            em.getTransaction().rollback();
        }
        em.close();
        return flag;
    }

//    @Override
//    public boolean delete(Klient klient) {
//        boolean flag = false;        
//        EntityManager em = DBManager.getManager().createEntityManager();
//        try {
//            em.getTransaction().begin();       
//            //Adres adres = (Adres) klient.getAdresSet().toArray()[0];
//            //em.remove(adres);
//            //em.remove(klient);            
//            em.remove(em.contains(klient) ? klient : em.merge(klient));
//            em.getTransaction().commit();
//            flag = true;
//        }
//        catch (Exception e) {
//            flag = false;
//            em.getTransaction().rollback();            
//        }         
//        return flag;
//    }
    @Override
    public boolean delete(Integer klientId) {
        boolean flag = false;
        int result;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            //Klient klient = em.find(Klient.class, klientId);            
//            Query query = em.createNamedQuery("Klient.findById");
//            query.setParameter("id", klientId);
//            Klient klient = (Klient) query.getSingleResult();                                    
//            if (klient != null) {
//                em.getTransaction().begin();  
//                em.remove(klient);
//                klient = new Klient();
//                em.getTransaction().commit();
//                em.close();
//                flag = true;
//            }

            em.getTransaction().begin();

            Query query = em.createQuery("delete Adres where klient_id = :klientId");
            query.setParameter("klientId", klientId);
            result = query.executeUpdate();

            query = em.createQuery("delete Klient where id = :klientId");
            query.setParameter("klientId", klientId);
            result = query.executeUpdate();

            em.getTransaction().commit();

            if (result > 0) {
                flag = true;
            }
        } catch (Exception e) {
            flag = false;
            em.getTransaction().rollback();
        }
        em.close();
        return flag;
    }
}
