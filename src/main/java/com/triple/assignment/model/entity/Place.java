package com.triple.assignment.model.entity;

import com.triple.assignment.model.entity.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Place extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String placeName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "place", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    private Place(String placeName) {
        this.placeName = placeName;
    }

    public static Place createPlace(String placeName) {
        return new Place(placeName);
    }

    public boolean hasReview() {
        return !this.reviews.isEmpty();
    }
}
