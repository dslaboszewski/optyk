package com.fukorsoft.dao;

import com.fukorsoft.entity.Naprawa;
import java.util.List;

public interface NaprawaDao {
    public List<Naprawa> findAll();
    public List<Naprawa> findAllByDate(String data_naprawy);
    public List<Naprawa> findAllFromZaklad(int zaklad_id, String data_naprawy);
    public List<Naprawa> findByKlientId(int klient_id);
    
    public boolean create(Naprawa naprawa);
    public boolean update(Naprawa naprawa);
    //public boolean delete(Klient klient);
    public boolean delete(Integer naprawaId);
}