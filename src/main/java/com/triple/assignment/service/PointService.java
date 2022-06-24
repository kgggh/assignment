package com.triple.assignment.service;

import com.triple.assignment.model.dto.PointCreateRequestDto;
import com.triple.assignment.model.dto.PointResponseDto;

import java.util.List;
import java.util.UUID;

public interface PointService {
    List<PointResponseDto> get(UUID userId) throws PointException;
    int getTotalPoint(UUID userId) throws PointException;
    UUID create(PointCreateRequestDto pointCreateRequestDto);
}
