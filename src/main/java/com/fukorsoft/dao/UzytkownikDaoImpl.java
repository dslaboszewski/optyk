package com.fukorsoft.dao;

import com.fukorsoft.config.DBManager;
import com.fukorsoft.entity.Uzytkownik;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

public class UzytkownikDaoImpl implements UzytkownikDao {

    public UzytkownikDaoImpl() {
    }

    @Override
    public List<Uzytkownik> findAll() {
        List<Uzytkownik> listaUzytkownikow = null;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Uzytkownik.findAll");
            listaUzytkownikow = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        em.close();
        return listaUzytkownikow;
    }
    
    @Override
    public List<Uzytkownik> findSprzedawcy() {
        List<Uzytkownik> listaUzytkownikow = null;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Uzytkownik.findSprzedawcy");
            listaUzytkownikow = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        em.close();
        return listaUzytkownikow;
    }

    @Override
    public Uzytkownik findByUzytkownik(Uzytkownik uzytkownik) {
        Uzytkownik model = null;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Uzytkownik.findByLogin");
            query.setParameter("login", uzytkownik.getLogin());
            model = (Uzytkownik) query.getSingleResult();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            model = null;
        }
        return model;
    }

    @Override
    public Uzytkownik login(Uzytkownik uzytkownik) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Uzytkownik model = this.findByUzytkownik(uzytkownik);
        if (model != null) {
            String uzytkownikHaslo = uzytkownik.getHaslo();
            String test = generatePasswordHash(uzytkownikHaslo);
            String modelHaslo = model.getHaslo();
//            if (!uzytkownikHaslo.equals(modelHaslo)) {
//                model = null;
//            }
            if (!validatePassword(uzytkownikHaslo, modelHaslo)) {
                model = null;
            }
        }
        return model;
    }

    public static Uzytkownik getLoggedUzytkownik() {
        Uzytkownik uzytkownik;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String uzytkownikLogin = (String) request.getSession().getAttribute("uzytkownik");
            Query query = em.createNamedQuery("Uzytkownik.findByLogin");
            query.setParameter("login", uzytkownikLogin);
            uzytkownik = (Uzytkownik) query.getSingleResult();
        } catch (Exception e) {
            uzytkownik = null;
            em.getTransaction().rollback();
        }
        em.close();
        return uzytkownik;
    }

    @Override
    public boolean zmienHaslo(Uzytkownik uzytkownik, String stareHaslo, String noweHaslo) {
        //czy stare hasło jest prawdziwe
        boolean wynik = false;
        String msg;
        String stareHasloUzytkownika;
        String passwordHash;
        EntityManager em = DBManager.getManager().createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Uzytkownik.findHasloUzytkownika");
            query.setParameter("login", uzytkownik.getLogin());
            stareHasloUzytkownika = (String) query.getSingleResult();
            //stareHasloUzytkownika = generatePasswordHash(stareHasloUzytkownika);
            //String sh = generatePasswordHash(stareHaslo);

            if (validatePassword(stareHaslo, stareHasloUzytkownika)) {
                passwordHash = generatePasswordHash(noweHaslo);
                
                //ustaw nowe hasło                
                uzytkownik.setHaslo(passwordHash);
                em.merge(uzytkownik);
                em.getTransaction().commit();
                wynik = true;
            } else {
                msg = "Wprowadzone obecne hasło jest nieprawidłowe.";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        em.close();
        return wynik;
    }
    
    private static String generatePasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();
         
        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }
     
    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
     
    private static String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }
    
    private static boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);
         
        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();
         
        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }
	
    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
