package com.fukorsoft.dao;

import com.fukorsoft.entity.Adres;
import com.fukorsoft.entity.Klient;
import java.util.List;

public interface KlientDao {
    public List<Klient> findAll();
    public List<Klient> findAllFromZaklad(int zaklad_id);
    public boolean create(Klient klient, Adres adres);
    //public boolean update(Klient klient, Adres adres);
    public boolean update(Klient klient);
    //public boolean delete(Klient klient);
    public boolean delete(Integer klientId);
}