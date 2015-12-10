package com.vasilmarkov.appjhipster.repository;

import com.vasilmarkov.appjhipster.domain.Partido;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Partido entity.
 */
public interface PartidoRepository extends JpaRepository<Partido,Long> {

}
