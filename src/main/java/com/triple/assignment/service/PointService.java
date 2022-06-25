package com.triple.assignment.service;

import com.triple.assignment.model.dto.PointCreateRequestDto;
import com.triple.assignment.model.dto.PointResponseDto;
import com.triple.assignment.service.impl.PointException;

import java.util.List;
import java.util.UUID;

public interface PointService {
    List<PointResponseDto> getUserPoints(UUID userId) throws PointException;
    PointResponseDto getPoint(UUID pointId) throws PointException;
    int getTotalPoint(UUID userId) throws PointException;
    UUID create(PointCreateRequestDto pointCreateRequestDto);
}
