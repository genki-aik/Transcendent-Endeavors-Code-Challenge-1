package com.example.codeChallenge;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/inputNumberResult")
public class InputNumberResultController {

    private InputNumberResultService inputNumberResultService;

    private InputNumberResultController(InputNumberResultService inputNumberResultService) {
        this.inputNumberResultService = inputNumberResultService;
    }

    @PostMapping("/storeNumber")
    public ResponseEntity<InputNumberResult> addInputNumberResult(@RequestBody Map<String, Long> payload) {
        return new ResponseEntity<>(inputNumberResultService.addInputNumberResult(payload.get("inputNumber")), HttpStatus.CREATED);
    }
}
