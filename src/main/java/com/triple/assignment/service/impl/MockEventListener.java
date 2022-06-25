package com.triple.assignment.service.impl;

import com.triple.assignment.model.dto.MockEventRequestDto;
import com.triple.assignment.service.EventListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MockEventListener implements EventListener<MockEventRequestDto> {

    @Override
    public void receive(MockEventRequestDto eventRequest) throws EventListenerException {
        log.debug(eventRequest.toString());
    }
}

