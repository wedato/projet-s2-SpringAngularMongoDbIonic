package com.example.projetsem2qrcode.controlleradmin;

import com.example.projetsem2qrcode.exceptions.*;
import com.example.projetsem2qrcode.modele.Cours;
import com.example.projetsem2qrcode.modele.GroupeTp;
import com.example.projetsem2qrcode.modele.Prof;
import com.example.projetsem2qrcode.service.CoursService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CoursController.class})
@ExtendWith(SpringExtension.class)
class CoursControllerTest {
    @Autowired
    private CoursController coursController;

    @MockBean
    private CoursService coursService;


    /**
     * Method under test: {@link CoursController#createCours(Cours)}
     */
    @Test
    void testCreateCours() throws Exception {
        Cours cours = new Cours();
        Set<GroupeTp> lesGroupes = new HashSet<>();
        Prof prof = new Prof("1","jf","gkg",new HashSet<>());
        cours.setId("1");
        cours.setNom("blabla");
        cours.setLesGroupes(lesGroupes);
        cours.setProf(prof);
        cours.setHeureDebut(LocalDate.of(1970,11,21));
        cours.setHeureFin(LocalDate.of(1970,11,22));

        when(this.coursService.createCours(cours)).thenReturn(cours);

        Cours cours2 = new Cours();
        Set<GroupeTp> lesGroupes2 = new HashSet<>();
        Prof prof2 = new Prof("1","jf","gkg",new HashSet<>());
        cours.setId("1");
        cours.setNom("blabla");
        cours.setLesGroupes(lesGroupes2);
        cours.setProf(prof2);
        cours.setHeureDebut(LocalDate.of(1970,11,21));
        cours.setHeureFin(LocalDate.of(1970,11,22));

        String content = (new ObjectMapper()).writeValueAsString(cours2);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/cours")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    /**
     * Method under test: {@link CoursController#createCours(Cours)}
     */
    @Test
    void testCreateCours2() throws Exception {
        Cours cours = new Cours();
        cours.setId("1");
        cours.setNom("bla");

        when(this.coursService.createCours(cours)).thenThrow(new NomCourInvalidException());

        String content = (new ObjectMapper()).writeValueAsString(cours);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/cours")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    /**
     * Method under test: {@link CoursController#createCours(Cours)}
     */
    @Test
    void testCreateCours3() throws Exception {
        Cours cours = new Cours();
        cours.setId("1");
        cours.setNom("bla");

        when(this.coursService.createCours(cours)).thenThrow(new PasHoraireException());

        String content = (new ObjectMapper()).writeValueAsString(cours);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/cours")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    /**
     * Method under test: {@link CoursController#createCours(Cours)}
     */
    @Test
    void testCreateCours4() throws Exception {
        Cours cours = new Cours();
        cours.setId("1");
        cours.setNom("bla");

        when(this.coursService.createCours(cours)).thenThrow(new CoursDejaCreerException());

        String content = (new ObjectMapper()).writeValueAsString(cours);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/cours")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isConflict());

    }

    /**
     * Method under test: {@link CoursController#addGroupeTpInCours(String, String)}
     */
    @Test
    void testAddGroupeTpInCours() throws Exception {
        Cours cours = new Cours();
        cours.setHeureDebut(null);
        cours.setHeureFin(null);
        cours.setId("42");
        cours.setLesGroupes(new HashSet<>());
        cours.setNom("Nom");
        cours.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        when(this.coursService.addGroupeTPAuCours((String) any(), (String) any())).thenReturn(cours);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .put("/api/cours/{nomCours}/groupe", "Nom Cours")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new String()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    /**
     * Method under test: {@link CoursController#addGroupeTpInCours(String, String)}
     */
    @Test
    void testAddGroupeTpInCours2() throws Exception {
        when(this.coursService.addGroupeTPAuCours((String) any(), (String) any()))
                .thenThrow(new CoursInnexistantException());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .put("/api/cours/{nomCours}/groupe", "Nom Cours")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new String()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CoursController#addGroupeTpInCours(String, String)}
     */
    @Test
    void testAddGroupeTpInCours3() throws Exception {
        when(this.coursService.addGroupeTPAuCours((String) any(), (String) any()))
                .thenThrow(new GroupeTpDejaAjouterException());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .put("/api/cours/{nomCours}/groupe", "Nom Cours")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new String()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link CoursController#addProfInCours(String, String)}
     */
    @Test
    void testAddProfInCours() throws Exception {
        Cours cours = new Cours();
        cours.setHeureDebut(null);
        cours.setHeureFin(null);
        cours.setId("42");
        cours.setLesGroupes(new HashSet<>());
        cours.setNom("Nom");
        cours.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        when(this.coursService.addProfAuCours((String) any(), (String) any())).thenReturn(cours);
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .put("/api/cours/{nomCours}/prof", "Nom Cours")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new String()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    /**
     * Method under test: {@link CoursController#addProfInCours(String, String)}
     */
    @Test
    void testAddProfInCours2() throws Exception {
        when(this.coursService.addProfAuCours((String) any(), (String) any())).thenThrow(new CoursInnexistantException());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .put("/api/cours/{nomCours}/prof", "Nom Cours")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new String()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CoursController#addProfInCours(String, String)}
     */
    @Test
    void testAddProfInCours3() throws Exception {
        when(this.coursService.addProfAuCours((String) any(), (String) any())).thenThrow(new ProfDejaAjouterException());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .put("/api/cours/{nomCours}/prof", "Nom Cours")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new String()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }


    /**
     * Method under test: {@link CoursController#deleteCoursByNom(String)}
     */
    @Test
    void testDeleteCoursByNom() throws Exception {
        doNothing().when(this.coursService).deleteCourByNom((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/cours/{nomCours}", "Nom Cours");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link CoursController#deleteCoursByNom(String)}
     */
    @Test
    void testDeleteCoursByNom2() throws Exception {
        doNothing().when(this.coursService).deleteCourByNom((String) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/cours/{nomCours}", "Nom Cours");
        deleteResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link CoursController#deleteCoursByNom(String)}
     */
    @Test
    void testDeleteCoursByNom3() throws Exception {
        doThrow(new CoursInnexistantException()).when(this.coursService).deleteCourByNom((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/cours/{nomCours}", "Nom Cours");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CoursController#deleteCoursByNom(String)}
     */
    @Test
    void testDeleteCoursByNom4() throws Exception {
        doNothing().when(this.coursService).deleteAllCours();
        doNothing().when(this.coursService).deleteCourByNom((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/cours/{nomCours}", "",
                "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link CoursController#deleteCoursByNom(String)}
     */
    @Test
    void testDeleteCoursByNom5() throws Exception {
        doNothing().when(this.coursService).deleteAllCours();
        doNothing().when(this.coursService).deleteCourByNom((String) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/cours/{nomCours}", "", "Uri Vars");
        deleteResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link CoursController#getAllCours()}
     */
    @Test
    void testGetAllCours() throws Exception {
        when(this.coursService.getAllCours()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/cours");
        MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CoursController#getAllCours()}
     */
    @Test
    void testGetAllCours2() throws Exception {
        when(this.coursService.getAllCours()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/cours");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CoursController#updateCours(String, Cours)}
     */
    @Test
    void testUpdateCours() throws Exception {
        Cours cours = new Cours();
        cours.setId("1");
        cours.setNom("blabla");

        when(this.coursService.updateCours((String) any(),(Cours) any())).thenReturn(cours);

        Cours cours2 = new Cours();
        cours.setId("1");
        cours.setNom("blabla");

        String content = (new ObjectMapper()).writeValueAsString(cours2);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/cours/{nomCours}", "blabla")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    /**
     * Method under test: {@link CoursController#updateCours(String, Cours)}
     */
    @Test
    void testUpdateCours2() throws Exception {
        Cours cours = new Cours();
        cours.setId("1");
        cours.setNom("blabla");

        when(this.coursService.updateCours((String) any(),(Cours) any())).thenThrow(new CoursInnexistantException());

        String content = (new ObjectMapper()).writeValueAsString(cours);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/cours/{nomCours}", "blabla")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CoursController#deleteAllCours()}
     */
    @Test
    void testDeleteAllCours() throws Exception {
        doNothing().when(this.coursService).deleteAllCours();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/cours");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link CoursController#deleteAllCours()}
     */
    @Test
    void testDeleteAllCours2() throws Exception {
        doNothing().when(this.coursService).deleteAllCours();
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/cours");
        deleteResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link CoursController#getCoursByNom(String)}
     */
    @Test
    void testGetCoursByNom() throws Exception {
        Cours cours = new Cours();
        cours.setHeureDebut(null);
        cours.setHeureFin(null);
        cours.setId("42");
        cours.setLesGroupes(new HashSet<>());
        cours.setNom("Nom");
        cours.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        when(this.coursService.findByNomDuCours((String) any())).thenReturn(cours);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/cours/{nomCours}", "Nom Cours");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"nom\":\"Nom\",\"prof\":{\"id\":\"42\",\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"coursDuProf\":[]},\"heureDebut"
                                        + "\":null,\"heureFin\":null,\"lesGroupes\":[]}"));
    }

    /**
     * Method under test: {@link CoursController#getCoursByNom(String)}
     */
    @Test
    void testGetCoursByNom2() throws Exception {
        Cours cours = new Cours();
        cours.setHeureDebut(null);
        cours.setHeureFin(null);
        cours.setId("42");
        cours.setLesGroupes(new HashSet<>());
        cours.setNom("Nom");
        cours.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        when(this.coursService.findByNomDuCours((String) any())).thenReturn(cours);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/cours/{nomCours}", "Nom Cours");
        getResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":\"42\",\"nom\":\"Nom\",\"prof\":{\"id\":\"42\",\"nom\":\"Nom\",\"prenom\":\"Prenom\",\"coursDuProf\":[]},\"heureDebut"
                                        + "\":null,\"heureFin\":null,\"lesGroupes\":[]}"));
    }

    /**
     * Method under test: {@link CoursController#getCoursByNom(String)}
     */
    @Test
    void testGetCoursByNom3() throws Exception {
        when(this.coursService.findByNomDuCours((String) any())).thenThrow(new CoursInnexistantException());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/cours/{nomCours}", "Nom Cours");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.coursController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }


}