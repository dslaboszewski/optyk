package com.fukorsoft.dao;

import com.fukorsoft.entity.Uzytkownik;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface UzytkownikDao {
    public List<Uzytkownik> findAll();
    public List<Uzytkownik> findSprzedawcy();
    public Uzytkownik findByUzytkownik(Uzytkownik uzytkownik);
    public Uzytkownik login(Uzytkownik uzytkownik) throws NoSuchAlgorithmException, InvalidKeySpecException;
    public boolean zmienHaslo(Uzytkownik uzytkownik, String stareHaslo, String noweHaslo);
}