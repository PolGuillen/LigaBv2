package com.vasilmarkov.appjhipster.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vasilmarkov.appjhipster.domain.util.CustomDateTimeDeserializer;
import com.vasilmarkov.appjhipster.domain.util.CustomDateTimeSerializer;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A Partido.
 */
@Entity
@Table(name = "PARTIDO")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Partido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    

    @Min(value = 0)        
    @Column(name = "local_puntos")
    private Integer localPuntos;

    @Min(value = 0)        
    @Column(name = "puntos_visitante")
    private Integer puntosVisitante;

    @NotNull        
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "inicio_partido", nullable = false)
    private DateTime inicioPartido;

    @NotNull        
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "final_partido", nullable = false)
    private DateTime finalPartido;

    @ManyToOne
    private Temporada temporada;

    @ManyToOne
    private Team localTeam;

    @ManyToOne
    private Team awayTeam;

    @ManyToOne
    private Arbitro arbitro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLocalPuntos() {
        return localPuntos;
    }

    public void setLocalPuntos(Integer localPuntos) {
        this.localPuntos = localPuntos;
    }

    public Integer getPuntosVisitante() {
        return puntosVisitante;
    }

    public void setPuntosVisitante(Integer puntosVisitante) {
        this.puntosVisitante = puntosVisitante;
    }

    public DateTime getInicioPartido() {
        return inicioPartido;
    }

    public void setInicioPartido(DateTime inicioPartido) {
        this.inicioPartido = inicioPartido;
    }

    public DateTime getFinalPartido() {
        return finalPartido;
    }

    public void setFinalPartido(DateTime finalPartido) {
        this.finalPartido = finalPartido;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    public Team getLocalTeam() {
        return localTeam;
    }

    public void setLocalTeam(Team team) {
        this.localTeam = team;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team team) {
        this.awayTeam = team;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Partido partido = (Partido) o;

        if ( ! Objects.equals(id, partido.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Partido{" +
                "id=" + id +
                ", localPuntos='" + localPuntos + "'" +
                ", puntosVisitante='" + puntosVisitante + "'" +
                ", inicioPartido='" + inicioPartido + "'" +
                ", finalPartido='" + finalPartido + "'" +
                '}';
    }
}
