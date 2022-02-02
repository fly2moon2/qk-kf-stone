package world.core;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table (name="wclang")
public class Language extends PanacheEntity {
    //public Long id;
    @Column(nullable=false, length = 3)
    public String code;
    @Column(nullable=false, length = 30)
    public String lang;

    /*public WcLang(Long id, String langCde, String lang) {
        this.id=id;
        this.langCde=langCde;
        this.lang=lang;
    }

    public WcLang() {
        
    }*/

    public static Language findByCode(String code){
        return find("code",code).firstResult();
    }
}
