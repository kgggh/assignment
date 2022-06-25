package com.triple.assignment.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.triple.assignment.model.dto.ReviewEventRequestDto;
import com.triple.assignment.service.EventSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReviewEventSender implements EventSender<ReviewEventRequestDto> {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String EVENT_URL = "http://localhost:8080/api/v1.0/event";
    private final ObjectMapper objectMapper;

    @Override
    public void send(ReviewEventRequestDto eventRequest) {
        log.debug("Send Review Event={}", eventRequest);

        try {
            String json = objectMapper.writeValueAsString(eventRequest);
            log.info(json);

            ResponseEntity<String> response =
                    restTemplate.postForEntity(EVENT_URL, objectMapper.writeValueAsString(eventRequest), String.class);
            if(response.getStatusCode().is2xxSuccessful()) {
                log.info("Send Review Event Success");
            }
        } catch (RestClientException | JsonProcessingException e) {
            log.error("Send Review Event Fail... ", e);
            /* Todo retry...*/
        }
    }
}
