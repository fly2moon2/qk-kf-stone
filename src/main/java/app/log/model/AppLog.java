package app.log.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.CreationTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
//@Table (name="atapplog")
//@Table (name="akpref", uniqueConstraints={@UniqueConstraint(name="uk_akpref01",columnNames={"code_id","prefscope","forobjid"})})
//@Inheritance(strategy = InheritanceType.JOINED)
public class AppLog extends PanacheEntity {
    @Column(nullable=false, length = 1)
    @Enumerated(EnumType.STRING)
    public LogType logType;
    @Column(length = 5)
    public String appCde;
    @Column(length = 5)
    public String returnCde;
    @CreationTimestamp
    public LocalDate crtdOn;
}