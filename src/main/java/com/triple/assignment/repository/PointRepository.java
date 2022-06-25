package com.triple.assignment.repository;

import com.triple.assignment.model.entity.Point;
import com.triple.assignment.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PointRepository extends JpaRepository<Point, UUID> {
    List<Point> findByUser(User user);
    Optional<Point> findTop1ByUserOrderByCreatedDatetimeDesc(User user);

}
