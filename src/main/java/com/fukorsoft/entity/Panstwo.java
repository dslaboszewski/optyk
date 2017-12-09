package com.fukorsoft.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "panstwo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Panstwo.findAll", query = "SELECT p FROM Panstwo p")
    , @NamedQuery(name = "Panstwo.findById", query = "SELECT p FROM Panstwo p WHERE p.id = :id")
    , @NamedQuery(name = "Panstwo.findByNazwa", query = "SELECT p FROM Panstwo p WHERE p.nazwa = :nazwa")})
public class Panstwo implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "panstwo", fetch = FetchType.EAGER)
    private Set<Wojewodztwo> wojewodztwoSet;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nazwa", nullable = false, length = 50)
    private String nazwa;
    @OneToMany(mappedBy = "panstwo", fetch = FetchType.EAGER)
    private Set<Adres> adresSet;

    public Panstwo() {
    }

    public Panstwo(Integer id) {
        this.id = id;
    }

    public Panstwo(Integer id, String nazwa) {
        this.id = id;
        this.nazwa = nazwa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @XmlTransient
    public Set<Adres> getAdresSet() {
        return adresSet;
    }

    public void setAdresSet(Set<Adres> adresSet) {
        this.adresSet = adresSet;
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
        if (!(object instanceof Panstwo)) {
            return false;
        }
        Panstwo other = (Panstwo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fukorsoft.klient.entity.Panstwo[ id=" + id + " ]";
    }

    public Set<Wojewodztwo> getWojewodztwoSet() {
        return wojewodztwoSet;
    }

    public void setWojewodztwoSet(Set<Wojewodztwo> wojewodztwoSet) {
        this.wojewodztwoSet = wojewodztwoSet;
    }
    
}
