package app.config;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.InheritanceType;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;


@Entity
@Table (name="akpref")
//@Table (name="akpref", uniqueConstraints={@UniqueConstraint(name="uk_akpref01",columnNames={"code"})})
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Preference extends PanacheEntity {
    //@ElementCollection
    //@CollectionTable(name="akprefscope")
    //public Set<PreferenceScope> scope=new HashSet<>();
    @Column(nullable=false, length = 5, unique = true)
    public String code;
    @Column(nullable=false, length = 50)
    public String dscrp;
    @Column(nullable=false)
    public Boolean onoff;
    public Float minVal;
    public Float maxVal;
    public String parm;


    public static Preference findByCode(String code){
        return find("code",code.toUpperCase()).firstResult();
    }


}
