package com.example.projetsem2qrcode.controlleradmin;

import com.example.projetsem2qrcode.exceptions.EtudiantInnexistantException;
import com.example.projetsem2qrcode.exceptions.NumEtudiantDejaPresentException;
import com.example.projetsem2qrcode.exceptions.NumEtudiantNonValideException;
import com.example.projetsem2qrcode.modele.Etudiant;
import com.example.projetsem2qrcode.service.EtudiantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {EtudiantController.class})
@ExtendWith(SpringExtension.class)
class EtudiantControllerTest {

    @Autowired
    private EtudiantController etudiantController;

    @MockBean
    private EtudiantService etudiantService;

    /**
     * Method under test: {@link EtudiantController#deleteAllEtudiant()}
     */
    @Test
    void testDeleteAllEtudiant() throws Exception {
        doNothing().when(this.etudiantService).deleteAllEtudiant();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/etudiants");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.etudiantController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link EtudiantController#deleteAllEtudiant()}
     */
    @Test
    void testDeleteAllEtudiant2() throws Exception {
        doNothing().when(this.etudiantService).deleteAllEtudiant();
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/etudiants");
        deleteResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.etudiantController)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link EtudiantController#reinitEmargement()}
     */
    @Test
    void testReinitEmargement() throws Exception {
        doNothing().when(this.etudiantService).reinitEmargement();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/etudiants");
        MockMvcBuilders.standaloneSetup(this.etudiantController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link EtudiantController#reinitEmargement()}
     */
    @Test
    void testReinitEmargement2() throws Exception {
        doNothing().when(this.etudiantService).reinitEmargement();
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/api/etudiants");
        putResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.etudiantController)
                .build()
                .perform(putResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link EtudiantController#createEtudiant(Etudiant)}
     */
    @Test
    void testCreateEtudiant() throws Exception {

        Etudiant etudiant = new Etudiant();
        etudiant.setEmargement(true);
        etudiant.setGroupeTp("Groupe Tp");
        etudiant.setId("42");
        etudiant.setNom("Nom");
        etudiant.setNumEtudiant("Num Etudiant");
        etudiant.setPrenom("Prenom");

        when(this.etudiantService.saveEtudiant((Etudiant) any())).thenReturn(etudiant);

        Etudiant etudiant1 = new Etudiant();
        etudiant1.setEmargement(true);
        etudiant1.setGroupeTp("Groupe Tp");
        etudiant1.setId("42");
        etudiant1.setNom("Nom");
        etudiant1.setNumEtudiant("Num Etudiant");
        etudiant1.setPrenom("Prenom");
        String content = (new ObjectMapper()).writeValueAsString(etudiant1);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/etudiants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.etudiantController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"numEtudiant\":\"Num Etudiant\",\"groupeTp\":\"Groupe Tp\",\"emargement"
                                        + "\":true}"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/api/etudiants/Num%20Etudiant"));
    }

    /**
     * Method under test: {@link EtudiantController#createEtudiant(Etudiant)}
     */
    @Test
    void testCreateEtudiant2() throws Exception {
        when(this.etudiantService.saveEtudiant((Etudiant) any())).thenThrow(new NumEtudiantDejaPresentException());

        Etudiant etudiant = new Etudiant();
        etudiant.setEmargement(true);
        etudiant.setGroupeTp("Groupe Tp");
        etudiant.setId("42");
        etudiant.setNom("Nom");
        etudiant.setNumEtudiant("Num Etudiant");
        etudiant.setPrenom("Prenom");
        String content = (new ObjectMapper()).writeValueAsString(etudiant);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/etudiants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.etudiantController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link EtudiantController#createEtudiant(Etudiant)}
     */
    @Test
    void testCreateEtudiant3() throws Exception {
        when(this.etudiantService.saveEtudiant((Etudiant) any())).thenThrow(new NumEtudiantNonValideException());

        Etudiant etudiant = new Etudiant();
        etudiant.setEmargement(true);
        etudiant.setGroupeTp("Groupe Tp");
        etudiant.setId("42");
        etudiant.setNom("Nom");
        etudiant.setNumEtudiant("Num Etudiant");
        etudiant.setPrenom("Prenom");
        String content = (new ObjectMapper()).writeValueAsString(etudiant);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/etudiants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.etudiantController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link EtudiantController#deleteEtudiantByNumEtu(String)}
     */
    @Test
    void testDeleteEtudiantByNumEtu() throws Exception {
        doNothing().when(this.etudiantService).deleteEtudiantByNumEtudiant((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/etudiants/{numEtudiant}",
                "Num Etudiant");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.etudiantController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link EtudiantController#deleteEtudiantByNumEtu(String)}
     */
    @Test
    void testDeleteEtudiantByNumEtu2() throws Exception {
        doNothing().when(this.etudiantService).deleteEtudiantByNumEtudiant((String) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/etudiants/{numEtudiant}",
                "Num Etudiant");
        deleteResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.etudiantController)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link EtudiantController#deleteEtudiantByNumEtu(String)}
     */
    @Test
    void testDeleteEtudiantByNumEtu3() throws Exception {
        doThrow(new EtudiantInnexistantException()).when(this.etudiantService).deleteEtudiantByNumEtudiant((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/etudiants/{numEtudiant}",
                "Num Etudiant");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.etudiantController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link EtudiantController#deleteEtudiantByNumEtu(String)}
     */
    @Test
    void testDeleteEtudiantByNumEtu4() throws Exception {
        doNothing().when(this.etudiantService).deleteAllEtudiant();
        doNothing().when(this.etudiantService).deleteEtudiantByNumEtudiant((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/etudiants/{numEtudiant}", "",
                "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.etudiantController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link EtudiantController#deleteEtudiantByNumEtu(String)}
     */
    @Test
    void testDeleteEtudiantByNumEtu5() throws Exception {
        doNothing().when(this.etudiantService).deleteAllEtudiant();
        doNothing().when(this.etudiantService).deleteEtudiantByNumEtudiant((String) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/etudiants/{numEtudiant}", "",
                "Uri Vars");
        deleteResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.etudiantController)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link EtudiantController#getAllEtudiants()}
     */
    @Test
    void testGetAllEtudiants() throws Exception {
        when(this.etudiantService.getAllEtudiant()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/etudiants");
        MockMvcBuilders.standaloneSetup(this.etudiantController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link EtudiantController#getAllEtudiants()}
     */
    @Test
    void testGetAllEtudiants2() throws Exception {
        when(this.etudiantService.getAllEtudiant()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/etudiants");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.etudiantController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link EtudiantController#getEtudiant(String)}
     */
    @Test
    void testGetEtudiant() throws Exception {
        Etudiant etudiant = new Etudiant();
        etudiant.setEmargement(true);
        etudiant.setGroupeTp("Groupe Tp");
        etudiant.setId("42");
        etudiant.setNom("Nom");
        etudiant.setNumEtudiant("Num Etudiant");
        etudiant.setPrenom("Prenom");
        when(this.etudiantService.findByNumEtudiant((String) any())).thenReturn(etudiant);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/etudiants/{numEtudiant}",
                "Num Etudiant");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.etudiantController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"numEtudiant\":\"Num Etudiant\",\"groupeTp\":\"Groupe Tp\",\"emargement"
                                        + "\":true}"));
    }

    /**
     * Method under test: {@link EtudiantController#getEtudiant(String)}
     */
    @Test
    void testGetEtudiant2() throws Exception {
        Etudiant etudiant = new Etudiant();
        etudiant.setEmargement(true);
        etudiant.setGroupeTp("Groupe Tp");
        etudiant.setId("42");
        etudiant.setNom("Nom");
        etudiant.setNumEtudiant("Num Etudiant");
        etudiant.setPrenom("Prenom");
        when(this.etudiantService.findByNumEtudiant((String) any())).thenReturn(etudiant);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/etudiants/{numEtudiant}",
                "Num Etudiant");
        getResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.etudiantController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"numEtudiant\":\"Num Etudiant\",\"groupeTp\":\"Groupe Tp\",\"emargement"
                                        + "\":true}"));
    }

    /**
     * Method under test: {@link EtudiantController#getEtudiant(String)}
     */
    @Test
    void testGetEtudiant3() throws Exception {
        when(this.etudiantService.findByNumEtudiant((String) any())).thenThrow(new EtudiantInnexistantException());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/etudiants/{numEtudiant}",
                "Num Etudiant");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.etudiantController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link EtudiantController#updateEtudiant(String, Etudiant)}
     */
    @Test
    void testUpdateEtudiant() throws Exception {
        Etudiant etudiant = new Etudiant();
        etudiant.setEmargement(true);
        etudiant.setGroupeTp("Groupe Tp");
        etudiant.setId("42");
        etudiant.setNom("Nom");
        etudiant.setNumEtudiant("Num Etudiant");
        etudiant.setPrenom("Prenom");
        when(this.etudiantService.updateEtudiantByNumEtudiant((String) any(), (Etudiant) any())).thenReturn(etudiant);

        Etudiant etudiant1 = new Etudiant();
        etudiant1.setEmargement(true);
        etudiant1.setGroupeTp("Groupe Tp");
        etudiant1.setId("42");
        etudiant1.setNom("Nom");
        etudiant1.setNumEtudiant("Num Etudiant");
        etudiant1.setPrenom("Prenom");
        String content = (new ObjectMapper()).writeValueAsString(etudiant1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/etudiants/{numEtudiant}", "Num Etudiant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.etudiantController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"numEtudiant\":\"Num Etudiant\",\"groupeTp\":\"Groupe Tp\",\"emargement"
                                        + "\":true}"));
    }

    /**
     * Method under test: {@link EtudiantController#updateEtudiant(String, Etudiant)}
     */
    @Test
    void testUpdateEtudiant2() throws Exception {
        when(this.etudiantService.updateEtudiantByNumEtudiant((String) any(), (Etudiant) any()))
                .thenThrow(new EtudiantInnexistantException());

        Etudiant etudiant = new Etudiant();
        etudiant.setEmargement(true);
        etudiant.setGroupeTp("Groupe Tp");
        etudiant.setId("42");
        etudiant.setNom("Nom");
        etudiant.setNumEtudiant("Num Etudiant");
        etudiant.setPrenom("Prenom");
        String content = (new ObjectMapper()).writeValueAsString(etudiant);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/etudiants/{numEtudiant}", "Num Etudiant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.etudiantController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}