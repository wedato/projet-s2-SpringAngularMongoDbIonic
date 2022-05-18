package com.example.projetsem2qrcode.controlleradmin;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.example.projetsem2qrcode.exceptions.EtudiantDejaDansUnGroupeException;
import com.example.projetsem2qrcode.exceptions.GroupeDejaCreerException;
import com.example.projetsem2qrcode.exceptions.GroupeInnexistantException;
import com.example.projetsem2qrcode.exceptions.NomGroupeNonValideException;
import com.example.projetsem2qrcode.modele.GroupeTp;
import com.example.projetsem2qrcode.service.GroupeTpService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import java.util.HashSet;

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

@ContextConfiguration(classes = {GroupeTpController.class})
@ExtendWith(SpringExtension.class)
class GroupeTpControllerTest {

    /**
     * Method under test: {@link GroupeTpController#addEtudiantAuGroupeTp(String, String)}
     */
    @Test
    void testAddEtudiantAuGroupeTp() throws Exception {
        when(this.groupeTpService.addEtudiantInGroupe((String) any(), (String) any()))
                .thenReturn(new GroupeTp("Numero Groupe"));
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .put("/api/groupetp/{numeroGroupe}", "Numero Groupe")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new String()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    /**
     * Method under test: {@link GroupeTpController#addEtudiantAuGroupeTp(String, String)}
     */
    @Test
    void testAddEtudiantAuGroupeTp2() throws Exception {
        when(this.groupeTpService.addEtudiantInGroupe((String) any(), (String) any()))
                .thenThrow(new GroupeInnexistantException());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .put("/api/groupetp/{numeroGroupe}", "Numero Groupe")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new String()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link GroupeTpController#addEtudiantAuGroupeTp(String, String)}
     */
    @Test
    void testAddEtudiantAuGroupeTp3() throws Exception {
        when(this.groupeTpService.addEtudiantInGroupe((String) any(), (String) any()))
                .thenThrow(new EtudiantDejaDansUnGroupeException());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .put("/api/groupetp/{numeroGroupe}", "Numero Groupe")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new String()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link GroupeTpController#createGroupeTp(GroupeTp)}
     */
    @Test
    void testCreateGroupeTp() throws Exception {
        when(this.groupeTpService.saveGroupeTp((GroupeTp) any())).thenReturn(new GroupeTp("Numero Groupe"));

        GroupeTp groupeTp = new GroupeTp();
        groupeTp.setId("42");
        groupeTp.setListeEtudiantGroupe(new HashSet<>());
        groupeTp.setNumeroGroupe("Numero Groupe");
        String content = (new ObjectMapper()).writeValueAsString(groupeTp);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/groupetp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":\"42\",\"numeroGroupe\":\"Numero Groupe\",\"listeEtudiantGroupe\":[]}"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/api/groupetp/Numero%20Groupe"));
    }

    /**
     * Method under test: {@link GroupeTpController#deleteAllEtudiantInGroupe(String)}
     */
    @Test
    void testDeleteAllEtudiantInGroupe() throws Exception {
        doNothing().when(this.groupeTpService).deleteAllEtudiantInGroupeTp((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/groupetp/{numeroGroupe}/etudiant", "Numero Groupe");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link GroupeTpController#deleteAllEtudiantInGroupe(String)}
     */
    @Test
    void testDeleteAllEtudiantInGroupe2() throws Exception {
        doNothing().when(this.groupeTpService).deleteAllEtudiantInGroupeTp((String) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/groupetp/{numeroGroupe}/etudiant",
                "Numero Groupe");
        deleteResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link GroupeTpController#deleteAllEtudiantInGroupe(String)}
     */
    @Test
    void testDeleteAllEtudiantInGroupe3() throws Exception {
        doNothing().when(this.groupeTpService).deleteGroupeByNumGroupe((String) any());
        doNothing().when(this.groupeTpService).deleteAllEtudiantInGroupeTp((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/groupetp/{numeroGroupe}/etudiant", "", "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link GroupeTpController#deleteAllEtudiantInGroupe(String)}
     */
    @Test
    void testDeleteAllEtudiantInGroupe4() throws Exception {
        doNothing().when(this.groupeTpService).deleteGroupeByNumGroupe((String) any());
        doNothing().when(this.groupeTpService).deleteAllEtudiantInGroupeTp((String) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/groupetp/{numeroGroupe}/etudiant",
                "", "Uri Vars");
        deleteResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link GroupeTpController#deleteAllEtudiantInGroupe(String)}
     */
    @Test
    void testDeleteAllEtudiantInGroupe5() throws Exception {
        doThrow(new GroupeInnexistantException()).when(this.groupeTpService).deleteGroupeByNumGroupe((String) any());
        doNothing().when(this.groupeTpService).deleteAllEtudiantInGroupeTp((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/groupetp/{numeroGroupe}/etudiant", "", "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link GroupeTpController#deleteAllGroupe()}
     */
    @Test
    void testDeleteAllGroupe() throws Exception {
        doNothing().when(this.groupeTpService).deleteAllGroupeTp();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/groupetp");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link GroupeTpController#deleteAllGroupe()}
     */
    @Test
    void testDeleteAllGroupe2() throws Exception {
        doNothing().when(this.groupeTpService).deleteAllGroupeTp();
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/groupetp");
        deleteResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link GroupeTpController#getAllGroupeTp()}
     */
    @Test
    void testGetAllGroupeTp() throws Exception {
        when(this.groupeTpService.getAllGroupeTp()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/groupetp");
        MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link GroupeTpController#getAllGroupeTp()}
     */
    @Test
    void testGetAllGroupeTp2() throws Exception {
        when(this.groupeTpService.getAllGroupeTp()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/groupetp");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link GroupeTpController#deleteGroupeByNumGroupe(String)}
     */
    @Test
    void testDeleteGroupeByNumGroupe() throws Exception {
        doNothing().when(this.groupeTpService).deleteGroupeByNumGroupe((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/groupetp/{numeroGroupe}",
                "Numero Groupe");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link GroupeTpController#deleteGroupeByNumGroupe(String)}
     */
    @Test
    void testDeleteGroupeByNumGroupe2() throws Exception {
        doNothing().when(this.groupeTpService).deleteGroupeByNumGroupe((String) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/groupetp/{numeroGroupe}",
                "Numero Groupe");
        deleteResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link GroupeTpController#deleteGroupeByNumGroupe(String)}
     */
    @Test
    void testDeleteGroupeByNumGroupe3() throws Exception {
        doThrow(new GroupeInnexistantException()).when(this.groupeTpService).deleteGroupeByNumGroupe((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/groupetp/{numeroGroupe}",
                "Numero Groupe");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link GroupeTpController#deleteGroupeByNumGroupe(String)}
     */
    @Test
    void testDeleteGroupeByNumGroupe4() throws Exception {
        doNothing().when(this.groupeTpService).deleteAllGroupeTp();
        doNothing().when(this.groupeTpService).deleteGroupeByNumGroupe((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/groupetp/{numeroGroupe}", "",
                "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link GroupeTpController#deleteGroupeByNumGroupe(String)}
     */
    @Test
    void testDeleteGroupeByNumGroupe5() throws Exception {
        doNothing().when(this.groupeTpService).deleteAllGroupeTp();
        doNothing().when(this.groupeTpService).deleteGroupeByNumGroupe((String) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/groupetp/{numeroGroupe}", "",
                "Uri Vars");
        deleteResult.contentType("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link GroupeTpController#createGroupeTp(GroupeTp)}
     */
    @Test
    void testCreateGroupeTp2() throws Exception {
        when(this.groupeTpService.saveGroupeTp((GroupeTp) any())).thenThrow(new GroupeDejaCreerException());

        GroupeTp groupeTp = new GroupeTp();
        groupeTp.setId("42");
        groupeTp.setListeEtudiantGroupe(new HashSet<>());
        groupeTp.setNumeroGroupe("Numero Groupe");
        String content = (new ObjectMapper()).writeValueAsString(groupeTp);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/groupetp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409));
    }

    /**
     * Method under test: {@link GroupeTpController#createGroupeTp(GroupeTp)}
     */
    @Test
    void testCreateGroupeTp3() throws Exception {
        when(this.groupeTpService.saveGroupeTp((GroupeTp) any())).thenThrow(new NomGroupeNonValideException());

        GroupeTp groupeTp = new GroupeTp();
        groupeTp.setId("42");
        groupeTp.setListeEtudiantGroupe(new HashSet<>());
        groupeTp.setNumeroGroupe("Numero Groupe");
        String content = (new ObjectMapper()).writeValueAsString(groupeTp);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/groupetp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link GroupeTpController#getGroupeTp(String)}
     */
    @Test
    void testGetGroupeTp() throws Exception {
        when(this.groupeTpService.findGroupeByNumGroupe((String) any())).thenReturn(new GroupeTp("Numero Groupe"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/groupetp/{numeroGroupe}",
                "Numero Groupe");
        MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound());
    }

    /**
     * Method under test: {@link GroupeTpController#getGroupeTp(String)}
     */
    @Test
    void testGetGroupeTp2() throws Exception {
        when(this.groupeTpService.findGroupeByNumGroupe((String) any())).thenReturn(new GroupeTp("Numero Groupe"));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/groupetp/{numeroGroupe}",
                "Numero Groupe");
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isFound());
    }

    /**
     * Method under test: {@link GroupeTpController#getGroupeTp(String)}
     */
    @Test
    void testGetGroupeTp3() throws Exception {
        when(this.groupeTpService.findGroupeByNumGroupe((String) any())).thenThrow(new GroupeInnexistantException());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/groupetp/{numeroGroupe}",
                "Numero Groupe");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link GroupeTpController#getGroupeTp(String)}
     */
    @Test
    void testGetGroupeTp4() throws Exception {
        when(this.groupeTpService.getAllGroupeTp()).thenReturn(new ArrayList<>());
        when(this.groupeTpService.findGroupeByNumGroupe((String) any())).thenReturn(new GroupeTp("Numero Groupe"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/groupetp/{numeroGroupe}", "",
                "Uri Vars");
        MockMvcBuilders.standaloneSetup(this.groupeTpController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Autowired
    private GroupeTpController groupeTpController;

    @MockBean
    private GroupeTpService groupeTpService;


}