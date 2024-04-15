package com.example.codeChallenge;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoOperations;

import static org.mockito.Mockito.when;

class InputNumberResultServiceTest {

    @Mock
    MongoOperations mockMongoOperations;
    AutoCloseable openMocks;
    private InputNumberResultService inputNumberResultService;

    @BeforeEach
    public void beforeEach() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_addInputNumberResult_should_calculate_correct_result() {
        inputNumberResultService = new InputNumberResultService(mockMongoOperations);
        long inputNumber = 10;

        InputNumberResult expectedInputNumberResult = new InputNumberResult(
                inputNumber,
                inputNumber * 2,
                (inputNumber * 2) * (inputNumber * 2)
        );

        when(mockMongoOperations.save(expectedInputNumberResult)).thenReturn(expectedInputNumberResult);
        inputNumberResultService.addInputNumberResult(inputNumber);
    }

    @AfterEach
    public void afterEach() throws Exception {
        openMocks.close();
    }
}