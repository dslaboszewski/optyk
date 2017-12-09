package com.fukorsoft.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "naprawa")
@NamedQueries({
    @NamedQuery(name = "Naprawa.findAll", query = "SELECT n FROM Naprawa n")
    , @NamedQuery(name = "Naprawa.findById", query = "SELECT n FROM Naprawa n WHERE n.id = :id")
    , @NamedQuery(name = "Naprawa.findByOpis", query = "SELECT n FROM Naprawa n WHERE n.opis = :opis")
    , @NamedQuery(name = "Naprawa.findByDataNaprawy", query = "SELECT n FROM Naprawa n WHERE n.dataNaprawy = :dataNaprawy")
    , @NamedQuery(name = "Naprawa.findByWartoscNaprawy", query = "SELECT n FROM Naprawa n WHERE n.wartoscNaprawy = :wartoscNaprawy")
    , @NamedQuery(name = "Naprawa.findByZakladId", query = "SELECT n FROM Naprawa n WHERE n.zaklad.id = :zaklad_id AND DATE_FORMAT(n.dataNaprawy, '%d%m%Y') = :data_naprawy")
    , @NamedQuery(name = "Naprawa.findAllByDate", query = "SELECT n FROM Naprawa n WHERE DATE_FORMAT(n.dataNaprawy, '%d%m%Y') = :data_naprawy")
    , @NamedQuery(name = "Naprawa.findByKlientId", query = "SELECT n FROM Naprawa n WHERE n.klient.id = :klient_id")
})
public class Naprawa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Size(max = 4000)
    @Column(name = "opis", length = 4000)
    private String opis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_naprawy", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNaprawy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "wartosc_naprawy", nullable = false)
    private int wartoscNaprawy;
    @JoinColumn(name = "klient_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Klient klient;
    @JoinColumn(name = "naprawiajacy_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Uzytkownik naprawiajacy;
    @JoinColumn(name = "sposob_platnosci_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private SposobPlatnosci sposobPlatnosci;
    @JoinColumn(name = "typ_uslugi_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TypUslugi typUslugi;
    @JoinColumn(name = "zaklad_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Zaklad zaklad;

    public Naprawa() {
    }

    public Naprawa(Integer id) {
        this.id = id;
    }
    
    public Naprawa(Date dataNaprawy, Zaklad zaklad) {
        this.dataNaprawy = dataNaprawy;
        this.zaklad = zaklad;
    }

    public Naprawa(Integer id, Date dataNaprawy, int wartoscNaprawy) {
        this.id = id;
        this.dataNaprawy = dataNaprawy;
        this.wartoscNaprawy = wartoscNaprawy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getDataNaprawy() {
        return dataNaprawy;
    }

    public void setDataNaprawy(Date dataNaprawy) {
        this.dataNaprawy = dataNaprawy;
    }

    public int getWartoscNaprawy() {
        return wartoscNaprawy;
    }

    public void setWartoscNaprawy(int wartoscNaprawy) {
        this.wartoscNaprawy = wartoscNaprawy;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public Uzytkownik getNaprawiajacy() {
        return naprawiajacy;
    }

    public void setNaprawiajacy(Uzytkownik naprawiajacy) {
        this.naprawiajacy = naprawiajacy;
    }

    public SposobPlatnosci getSposobPlatnosci() {
        return sposobPlatnosci;
    }

    public void setSposobPlatnosci(SposobPlatnosci sposobPlatnosci) {
        this.sposobPlatnosci = sposobPlatnosci;
    }

    public TypUslugi getTypUslugi() {
        return typUslugi;
    }

    public void setTypUslugi(TypUslugi typUslugi) {
        this.typUslugi = typUslugi;
    }

    public Zaklad getZaklad() {
        return zaklad;
    }

    public void setZaklad(Zaklad zaklad) {
        this.zaklad = zaklad;
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
        if (!(object instanceof Naprawa)) {
            return false;
        }
        Naprawa other = (Naprawa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fukorsoft.entity.Naprawa[ id=" + id + " ]";
    }
    
}
