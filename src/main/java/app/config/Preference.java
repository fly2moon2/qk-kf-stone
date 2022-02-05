package app.config;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;


@Entity
@Table (name="akpref")
public class Preference extends PanacheEntity {
    @ElementCollection
    @CollectionTable(name="akprefscope")
    public Set<PreferenceScope> scope=new HashSet<>();
    public Long subjectId;
    @Column(nullable=false, length = 5)
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
