package com.triple.assignment.repository;

import com.triple.assignment.model.entity.Place;
import com.triple.assignment.model.entity.Point;
import com.triple.assignment.model.entity.Review;
import com.triple.assignment.model.entity.User;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    Optional<Review> findTop1ByPlaceOrderByCreatedDatetimeAsc(Place place);
}
