package app.config;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import world.core.ActiveStatus;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;


@Entity
@Table (name="akpref")
//@Table (name="akpref", uniqueConstraints={@UniqueConstraint(name="uk_akpref01",columnNames={"prefCode","prefScope","scopeSubjId"})})
//@Inheritance(strategy = InheritanceType.JOINED)
public class Preference extends PanacheEntity {
    //@ElementCollection
    //@CollectionTable(name="akprefscope")
    //public Set<PreferenceScope> scope=new HashSet<>();
    //public PreferenceCode prefCode;
    @JsonbTransient
    @ManyToOne(fetch = FetchType.LAZY)
    public PreferenceCode code;
    @Enumerated(EnumType.STRING)
    public PreferenceScope prefScope;
    public Long scopeSubjId;
    // true/false, no need to change to custom type, 'Y'/'N'
    // @org.hibernate.annotations.Type(type = "yes_no")
    @Column(nullable=false)
    public Boolean onoff;
    public Float minVal;
    public Float maxVal;
    public String parm;
    @CreationTimestamp
    public LocalDate crtdOn;
    @UpdateTimestamp
    public LocalDateTime lastUpdOn;
    

    public static Preference findByCode(PreferenceCode code){
        return find("code",code).firstResult();
    }


}
