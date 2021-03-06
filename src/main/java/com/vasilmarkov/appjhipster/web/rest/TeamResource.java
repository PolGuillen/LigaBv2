package com.vasilmarkov.appjhipster.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.vasilmarkov.appjhipster.domain.Team;
import com.vasilmarkov.appjhipster.repository.TeamRepository;
import com.vasilmarkov.appjhipster.web.rest.util.HeaderUtil;
import com.vasilmarkov.appjhipster.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Team.
 */
@RestController
@RequestMapping("/api")
public class TeamResource {

    private final Logger log = LoggerFactory.getLogger(TeamResource.class);

    @Inject
    private TeamRepository teamRepository;

    /**
     * POST  /teams -> Create a new team.
     */
    @RequestMapping(value = "/teams",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Team> create(@RequestBody Team team) throws URISyntaxException {
        log.debug("REST request to save Team : {}", team);
        if (team.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new team cannot already have an ID").body(null);
        }
        Team result = teamRepository.save(team);
        return ResponseEntity.created(new URI("/api/teams/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("team", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /teams -> Updates an existing team.
     */
    @RequestMapping(value = "/teams",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Team> update(@RequestBody Team team) throws URISyntaxException {
        log.debug("REST request to update Team : {}", team);
        if (team.getId() == null) {
            return create(team);
        }
        Team result = teamRepository.save(team);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("team", team.getId().toString()))
                .body(result);
    }

    /**
     * GET  /teams -> get all the teams.
     */
    @RequestMapping(value = "/teams",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Team>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<Team> page = teamRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/teams", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /teams/:id -> get the "id" team.
     */
    @RequestMapping(value = "/teams/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Team> get(@PathVariable Long id) {
        log.debug("REST request to get Team : {}", id);
        return Optional.ofNullable(teamRepository.findOne(id))
            .map(team -> new ResponseEntity<>(
                team,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /teams/:id -> delete the "id" team.
     */
    @RequestMapping(value = "/teams/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete Team : {}", id);
        teamRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("team", id.toString())).build();
    }
}
