package com.example.projetsem2qrcode.controlleradmin;

import com.example.projetsem2qrcode.exceptions.CoursInnexistantException;
import com.example.projetsem2qrcode.exceptions.GroupeTpDejaAjouterException;
import com.example.projetsem2qrcode.exceptions.ProfDejaAjouterException;
import com.example.projetsem2qrcode.modele.Cours;
import com.example.projetsem2qrcode.modele.GroupeTp;
import com.example.projetsem2qrcode.modele.Prof;
import com.example.projetsem2qrcode.service.CoursService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
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

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CoursController.class})
@ExtendWith(SpringExtension.class)
class CoursControllerTest {
    @Autowired
    private CoursController coursController;

    @MockBean
    private CoursService coursService;

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
     * Method under test: {@link CoursController#createCours(Cours)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateCours() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.projetsem2qrcode.controlleradmin.CoursController.createCours(CoursController.java:26)
        //   In order to prevent createCours(Cours)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   createCours(Cours).
        //   See https://diff.blue/R013 to resolve this issue.

        CoursController coursController = new CoursController();

        Cours cours = new Cours();
        cours.setHeureDebut(null);
        cours.setHeureFin(null);
        cours.setId("42");
        cours.setLesGroupes(new HashSet<>());
        cours.setNom("Nom");
        cours.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        coursController.createCours(cours);
    }

    /**
     * Method under test: {@link CoursController#createCours(Cours)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateCours2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.projetsem2qrcode.controlleradmin.CoursController.createCours(CoursController.java:26)
        //   In order to prevent createCours(Cours)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   createCours(Cours).
        //   See https://diff.blue/R013 to resolve this issue.

        CoursController coursController = new CoursController();
        Cours cours = mock(Cours.class);
        doNothing().when(cours).setHeureDebut((LocalDate) any());
        doNothing().when(cours).setHeureFin((LocalDate) any());
        doNothing().when(cours).setId((String) any());
        doNothing().when(cours).setLesGroupes((java.util.Set<GroupeTp>) any());
        doNothing().when(cours).setNom((String) any());
        doNothing().when(cours).setProf((Prof) any());
        cours.setHeureDebut(null);
        cours.setHeureFin(null);
        cours.setId("42");
        cours.setLesGroupes(new HashSet<>());
        cours.setNom("Nom");
        cours.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        coursController.createCours(cours);
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
    @Disabled("TODO: Complete this test")
    void testUpdateCours() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.projetsem2qrcode.controlleradmin.CoursController.updateCours(CoursController.java:56)
        //   In order to prevent updateCours(String, Cours)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   updateCours(String, Cours).
        //   See https://diff.blue/R013 to resolve this issue.

        CoursController coursController = new CoursController();

        Cours cours = new Cours();
        cours.setHeureDebut(null);
        cours.setHeureFin(null);
        cours.setId("42");
        cours.setLesGroupes(new HashSet<>());
        cours.setNom("Nom");
        cours.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        coursController.updateCours("Nom Cours", cours);
    }

    /**
     * Method under test: {@link CoursController#updateCours(String, Cours)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateCours2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.example.projetsem2qrcode.controlleradmin.CoursController.updateCours(CoursController.java:56)
        //   In order to prevent updateCours(String, Cours)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   updateCours(String, Cours).
        //   See https://diff.blue/R013 to resolve this issue.

        CoursController coursController = new CoursController();
        Cours cours = mock(Cours.class);
        doNothing().when(cours).setHeureDebut((LocalDate) any());
        doNothing().when(cours).setHeureFin((LocalDate) any());
        doNothing().when(cours).setId((String) any());
        doNothing().when(cours).setLesGroupes((java.util.Set<GroupeTp>) any());
        doNothing().when(cours).setNom((String) any());
        doNothing().when(cours).setProf((Prof) any());
        cours.setHeureDebut(null);
        cours.setHeureFin(null);
        cours.setId("42");
        cours.setLesGroupes(new HashSet<>());
        cours.setNom("Nom");
        cours.setProf(new Prof("42", "Nom", "Prenom", new HashSet<>()));
        coursController.updateCours("Nom Cours", cours);
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