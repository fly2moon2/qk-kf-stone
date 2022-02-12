package app.config;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;


@Entity
//@AttributeOverride(name="code",column = @Column(unique = false))
@Table (name="akprefoapp", uniqueConstraints={@UniqueConstraint(name="uk_akprefoapp01",columnNames={"code","prefScope","subjectId"})})
public class PreferenceOApp extends PanacheEntity {
    public PreferenceScope prefScope;
    public Long subjectId;
    @Column(nullable=false, length = 5, unique = true)
    public String code;
    @Column(nullable=false)
    public Boolean onoff;
    public Float minVal;
    public Float maxVal;
    public String parm;


}
