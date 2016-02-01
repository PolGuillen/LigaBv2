package com.vasilmarkov.appjhipster.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vasilmarkov.appjhipster.domain.util.CustomLocalDateSerializer;
import com.vasilmarkov.appjhipster.domain.util.ISO8601LocalDateDeserializer;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A Team.
 */
@Entity
@Table(name = "TEAM")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "localidad")
    private String localidad;
    
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @JsonDeserialize(using = ISO8601LocalDateDeserializer.class)
    @Column(name = "fecha_creacion")
    private LocalDate fecha_creacion;

    @OneToOne
    private Entrenador entrenador;

    @OneToOne
    private Estadio estadio;

    @ManyToMany(mappedBy = "equiposAfiliadoss")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Socio> sociosAfiliadoss = new HashSet<>();

    @ManyToMany(mappedBy = "equiposActualess")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Temporada> teporadaActuals = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public Set<Socio> getSociosAfiliadoss() {
        return sociosAfiliadoss;
    }

    public void setSociosAfiliadoss(Set<Socio> socios) {
        this.sociosAfiliadoss = socios;
    }

    public Set<Temporada> getTeporadaActuals() {
        return teporadaActuals;
    }

    public void setTeporadaActuals(Set<Temporada> temporadas) {
        this.teporadaActuals = temporadas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Team team = (Team) o;

        if ( ! Objects.equals(id, team.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", nombre='" + nombre + "'" +
                ", localidad='" + localidad + "'" +
                ", fecha_creacion='" + fecha_creacion + "'" +
                '}';
    }
}
