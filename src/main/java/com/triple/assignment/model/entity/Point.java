package com.triple.assignment.model.entity;

import com.triple.assignment.model.entity.common.BaseTimeEntity;
import com.triple.assignment.model.enums.EventActionType;
import com.triple.assignment.model.enums.EventType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.util.UUID;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "point", indexes = {
        @Index(name = "idx__user", columnList = "user_id"),
        @Index(name = "idx__user__createDatetime", columnList = "user_id, createdDatetime")
})
public class Point extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private User user;

    private int point;

    @Enumerated(EnumType.STRING)
    private EventActionType actionType;

    @Enumerated(EnumType.STRING)
    private EventType type;

    private String content;

    private int appliedPoint;

    private Point(User user, int point, EventActionType actionType, EventType type, String content, int appliedPoint) {
        this.user = user;
        this.point = point;
        this.actionType = actionType;
        this.type = type;
        this.content = content;
        this.appliedPoint = appliedPoint;
    }

    public static Point createPoint(User user, int point, EventActionType actionType, EventType type, String content, int appliedPoint) {
        return new Point(user, point, actionType, type, content, appliedPoint);
    }

    public void updatePointHistory(int point, int appliedPoint, String content) {
        this.point = point;
        this.appliedPoint = appliedPoint;
        this.content = content;
    }
}
