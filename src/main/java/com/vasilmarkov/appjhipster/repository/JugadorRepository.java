package com.vasilmarkov.appjhipster.repository;

import com.vasilmarkov.appjhipster.domain.Jugador;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Jugador entity.
 */
public interface JugadorRepository extends JpaRepository<Jugador,Long> {

}
