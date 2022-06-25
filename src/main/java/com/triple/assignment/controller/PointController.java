package com.triple.assignment.controller;

import com.triple.assignment.model.api.ApiResponse;
import com.triple.assignment.model.dto.PointCreateRequestDto;
import com.triple.assignment.model.dto.PointResponseDto;
import com.triple.assignment.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PointController {
    private final PointService pointService;

    @GetMapping("/api/v1.0/points/{point_id}")
    public ResponseEntity<ApiResponse<PointResponseDto>> getPoint(@PathVariable("point_id") UUID pointId) {
        PointResponseDto pointResponseDto = pointService.getPoint(pointId);

        return ResponseEntity
                .ok(new ApiResponse<>(pointResponseDto));
    }

    @PostMapping("/api/v1.0/users/{user_id}/points")
    public ResponseEntity<ApiResponse<List<PointResponseDto>>> getUserPoint(
            @PathVariable("user_id") UUID userId, @RequestBody PointCreateRequestDto pointCreateRequestDto) {
        List<PointResponseDto> userPoints = pointService.getUserPoints(userId);

        return ResponseEntity
                .ok(new ApiResponse<>(userPoints));
    }
}
