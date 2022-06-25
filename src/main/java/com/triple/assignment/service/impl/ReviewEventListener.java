package com.triple.assignment.service.impl;

import com.triple.assignment.model.api.ErrorCode;
import com.triple.assignment.model.dto.ReviewEventRequestDto;
import com.triple.assignment.model.entity.*;
import com.triple.assignment.model.enums.EventActionType;
import com.triple.assignment.model.enums.EventType;
import com.triple.assignment.repository.PlaceRepository;
import com.triple.assignment.repository.PointRepository;
import com.triple.assignment.repository.ReviewRepository;
import com.triple.assignment.repository.UserRepository;
import com.triple.assignment.service.EventListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReviewEventListener implements EventListener<ReviewEventRequestDto> {
    private final UserRepository userRepository;
    private final PointRepository pointRepository;
    private final PlaceRepository placeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public void receive(ReviewEventRequestDto eventRequest) throws EventListenerException {
        log.debug("Review Event Listen... ReviewEventRequest={}", eventRequest);

        int beforePoint;
        int appliedPoint = 0;
        int afterPoint = 0;
        EventActionType action = eventRequest.getAction();
        EventType type = eventRequest.getType();

        User user = userRepository.findById(eventRequest.getUserId())
                .orElseThrow(() -> new EventListenerException(ErrorCode.NOT_EXIST_USER));
        Place place = placeRepository.findById(eventRequest.getPlaceId())
                .orElseThrow(() -> new EventListenerException(ErrorCode.NOT_EXIST_PlACE));
        Review firstReview = reviewRepository.findTop1ByPlaceOrderByCreatedDatetimeAsc(place)
                .orElse(null);

        Point point = getRecentPointHistory(action, type, user,null);
        beforePoint = point.getPoint();

        boolean isWrite = place.getReviews().stream()
                .filter(r -> r.isWriter(user.getId()))
                .findFirst().isPresent();

        switch (eventRequest.getAction()) {
            case ADD:
                if(isWrite) {
                    return;
                }

                log.info("Before User Point ={}", beforePoint);
                if(hasContent(eventRequest.getContent())) {
                    appliedPoint++;
                }

                if(hasPhoto(eventRequest.getAttachedPhotoIds())) {
                    appliedPoint++;
                }
                log.info("After Applied Event Point ={}", appliedPoint);

                if(!place.hasReview()) {
                    appliedPoint++;
                    log.info("First Write Review ={}", appliedPoint);
                }

                log.info("After User Point={}", afterPoint);
                afterPoint = beforePoint + appliedPoint;
                pointRepository.save(Point.createPoint(
                        user,
                        afterPoint,
                        action,
                        type,
                        eventRequest.getContent(),
                        appliedPoint)
                );
                break;
            case MOD:
                /* Todo */

                break;
            case DELETE:
                log.info("Before User Point ={}", beforePoint);
                if(beforePoint <= 0) {
                    return;
                }

                if(firstReview.isWriter(user.getId())) {
                    appliedPoint--;
                }

                if(hasContent(firstReview.getContent())) {
                    appliedPoint--;
                }
                log.info("After Applied Event Point ={}", appliedPoint);

                if(firstReview.hasPhotos()){
                    appliedPoint--;
                }
                log.info("After User Point={}", afterPoint);
                afterPoint = beforePoint + appliedPoint;
                pointRepository.save(Point.createPoint(
                        user,
                        afterPoint,
                        action,
                        type,
                        eventRequest.getContent(),
                        appliedPoint
                ));
                break;
        }
    }

    @Transactional(readOnly = true)
    private Point getRecentPointHistory(EventActionType action, EventType type, User user, String content) {
        return pointRepository.findTop1ByUserOrderByCreatedDatetimeDesc(user)
                .orElse(Point.createPoint(
                        user,
                        0,
                        action,
                        type,
                        content,
                        0)
                );
    }

    private boolean hasContent(String content) {
        return Strings.isNotBlank(content);
    }

    private boolean hasPhoto(List<UUID> photos) {
        return !CollectionUtils.isEmpty(photos);
    }
}

