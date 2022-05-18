package com.example.projetsem2qrcode.service;

import com.google.common.cache.LoadingCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LoginAttemptService.class})
@ExtendWith(SpringExtension.class)
class LoginAttemptServiceTest {


    @Autowired
    private LoginAttemptService loginAttemptService;

    @Mock
    private LoadingCache<String, Integer> loginAttemptCache;

    @BeforeEach
    void init() {
        loginAttemptService = new LoginAttemptService();
    }

    /**
     * Method under test: {@link LoginAttemptService#evictUserFromLoginAttemptCache(String)}
     */
    @Test
    void testEvictUserFromLoginAttemptCache() {
        // TODO: Complete this test.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by evictUserFromLoginAttemptCache(String)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        this.loginAttemptService.evictUserFromLoginAttemptCache("janedoe");
    }


}