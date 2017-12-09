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
@Table(name = "sprzedaz")
@NamedQueries({
    @NamedQuery(name = "Sprzedaz.findAll", query = "SELECT s FROM Sprzedaz s")
    , @NamedQuery(name = "Sprzedaz.findById", query = "SELECT s FROM Sprzedaz s WHERE s.id = :id")
    , @NamedQuery(name = "Sprzedaz.findByDataSprzedazy", query = "SELECT s FROM Sprzedaz s WHERE s.dataSprzedazy = :dataSprzedazy")
    , @NamedQuery(name = "Sprzedaz.findByWartoscSprzedazy", query = "SELECT s FROM Sprzedaz s WHERE s.wartoscSprzedazy = :wartoscSprzedazy")
    , @NamedQuery(name = "Sprzedaz.findByOprawaOpis", query = "SELECT s FROM Sprzedaz s WHERE s.oprawaOpis = :oprawaOpis")
    , @NamedQuery(name = "Sprzedaz.findBySzklaOpis", query = "SELECT s FROM Sprzedaz s WHERE s.szklaOpis = :szklaOpis")
    , @NamedQuery(name = "Sprzedaz.findBySoczewkiOpis", query = "SELECT s FROM Sprzedaz s WHERE s.soczewkiOpis = :soczewkiOpis")
    , @NamedQuery(name = "Sprzedaz.findByInnyTowarOpis", query = "SELECT s FROM Sprzedaz s WHERE s.innyTowarOpis = :innyTowarOpis")
    , @NamedQuery(name = "Sprzedaz.findByRozstawZrenic", query = "SELECT s FROM Sprzedaz s WHERE s.rozstawZrenic = :rozstawZrenic")
    , @NamedQuery(name = "Sprzedaz.findByOkulary", query = "SELECT s FROM Sprzedaz s WHERE s.okulary = :okulary")
    , @NamedQuery(name = "Sprzedaz.findBySoczewkiKontaktowe", query = "SELECT s FROM Sprzedaz s WHERE s.soczewkiKontaktowe = :soczewkiKontaktowe")
    , @NamedQuery(name = "Sprzedaz.findByDOpSfera", query = "SELECT s FROM Sprzedaz s WHERE s.dOpSfera = :dOpSfera")
    , @NamedQuery(name = "Sprzedaz.findByDOlSfera", query = "SELECT s FROM Sprzedaz s WHERE s.dOlSfera = :dOlSfera")
    , @NamedQuery(name = "Sprzedaz.findByBOpSfera", query = "SELECT s FROM Sprzedaz s WHERE s.bOpSfera = :bOpSfera")
    , @NamedQuery(name = "Sprzedaz.findByBOlSfera", query = "SELECT s FROM Sprzedaz s WHERE s.bOlSfera = :bOlSfera")
    , @NamedQuery(name = "Sprzedaz.findByDOpCyl", query = "SELECT s FROM Sprzedaz s WHERE s.dOpCyl = :dOpCyl")
    , @NamedQuery(name = "Sprzedaz.findByDOlCyl", query = "SELECT s FROM Sprzedaz s WHERE s.dOlCyl = :dOlCyl")
    , @NamedQuery(name = "Sprzedaz.findByBOpCyl", query = "SELECT s FROM Sprzedaz s WHERE s.bOpCyl = :bOpCyl")
    , @NamedQuery(name = "Sprzedaz.findByBOlCyl", query = "SELECT s FROM Sprzedaz s WHERE s.bOlCyl = :bOlCyl")
    , @NamedQuery(name = "Sprzedaz.findByDOpOs", query = "SELECT s FROM Sprzedaz s WHERE s.dOpOs = :dOpOs")
    , @NamedQuery(name = "Sprzedaz.findByDOlOs", query = "SELECT s FROM Sprzedaz s WHERE s.dOlOs = :dOlOs")
    , @NamedQuery(name = "Sprzedaz.findByBOpOs", query = "SELECT s FROM Sprzedaz s WHERE s.bOpOs = :bOpOs")
    , @NamedQuery(name = "Sprzedaz.findByBOlOs", query = "SELECT s FROM Sprzedaz s WHERE s.bOlOs = :bOlOs")
    , @NamedQuery(name = "Sprzedaz.findByDOpAddycja", query = "SELECT s FROM Sprzedaz s WHERE s.dOpAddycja = :dOpAddycja")
    , @NamedQuery(name = "Sprzedaz.findByDOlAddycja", query = "SELECT s FROM Sprzedaz s WHERE s.dOlAddycja = :dOlAddycja")
    , @NamedQuery(name = "Sprzedaz.findByBOpAddycja", query = "SELECT s FROM Sprzedaz s WHERE s.bOpAddycja = :bOpAddycja")
    , @NamedQuery(name = "Sprzedaz.findByBOlAddycja", query = "SELECT s FROM Sprzedaz s WHERE s.bOlAddycja = :bOlAddycja")
    , @NamedQuery(name = "Sprzedaz.findByDOpPd", query = "SELECT s FROM Sprzedaz s WHERE s.dOpPd = :dOpPd")
    , @NamedQuery(name = "Sprzedaz.findByDOlPd", query = "SELECT s FROM Sprzedaz s WHERE s.dOlPd = :dOlPd")
    , @NamedQuery(name = "Sprzedaz.findByBOpPd", query = "SELECT s FROM Sprzedaz s WHERE s.bOpPd = :bOpPd")
    , @NamedQuery(name = "Sprzedaz.findByBOlPd", query = "SELECT s FROM Sprzedaz s WHERE s.bOlPd = :bOlPd")
    , @NamedQuery(name = "Sprzedaz.findByDOpPryzma", query = "SELECT s FROM Sprzedaz s WHERE s.dOpPryzma = :dOpPryzma")
    , @NamedQuery(name = "Sprzedaz.findByDOlPryzma", query = "SELECT s FROM Sprzedaz s WHERE s.dOlPryzma = :dOlPryzma")
    , @NamedQuery(name = "Sprzedaz.findByBOpPryzma", query = "SELECT s FROM Sprzedaz s WHERE s.bOpPryzma = :bOpPryzma")
    , @NamedQuery(name = "Sprzedaz.findByBOlPryzma", query = "SELECT s FROM Sprzedaz s WHERE s.bOlPryzma = :bOlPryzma")
    , @NamedQuery(name = "Sprzedaz.findByDOpBaza", query = "SELECT s FROM Sprzedaz s WHERE s.dOpBaza = :dOpBaza")
    , @NamedQuery(name = "Sprzedaz.findByDOlBaza", query = "SELECT s FROM Sprzedaz s WHERE s.dOlBaza = :dOlBaza")
    , @NamedQuery(name = "Sprzedaz.findByBOpBaza", query = "SELECT s FROM Sprzedaz s WHERE s.bOpBaza = :bOpBaza")
    , @NamedQuery(name = "Sprzedaz.findByBOlBaza", query = "SELECT s FROM Sprzedaz s WHERE s.bOlBaza = :bOlBaza")
    , @NamedQuery(name = "Sprzedaz.findByDOpH", query = "SELECT s FROM Sprzedaz s WHERE s.dOpH = :dOpH")
    , @NamedQuery(name = "Sprzedaz.findByDOlH", query = "SELECT s FROM Sprzedaz s WHERE s.dOlH = :dOlH")
    , @NamedQuery(name = "Sprzedaz.findByBOpH", query = "SELECT s FROM Sprzedaz s WHERE s.bOpH = :bOpH")
    , @NamedQuery(name = "Sprzedaz.findByBOlH", query = "SELECT s FROM Sprzedaz s WHERE s.bOlH = :bOlH")
    , @NamedQuery(name = "Sprzedaz.findByZakladId", query = "SELECT s FROM Sprzedaz s WHERE s.zaklad.id = :zaklad_id AND DATE_FORMAT(s.dataSprzedazy, '%d%m%Y') = :data_sprz")
    , @NamedQuery(name = "Sprzedaz.findAllByDate", query = "SELECT s FROM Sprzedaz s WHERE DATE_FORMAT(s.dataSprzedazy, '%d%m%Y') = :data_sprz")
    , @NamedQuery(name = "Sprzedaz.findByKlientId", query = "SELECT s FROM Sprzedaz s WHERE s.klient.id = :klient_id")
    , @NamedQuery(name = "Sprzedaz.sumByZakladId", query = "SELECT SUM(s.wartoscSprzedazy) FROM Sprzedaz s WHERE s.zaklad.id = :zaklad_id AND DATE(s.dataSprzedazy) BETWEEN DATE(:data_od) AND DATE(:data_do)")
    , @NamedQuery(name = "Sprzedaz.sumBySprzedawcaId", query = "SELECT SUM(s.wartoscSprzedazy) FROM Sprzedaz s WHERE s.sprzedawca.id = :sprzedawca_id AND DATE(s.dataSprzedazy) BETWEEN DATE(:data_od) AND DATE(:data_do)")
})
public class Sprzedaz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_sprzedazy", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataSprzedazy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "wartosc_sprzedazy", nullable = false)
    private int wartoscSprzedazy;
    @Size(max = 4000)
    @Column(name = "oprawa_opis", length = 4000)
    private String oprawaOpis;
    @Size(max = 4000)
    @Column(name = "szkla_opis", length = 4000)
    private String szklaOpis;
    @Size(max = 4000)
    @Column(name = "soczewki_opis", length = 4000)
    private String soczewkiOpis;
    @Size(max = 4000)
    @Column(name = "inny_towar_opis", length = 4000)
    private String innyTowarOpis;
    @Column(name = "rozstaw_zrenic")
    private Short rozstawZrenic;
    @Column(name = "okulary")
    private boolean okulary;
    @Column(name = "soczewki_kontaktowe")
    private boolean soczewkiKontaktowe;
    @Size(max = 10)
    @Column(name = "d_op_sfera", length = 10)
    private String dOpSfera;
    @Size(max = 10)
    @Column(name = "d_ol_sfera", length = 10)
    private String dOlSfera;
    @Size(max = 10)
    @Column(name = "b_op_sfera", length = 10)
    private String bOpSfera;
    @Size(max = 10)
    @Column(name = "b_ol_sfera", length = 10)
    private String bOlSfera;
    @Size(max = 10)
    @Column(name = "d_op_cyl", length = 10)
    private String dOpCyl;
    @Size(max = 10)
    @Column(name = "d_ol_cyl", length = 10)
    private String dOlCyl;
    @Size(max = 10)
    @Column(name = "b_op_cyl", length = 10)
    private String bOpCyl;
    @Size(max = 10)
    @Column(name = "b_ol_cyl", length = 10)
    private String bOlCyl;
    @Size(max = 10)
    @Column(name = "d_op_os", length = 10)
    private String dOpOs;
    @Size(max = 10)
    @Column(name = "d_ol_os", length = 10)
    private String dOlOs;
    @Size(max = 10)
    @Column(name = "b_op_os", length = 10)
    private String bOpOs;
    @Size(max = 10)
    @Column(name = "b_ol_os", length = 10)
    private String bOlOs;
    @Size(max = 10)
    @Column(name = "d_op_addycja", length = 10)
    private String dOpAddycja;
    @Size(max = 10)
    @Column(name = "d_ol_addycja", length = 10)
    private String dOlAddycja;
    @Size(max = 10)
    @Column(name = "b_op_addycja", length = 10)
    private String bOpAddycja;
    @Size(max = 10)
    @Column(name = "b_ol_addycja", length = 10)
    private String bOlAddycja;
    @Size(max = 10)
    @Column(name = "d_op_pd", length = 10)
    private String dOpPd;
    @Size(max = 10)
    @Column(name = "d_ol_pd", length = 10)
    private String dOlPd;
    @Size(max = 10)
    @Column(name = "b_op_pd", length = 10)
    private String bOpPd;
    @Size(max = 10)
    @Column(name = "b_ol_pd", length = 10)
    private String bOlPd;
    @Size(max = 10)
    @Column(name = "d_op_pryzma", length = 10)
    private String dOpPryzma;
    @Size(max = 10)
    @Column(name = "d_ol_pryzma", length = 10)
    private String dOlPryzma;
    @Size(max = 10)
    @Column(name = "b_op_pryzma", length = 10)
    private String bOpPryzma;
    @Size(max = 10)
    @Column(name = "b_ol_pryzma", length = 10)
    private String bOlPryzma;
    @Size(max = 10)
    @Column(name = "d_op_baza", length = 10)
    private String dOpBaza;
    @Size(max = 10)
    @Column(name = "d_ol_baza", length = 10)
    private String dOlBaza;
    @Size(max = 10)
    @Column(name = "b_op_baza", length = 10)
    private String bOpBaza;
    @Size(max = 10)
    @Column(name = "b_ol_baza", length = 10)
    private String bOlBaza;
    @Size(max = 10)
    @Column(name = "d_op_h", length = 10)
    private String dOpH;
    @Size(max = 10)
    @Column(name = "d_ol_h", length = 10)
    private String dOlH;
    @Size(max = 10)
    @Column(name = "b_op_h", length = 10)
    private String bOpH;
    @Size(max = 10)
    @Column(name = "b_ol_h", length = 10)
    private String bOlH;
    @JoinColumn(name = "inny_towar_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private InnyTowar innyTowar;
    @JoinColumn(name = "klient_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Klient klient;
    @JoinColumn(name = "oprawa_producent_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private OprawaProducent oprawaProducent;
    @JoinColumn(name = "soczewki_producent_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private SoczewkiProducent soczewkiProducent;
    @JoinColumn(name = "sposob_platnosci_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private SposobPlatnosci sposobPlatnosci;
    @JoinColumn(name = "sprzedawca_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Uzytkownik sprzedawca;
    @JoinColumn(name = "szkla_producent_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private SzklaProducent szklaProducent;
    @JoinColumn(name = "zaklad_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Zaklad zaklad;

    public Sprzedaz() {
    }

    public Sprzedaz(Integer id) {
        this.id = id;
    }
    
    public Sprzedaz(Uzytkownik sprzedawca) {
        this.sprzedawca = sprzedawca;
        this.dataSprzedazy = new Date();
    }
    
    public Sprzedaz(Date dataSprzedazy, Zaklad zaklad) {
        this.dataSprzedazy = dataSprzedazy;
        this.zaklad = zaklad;
    }

    public Sprzedaz(Integer id, Date dataSprzedazy, int wartoscSprzedazy) {
        this.id = id;
        this.dataSprzedazy = dataSprzedazy;
        this.wartoscSprzedazy = wartoscSprzedazy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataSprzedazy() {
        return dataSprzedazy;
    }

    public void setDataSprzedazy(Date dataSprzedazy) {
        this.dataSprzedazy = dataSprzedazy;
    }

    public int getWartoscSprzedazy() {
        return wartoscSprzedazy;
    }

    public void setWartoscSprzedazy(int wartoscSprzedazy) {
        this.wartoscSprzedazy = wartoscSprzedazy;
    }

    public String getOprawaOpis() {
        return oprawaOpis;
    }

    public void setOprawaOpis(String oprawaOpis) {
        this.oprawaOpis = oprawaOpis;
    }

    public String getSzklaOpis() {
        return szklaOpis;
    }

    public void setSzklaOpis(String szklaOpis) {
        this.szklaOpis = szklaOpis;
    }

    public String getSoczewkiOpis() {
        return soczewkiOpis;
    }

    public void setSoczewkiOpis(String soczewkiOpis) {
        this.soczewkiOpis = soczewkiOpis;
    }

    public String getInnyTowarOpis() {
        return innyTowarOpis;
    }

    public void setInnyTowarOpis(String innyTowarOpis) {
        this.innyTowarOpis = innyTowarOpis;
    }

    public Short getRozstawZrenic() {
        return rozstawZrenic;
    }

    public void setRozstawZrenic(Short rozstawZrenic) {
        this.rozstawZrenic = rozstawZrenic;
    }

    public boolean getOkulary() {
        return okulary;
    }

    public void setOkulary(boolean okulary) {
        this.okulary = okulary;
    }

    public boolean getSoczewkiKontaktowe() {
        return soczewkiKontaktowe;
    }

    public void setSoczewkiKontaktowe(boolean soczewkiKontaktowe) {
        this.soczewkiKontaktowe = soczewkiKontaktowe;
    }

    public String getDOpSfera() {
        return dOpSfera;
    }

    public void setDOpSfera(String dOpSfera) {
        this.dOpSfera = dOpSfera;
    }

    public String getDOlSfera() {
        return dOlSfera;
    }

    public void setDOlSfera(String dOlSfera) {
        this.dOlSfera = dOlSfera;
    }

    public String getBOpSfera() {
        return bOpSfera;
    }

    public void setBOpSfera(String bOpSfera) {
        this.bOpSfera = bOpSfera;
    }

    public String getBOlSfera() {
        return bOlSfera;
    }

    public void setBOlSfera(String bOlSfera) {
        this.bOlSfera = bOlSfera;
    }

    public String getDOpCyl() {
        return dOpCyl;
    }

    public void setDOpCyl(String dOpCyl) {
        this.dOpCyl = dOpCyl;
    }

    public String getDOlCyl() {
        return dOlCyl;
    }

    public void setDOlCyl(String dOlCyl) {
        this.dOlCyl = dOlCyl;
    }

    public String getBOpCyl() {
        return bOpCyl;
    }

    public void setBOpCyl(String bOpCyl) {
        this.bOpCyl = bOpCyl;
    }

    public String getBOlCyl() {
        return bOlCyl;
    }

    public void setBOlCyl(String bOlCyl) {
        this.bOlCyl = bOlCyl;
    }

    public String getDOpOs() {
        return dOpOs;
    }

    public void setDOpOs(String dOpOs) {
        this.dOpOs = dOpOs;
    }

    public String getDOlOs() {
        return dOlOs;
    }

    public void setDOlOs(String dOlOs) {
        this.dOlOs = dOlOs;
    }

    public String getBOpOs() {
        return bOpOs;
    }

    public void setBOpOs(String bOpOs) {
        this.bOpOs = bOpOs;
    }

    public String getBOlOs() {
        return bOlOs;
    }

    public void setBOlOs(String bOlOs) {
        this.bOlOs = bOlOs;
    }

    public String getDOpAddycja() {
        return dOpAddycja;
    }

    public void setDOpAddycja(String dOpAddycja) {
        this.dOpAddycja = dOpAddycja;
    }

    public String getDOlAddycja() {
        return dOlAddycja;
    }

    public void setDOlAddycja(String dOlAddycja) {
        this.dOlAddycja = dOlAddycja;
    }

    public String getBOpAddycja() {
        return bOpAddycja;
    }

    public void setBOpAddycja(String bOpAddycja) {
        this.bOpAddycja = bOpAddycja;
    }

    public String getBOlAddycja() {
        return bOlAddycja;
    }

    public void setBOlAddycja(String bOlAddycja) {
        this.bOlAddycja = bOlAddycja;
    }

    public String getDOpPd() {
        return dOpPd;
    }

    public void setDOpPd(String dOpPd) {
        this.dOpPd = dOpPd;
    }

    public String getDOlPd() {
        return dOlPd;
    }

    public void setDOlPd(String dOlPd) {
        this.dOlPd = dOlPd;
    }

    public String getBOpPd() {
        return bOpPd;
    }

    public void setBOpPd(String bOpPd) {
        this.bOpPd = bOpPd;
    }

    public String getBOlPd() {
        return bOlPd;
    }

    public void setBOlPd(String bOlPd) {
        this.bOlPd = bOlPd;
    }

    public String getDOpPryzma() {
        return dOpPryzma;
    }

    public void setDOpPryzma(String dOpPryzma) {
        this.dOpPryzma = dOpPryzma;
    }

    public String getDOlPryzma() {
        return dOlPryzma;
    }

    public void setDOlPryzma(String dOlPryzma) {
        this.dOlPryzma = dOlPryzma;
    }

    public String getBOpPryzma() {
        return bOpPryzma;
    }

    public void setBOpPryzma(String bOpPryzma) {
        this.bOpPryzma = bOpPryzma;
    }

    public String getBOlPryzma() {
        return bOlPryzma;
    }

    public void setBOlPryzma(String bOlPryzma) {
        this.bOlPryzma = bOlPryzma;
    }

    public String getDOpBaza() {
        return dOpBaza;
    }

    public void setDOpBaza(String dOpBaza) {
        this.dOpBaza = dOpBaza;
    }

    public String getDOlBaza() {
        return dOlBaza;
    }

    public void setDOlBaza(String dOlBaza) {
        this.dOlBaza = dOlBaza;
    }

    public String getBOpBaza() {
        return bOpBaza;
    }

    public void setBOpBaza(String bOpBaza) {
        this.bOpBaza = bOpBaza;
    }

    public String getBOlBaza() {
        return bOlBaza;
    }

    public void setBOlBaza(String bOlBaza) {
        this.bOlBaza = bOlBaza;
    }

    public String getDOpH() {
        return dOpH;
    }

    public void setDOpH(String dOpH) {
        this.dOpH = dOpH;
    }

    public String getDOlH() {
        return dOlH;
    }

    public void setDOlH(String dOlH) {
        this.dOlH = dOlH;
    }

    public String getBOpH() {
        return bOpH;
    }

    public void setBOpH(String bOpH) {
        this.bOpH = bOpH;
    }

    public String getBOlH() {
        return bOlH;
    }

    public void setBOlH(String bOlH) {
        this.bOlH = bOlH;
    }

    public InnyTowar getInnyTowar() {
        return innyTowar;
    }

    public void setInnyTowar(InnyTowar innyTowar) {
        this.innyTowar = innyTowar;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public OprawaProducent getOprawaProducent() {
        return oprawaProducent;
    }

    public void setOprawaProducent(OprawaProducent oprawaProducent) {
        this.oprawaProducent = oprawaProducent;
    }

    public SoczewkiProducent getSoczewkiProducent() {
        return soczewkiProducent;
    }

    public void setSoczewkiProducent(SoczewkiProducent soczewkiProducent) {
        this.soczewkiProducent = soczewkiProducent;
    }

    public SposobPlatnosci getSposobPlatnosci() {
        return sposobPlatnosci;
    }

    public void setSposobPlatnosci(SposobPlatnosci sposobPlatnosci) {
        this.sposobPlatnosci = sposobPlatnosci;
    }

    public Uzytkownik getSprzedawca() {
        return sprzedawca;
    }

    public void setSprzedawca(Uzytkownik sprzedawca) {
        this.sprzedawca = sprzedawca;
    }

    public SzklaProducent getSzklaProducent() {
        return szklaProducent;
    }

    public void setSzklaProducent(SzklaProducent szklaProducent) {
        this.szklaProducent = szklaProducent;
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
        if (!(object instanceof Sprzedaz)) {
            return false;
        }
        Sprzedaz other = (Sprzedaz) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fukorsoft.entity.Sprzedaz[ id=" + id + " ]";
    }   
}