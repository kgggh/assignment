package com.triple.assignment.model.entity;

import com.triple.assignment.model.entity.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Photo extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String photoName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id")
    private Review review;

    private Photo(String photoName, Review review) {
        this.photoName = photoName;
        this.review = review;
    }

    public static Photo createPhoto(String photoName, Review review) {
        return new Photo(photoName, review);
    }
}
