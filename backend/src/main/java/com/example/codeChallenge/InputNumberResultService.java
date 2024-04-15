package com.example.codeChallenge;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
public class InputNumberResultService {
    private MongoOperations mongoOperations;

    public InputNumberResultService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public InputNumberResult addInputNumberResult(long inputNumber) {
        long inputNumberDoubled = inputNumber * 2;

        return mongoOperations.save(new InputNumberResult(
                inputNumber,
                inputNumberDoubled,
                inputNumberDoubled * inputNumberDoubled));
    }

}
