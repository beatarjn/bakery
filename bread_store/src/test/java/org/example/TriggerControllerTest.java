package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TriggerControllerTest {

    private TriggerController triggerController;
    @Mock
    private BakeryService bakeryService;
    private AutoCloseable autoCloseable;
    @Mock
    private Bread bread;


    @BeforeEach
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        triggerController = new TriggerController(bakeryService);
    }


    @Test
    void postBread() {
        when(bakeryService.acceptBread(bread)).thenReturn("ok");

        String result = triggerController.postBread(bread);

        assertEquals("ok", result);
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();
    }
}