package com.exam.controller;

public class OpenAI {
          private String model;
          private String prompt;
          private Integer  max_tokens;
          private Double temperature;
          private Integer top_p;
          private Integer frequency_penalty;
          private Integer presence_penalty;

    public OpenAI(String prompt) {
     this.model = "text-davinci-002";
     this.prompt = prompt;
     this.max_tokens = 100;
     this.temperature = 0.5;
     this.top_p = 1;
     this.frequency_penalty = 0;
     this.presence_penalty = 0;

    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Integer getMax_tokens() {
        return max_tokens;
    }

    public void setMax_tokens(Integer max_tokens) {
        this.max_tokens = max_tokens;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getTop_p() {
        return top_p;
    }

    public void setTop_p(Integer top_p) {
        this.top_p = top_p;
    }

    public Integer getFrequency_penalty() {
        return frequency_penalty;
    }

    public void setFrequency_penalty(Integer frequency_penalty) {
        this.frequency_penalty = frequency_penalty;
    }

    public Integer getPresence_penalty() {
        return presence_penalty;
    }

    public void setPresence_penalty(Integer presence_penalty) {
        this.presence_penalty = presence_penalty;
    }
}
