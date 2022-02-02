package world.core;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table (name="wclocale")
public class Locale extends PanacheEntity {
 
    public String loclCde;
    public String locale;


    public static Locale findByCde(String loclCde){
        return find("loclCde",loclCde).firstResult();
    }
}
