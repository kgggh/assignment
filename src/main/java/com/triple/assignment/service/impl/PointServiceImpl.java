package com.triple.assignment.service.impl;

import com.triple.assignment.model.api.ErrorCode;
import com.triple.assignment.model.dto.PointCreateRequestDto;
import com.triple.assignment.model.dto.PointResponseDto;
import com.triple.assignment.model.entity.Point;
import com.triple.assignment.model.entity.User;
import com.triple.assignment.repository.PointRepository;
import com.triple.assignment.repository.UserRepository;
import com.triple.assignment.service.PointException;
import com.triple.assignment.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {
    private final UserRepository userRepository;
    private final PointRepository pointRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PointResponseDto> get(UUID userId) {
        log.debug("userId={}",userId);

        User user = getUser(userId);
        List<Point> userPoint = pointRepository.findByUser(user);
        return Optional.ofNullable(userPoint.stream()
                .map(point -> PointResponseDto.builder()
                        .point(point.getPoint())
                        .userId(point.getUser().getId())
                        .appliedPoint(point.getAppliedPoint())
                        .actionType(point.getActionType())
                        .type(point.getType())
                        .content(point.getContent())
                        .build())
                .collect(Collectors.toList())).filter(u -> !u.isEmpty()).orElseThrow(() -> new PointException(ErrorCode.NOT_EXIST_POINT));
    }

    private User getUser(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new PointException(ErrorCode.NOT_EXIST_USER.getReason()));
    }

    @Override
    @Transactional(readOnly = true)
    public int getTotalPoint(UUID userId) {
        User user = getUser(userId);
        Point point = pointRepository.findTopByUserOrderByCreatedDatetimeDesc(user)
                .orElseThrow(() -> new PointException(ErrorCode.NOT_EXIST_POINT));
        return point.getPoint();
    }

    @Override
    @Transactional
    public UUID create(PointCreateRequestDto pointCreateRequestDto) {
        log.debug("pointCreateRequestDto={}",pointCreateRequestDto);
        User user = getUser(pointCreateRequestDto.getUserId());
        return pointRepository.save(Point.createPoint(
                user,pointCreateRequestDto.getPoint(),
                pointCreateRequestDto.getActionType(),
                pointCreateRequestDto.getType(),
                pointCreateRequestDto.getContent(),
                pointCreateRequestDto.getAppliedPoint())
        ).getId();
    }
}
