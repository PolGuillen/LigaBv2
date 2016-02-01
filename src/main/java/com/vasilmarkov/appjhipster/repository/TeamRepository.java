package com.vasilmarkov.appjhipster.repository;

import com.vasilmarkov.appjhipster.domain.Team;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Team entity.
 */
public interface TeamRepository extends JpaRepository<Team,Long> {


}
