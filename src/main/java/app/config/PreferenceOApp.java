package app.config;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;


@Entity
@Table (name="akprefoapp")
//@AttributeOverride(name="code",column = @Column(unique = false))
public class PreferenceOApp extends PreferenceBase {
 //   public PreferenceScope scope=new PreferenceScope();
    public Long subjectId;

}
