package com.triple.assignment.controller;

import com.triple.assignment.model.api.ApiResponse;
import com.triple.assignment.model.dto.EventRequest;
import com.triple.assignment.model.dto.MockEventRequestDto;
import com.triple.assignment.model.dto.ReviewEventRequestDto;
import com.triple.assignment.service.EventListener;
import com.triple.assignment.service.impl.EventListenerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@Slf4j
@RestController
public class EventController {
    private final EventListener reviewEventService;
    private final EventListener mockEventService;

    public EventController(EventListener<ReviewEventRequestDto> reviewEventService,
                           EventListener<MockEventRequestDto> mockEventService) {
        this.reviewEventService = reviewEventService;
        this.mockEventService = mockEventService;
    }

    @PostMapping("/api/v1.0/event")
    public ResponseEntity<ApiResponse> listen(@Valid @RequestBody EventRequest eventRequest) throws EventListenerException {
        log.debug("event listen={}", eventRequest);

        switch (eventRequest.getType()) {
            case REVIEW:
                reviewEventService.receive(eventRequest);
                break;
            case TEST:
                mockEventService.receive(eventRequest);
                break;
        }

        return ResponseEntity.ok(new ApiResponse());
    }
}
