package com.example.codeChallenge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "results")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputNumberResult {
    @Id
    private long inputNumber;

    // No need to store in database as per instructions
    @Transient
    private long inputNumberDoubled;

    private long inputNumberDoubledAndSquared;
}
