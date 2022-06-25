package com.triple.assignment.model.entity;

import com.triple.assignment.model.entity.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "review" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photo> photos;

    @Lob
    private String content;

    public Review(Place place, User user, List<Photo> photos, String content) {
        this.place = place;
        this.user = user;
        this.photos = photos;
        this.content = content;
    }

    public static Review createReview(Place place, User user, List<Photo> photos, String content) {
        return new Review(place, user, photos, content);
    }

    public boolean isWriter(UUID id) {
        return this.user.getId().equals(id);
    }

    public boolean hasPhotos() {
        return !CollectionUtils.isEmpty(this.photos);
    }

    public boolean hasContent() {
        return Strings.isNotBlank(this.content);
    }
}
