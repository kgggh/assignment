package com.triple.assignment.service;

import com.triple.assignment.model.dto.EventRequest;
import com.triple.assignment.service.impl.EventSenderException;

public interface EventSender<T extends EventRequest> {
    void send(T eventRequest) throws EventSenderException;
}
