package com.triple.assignment.service.impl;

import com.triple.assignment.model.api.ErrorCode;
import com.triple.assignment.model.dto.ReviewCreateRequestDto;
import com.triple.assignment.model.dto.ReviewEventRequestDto;
import com.triple.assignment.model.dto.ReviewResponseDto;
import com.triple.assignment.model.dto.ReviewUpdateRequestDto;
import com.triple.assignment.model.entity.Photo;
import com.triple.assignment.model.entity.Place;
import com.triple.assignment.model.entity.Review;
import com.triple.assignment.model.entity.User;
import com.triple.assignment.model.enums.EventActionType;
import com.triple.assignment.repository.PhotoRepository;
import com.triple.assignment.repository.PlaceRepository;
import com.triple.assignment.repository.ReviewRepository;
import com.triple.assignment.repository.UserRepository;
import com.triple.assignment.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;
    private final ReviewEventSender reviewEventSender;


    @Override
    @Transactional
    public UUID create(ReviewCreateRequestDto reviewCreateRequestDto) {
        User user = userRepository.findById(reviewCreateRequestDto.getUserId())
                .orElseThrow(() -> new ReviewException(ErrorCode.NOT_EXIST_USER));
        Place place = placeRepository.findById(reviewCreateRequestDto.getPlaceId())
                .orElseThrow(() -> new ReviewException(ErrorCode.NOT_EXIST_PlACE));
        Review review = Review.createReview(place, user, null, reviewCreateRequestDto.getContent());
        List<Photo> photos = new ArrayList<>();

        reviewCreateRequestDto.getPhotos().forEach(p -> {
            Photo photo = Photo.createPhoto(p.getPhotoName(),review);
            photos.add(photo);
        });

        reviewRepository.save(review);


        ReviewEventRequestDto reviewEventRequestDto = getReviewEventRequestDto(photos, review);
        reviewEventSender.send(reviewEventRequestDto);

        return review.getId();
    }

    private ReviewEventRequestDto getReviewEventRequestDto(List<Photo> photos, Review review) {
        ReviewEventRequestDto reviewEventRequestDto = ReviewEventRequestDto.builder()
                .reviewId(review.getId())
                .userId(review.getId())
                .placeId(review.getPlace().getId())
                .attachedPhotoIds(photos.stream().map(p -> p.getId()).collect(Collectors.toList()))
                .content(review.getContent())
                .eventActionType(EventActionType.ADD)
                .build();
        return reviewEventRequestDto;
    }

    @Override
    public void update(UUID reviewId, ReviewUpdateRequestDto reviewUpdateRequestDto) {

    }

    @Override
    public void delete(UUID reviewId) {

    }

    @Override
    public List<ReviewResponseDto> get() {
        return null;
    }
}
