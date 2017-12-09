package com.fukorsoft.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "adres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adres.findAll", query = "SELECT a FROM Adres a")
    , @NamedQuery(name = "Adres.findById", query = "SELECT a FROM Adres a WHERE a.id = :id")
    , @NamedQuery(name = "Adres.findByUlica", query = "SELECT a FROM Adres a WHERE a.ulica = :ulica")
    , @NamedQuery(name = "Adres.findByNrBudynku", query = "SELECT a FROM Adres a WHERE a.nrBudynku = :nrBudynku")
    , @NamedQuery(name = "Adres.findByNrLokalu", query = "SELECT a FROM Adres a WHERE a.nrLokalu = :nrLokalu")
    , @NamedQuery(name = "Adres.findByKodPocztowy", query = "SELECT a FROM Adres a WHERE a.kodPocztowy = :kodPocztowy")})
public class Adres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 100)
    @Column(name = "ulica", length = 100)
    private String ulica;
    @Size(max = 10)
    @Column(name = "nr_budynku", length = 10)
    private String nrBudynku;
    @Size(max = 10)
    @Column(name = "nr_lokalu", length = 10)
    private String nrLokalu;
    @Size(max = 10)
    @Column(name = "kod_pocztowy", length = 10)
    private String kodPocztowy;
    @JoinColumn(name = "klient_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Klient klient;
    @JoinColumn(name = "miejscowosc_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Miejscowosc miejscowosc;
    @JoinColumn(name = "panstwo_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Panstwo panstwo;
    @JoinColumn(name = "wojewodztwo_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Wojewodztwo wojewodztwo;

    public Adres() {
    }

    public Adres(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNrBudynku() {
        return nrBudynku;
    }

    public void setNrBudynku(String nrBudynku) {
        this.nrBudynku = nrBudynku;
    }

    public String getNrLokalu() {
        return nrLokalu;
    }

    public void setNrLokalu(String nrLokalu) {
        this.nrLokalu = nrLokalu;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public Miejscowosc getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(Miejscowosc miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public Panstwo getPanstwo() {
        return panstwo;
    }

    public void setPanstwo(Panstwo panstwo) {
        this.panstwo = panstwo;
    }

    public Wojewodztwo getWojewodztwo() {
        return wojewodztwo;
    }

    public void setWojewodztwo(Wojewodztwo wojewodztwo) {
        this.wojewodztwo = wojewodztwo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adres)) {
            return false;
        }
        Adres other = (Adres) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fukorsoft.klient.entity.Adres[ id=" + id + " ]";
    }
    
}