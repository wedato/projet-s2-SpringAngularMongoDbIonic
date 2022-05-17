package com.example.projetsem2qrcode.controlleradmin;

import com.example.projetsem2qrcode.service.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

class CoursControllerTest {

    @Autowired
    private CoursController coursController;

    @MockBean
    private CoursService coursService;


}