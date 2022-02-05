package app.config;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Parent;

import io.quarkus.hibernate.orm.panache.PanacheEntity;


@Embeddable
public class PreferenceScope {
    @Column(nullable=false, length = 5)
    public String code;
    @Column(nullable=false, length = 30)
    public String prefscope;

    @JsonbTransient
    // @ManyToOne(fetch = FetchType.LAZY)
    @Parent
    public Preference pref;
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