package app.config;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import world.core.ActiveStatus;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

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
    public PreferenceScope prefScope;
    public Long scopeSubjId;
    @Column(nullable=false)
    public Boolean onoff;
    public Float minVal;
    public Float maxVal;
    public String parm;
    


/*     public static Preference findByCode(String code){
        return find("code",code.toUpperCase()).firstResult();
    } */


}
