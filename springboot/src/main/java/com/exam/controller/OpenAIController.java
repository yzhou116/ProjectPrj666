package com.exam.controller;

import com.exam.entity.ApiResult;
import com.exam.entity.Score;
import com.exam.util.ApiResultHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@CrossOrigin
public class OpenAIController {

    private static String API_KEY = "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";

    @PostMapping("/openai")
    public ResponseEntity<String> generateText(@RequestBody String prompt) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + API_KEY);
            OpenAI openAI = new OpenAI(prompt);
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(openAI);



          //  String requestJson = "{\"model\":\"text-davinci-002\",\"prompt\":\"" + prompt + "\",\"max_tokens\":100,\"temperature\":0.5,\"top_p\":1,\"frequency_penalty\":0,\"presence_penalty\":0}";

            HttpEntity<String> entity = new HttpEntity<>(jsonString, headers);

            ResponseEntity<String> response = restTemplate.postForEntity("https://api.openai.com/v1/completions", entity, String.class);

            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
