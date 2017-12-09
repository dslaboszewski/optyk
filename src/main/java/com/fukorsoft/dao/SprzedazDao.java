package com.fukorsoft.dao;

import com.fukorsoft.entity.Sprzedaz;
import java.util.Date;
import java.util.List;

public interface SprzedazDao {
    public List<Sprzedaz> findAll();
    public List<Sprzedaz> findAllByDate(String data_sprz);
    public List<Sprzedaz> findAllFromZaklad(int zaklad_id, String data_sprz);
    public List<Sprzedaz> findByKlientId(int klient_id);
    public long sumByZakladId(int zaklad_id, Date data_od, Date data_do);
    public long sumBySprzedawcaId(int sprzedawca_id, Date data_od, Date data_do);
    
    public boolean create(Sprzedaz sprzedaz);
    public boolean update(Sprzedaz sprzedaz);
    //public boolean delete(Klient klient);
    public boolean delete(Integer sprzedazId);
}