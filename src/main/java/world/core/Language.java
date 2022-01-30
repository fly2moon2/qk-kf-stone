package world.core;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name="wclang")
public class Language extends PanacheEntity {
    //public Long id;
    public String langCde;
    public String lang;

    /*public WcLang(Long id, String langCde, String lang) {
        this.id=id;
        this.langCde=langCde;
        this.lang=lang;
    }

    public WcLang() {
        
    }*/

    public static Language findByCde(String langCde){
        return find("langCde",langCde).firstResult();
    }
}
