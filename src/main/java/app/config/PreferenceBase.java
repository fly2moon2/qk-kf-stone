package app.config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

//@Entity
@MappedSuperclass
public abstract class PreferenceBase extends PanacheEntity{
   // @Column(nullable=false, length = 5, unique = true)
   @Column(nullable=false, length = 5)
    public String code;
    @Column(nullable=false)
    public Boolean onoff;
    public Float minVal;
    public Float maxVal;
    public String parm;


/*     public static Preference findByCode(String code){
        return find("code",code.toUpperCase()).firstResult();
    } */
}