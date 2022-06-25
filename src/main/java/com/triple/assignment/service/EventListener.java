package com.triple.assignment.service;

import com.triple.assignment.model.dto.EventRequest;
import com.triple.assignment.service.impl.EventListenerException;

public interface EventListener <T extends EventRequest> {
    void receive(T eventRequest) throws EventListenerException;
}
