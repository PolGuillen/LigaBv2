package com.vasilmarkov.appjhipster.web.rest;

import com.vasilmarkov.appjhipster.Application;
import com.vasilmarkov.appjhipster.domain.Temporada;
import com.vasilmarkov.appjhipster.repository.TemporadaRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.joda.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the TemporadaResource REST controller.
 *
 * @see TemporadaResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class TemporadaResourceTest {


    private static final LocalDate DEFAULT_FECHA_TEMPORADA = new LocalDate(0L);
    private static final LocalDate UPDATED_FECHA_TEMPORADA = new LocalDate();

    @Inject
    private TemporadaRepository temporadaRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private MockMvc restTemporadaMockMvc;

    private Temporada temporada;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TemporadaResource temporadaResource = new TemporadaResource();
        ReflectionTestUtils.setField(temporadaResource, "temporadaRepository", temporadaRepository);
        this.restTemporadaMockMvc = MockMvcBuilders.standaloneSetup(temporadaResource).setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        temporada = new Temporada();
        temporada.setFecha_temporada(DEFAULT_FECHA_TEMPORADA);
    }

    @Test
    @Transactional
    public void createTemporada() throws Exception {
        int databaseSizeBeforeCreate = temporadaRepository.findAll().size();

        // Create the Temporada

        restTemporadaMockMvc.perform(post("/api/temporadas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(temporada)))
                .andExpect(status().isCreated());

        // Validate the Temporada in the database
        List<Temporada> temporadas = temporadaRepository.findAll();
        assertThat(temporadas).hasSize(databaseSizeBeforeCreate + 1);
        Temporada testTemporada = temporadas.get(temporadas.size() - 1);
        assertThat(testTemporada.getFecha_temporada()).isEqualTo(DEFAULT_FECHA_TEMPORADA);
    }

    @Test
    @Transactional
    public void checkFecha_temporadaIsRequired() throws Exception {
        int databaseSizeBeforeTest = temporadaRepository.findAll().size();
        // set the field null
        temporada.setFecha_temporada(null);

        // Create the Temporada, which fails.

        restTemporadaMockMvc.perform(post("/api/temporadas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(temporada)))
                .andExpect(status().isBadRequest());

        List<Temporada> temporadas = temporadaRepository.findAll();
        assertThat(temporadas).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTemporadas() throws Exception {
        // Initialize the database
        temporadaRepository.saveAndFlush(temporada);

        // Get all the temporadas
        restTemporadaMockMvc.perform(get("/api/temporadas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(temporada.getId().intValue())))
                .andExpect(jsonPath("$.[*].fecha_temporada").value(hasItem(DEFAULT_FECHA_TEMPORADA.toString())));
    }

    @Test
    @Transactional
    public void getTemporada() throws Exception {
        // Initialize the database
        temporadaRepository.saveAndFlush(temporada);

        // Get the temporada
        restTemporadaMockMvc.perform(get("/api/temporadas/{id}", temporada.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(temporada.getId().intValue()))
            .andExpect(jsonPath("$.fecha_temporada").value(DEFAULT_FECHA_TEMPORADA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTemporada() throws Exception {
        // Get the temporada
        restTemporadaMockMvc.perform(get("/api/temporadas/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTemporada() throws Exception {
        // Initialize the database
        temporadaRepository.saveAndFlush(temporada);

		int databaseSizeBeforeUpdate = temporadaRepository.findAll().size();

        // Update the temporada
        temporada.setFecha_temporada(UPDATED_FECHA_TEMPORADA);
        

        restTemporadaMockMvc.perform(put("/api/temporadas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(temporada)))
                .andExpect(status().isOk());

        // Validate the Temporada in the database
        List<Temporada> temporadas = temporadaRepository.findAll();
        assertThat(temporadas).hasSize(databaseSizeBeforeUpdate);
        Temporada testTemporada = temporadas.get(temporadas.size() - 1);
        assertThat(testTemporada.getFecha_temporada()).isEqualTo(UPDATED_FECHA_TEMPORADA);
    }

    @Test
    @Transactional
    public void deleteTemporada() throws Exception {
        // Initialize the database
        temporadaRepository.saveAndFlush(temporada);

		int databaseSizeBeforeDelete = temporadaRepository.findAll().size();

        // Get the temporada
        restTemporadaMockMvc.perform(delete("/api/temporadas/{id}", temporada.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Temporada> temporadas = temporadaRepository.findAll();
        assertThat(temporadas).hasSize(databaseSizeBeforeDelete - 1);
    }
}
