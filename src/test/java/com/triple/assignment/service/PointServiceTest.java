package com.triple.assignment.service;

import com.triple.assignment.model.dto.PointCreateRequestDto;
import com.triple.assignment.model.dto.PointResponseDto;
import com.triple.assignment.model.entity.User;
import com.triple.assignment.model.enums.EventActionType;
import com.triple.assignment.model.enums.EventType;
import com.triple.assignment.repository.UserRepository;
import com.triple.assignment.service.impl.PointException;
import com.triple.assignment.service.impl.PointServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class PointServiceTest {
    UserRepository userRepository;
    PointService pointService;

    @Autowired
    public PointServiceTest(PointServiceImpl pointService, UserRepository userRepository) {
        this.pointService = pointService;
        this.userRepository = userRepository;
    }
    @Test
    @DisplayName("유저의 포인트 조회 실패")
    void 유저의_포인트내역이_존재하지_않을떄() {
        /* given */
        User user = userRepository.save(User.createUser("테스터입니다."));

        /* when */

        /* then */
        assertThrows(PointException.class, () -> pointService.getUserPoints(user.getId()));
    }

    @Test
    @DisplayName("유저의 포인트내역이 존재할때 ")
    void 유저의_포인트내역이_존재할때() {
        /* given */
        User user = userRepository.save(User.createUser("테스터입니다."));
        PointCreateRequestDto pointCreateRequestDto = new PointCreateRequestDto(
                user.getId(),
                EventType.REVIEW,
                EventActionType.ADD,
                "하잉",
                1,
                1);
        pointService.create(pointCreateRequestDto);

        /* when */
        List<PointResponseDto> userPoints = pointService.getUserPoints(user.getId());

        /* then */
        System.out.println(userPoints);
        assertEquals(userPoints.size(), 1);
    }

    @Test
    @DisplayName("유저의 포인트 sync가 맞는지")
    void 유저의_포인트점수를_정상적으로_가져온다() {
        /* given */
        User user = userRepository.save(User.createUser("테스터입니다."));
        PointCreateRequestDto pointCreateRequestDto = new PointCreateRequestDto(
                user.getId(),
                EventType.REVIEW,
                EventActionType.ADD,
                "하잉",
                1,
                1);
        pointService.create(pointCreateRequestDto);

        PointCreateRequestDto pointCreateRequestDto2 = new PointCreateRequestDto(
                user.getId(),
                EventType.REVIEW,
                EventActionType.ADD,
                "하잉",
                55,
                1);
        pointService.create(pointCreateRequestDto2);
        /* when */
        int totalPoint = pointService.getTotalPoint(user.getId());

        /* then */
        assertNotNull(totalPoint);
        assertNotEquals(totalPoint, 2);
    }
}