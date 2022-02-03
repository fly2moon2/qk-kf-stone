package world.core;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

//@Embeddable
@Entity
@Table (name="wclocale")
public class Locale extends PanacheEntity {
    @Column(nullable=false, length = 3)
    public String code;
    @Column(nullable=false, length = 30)
    public String locale;

    @ManyToOne(fetch = FetchType.LAZY)
    public Language lang;
/*
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Locale locl = (Locale) o;
    return code.equals(locl.code);
}
 
@Override
public int hashCode() {
    return Objects.hash(code, locale);
}
*/
}