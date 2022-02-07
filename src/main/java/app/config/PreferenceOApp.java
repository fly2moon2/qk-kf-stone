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
@Table (name="akprefoapp")
public class PreferenceOApp extends PanacheEntity {
 //   public PreferenceScope scope=new PreferenceScope();
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


    public static PreferenceOApp findByCode(String code){
        return find("code",code.toUpperCase()).firstResult();
    }


}
