package com.echohub.EchoHub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Comment {

        @Id
        Column(nullable = false, unique = true)
        private Long id; // уникальный идентификатор комментария
        @Column(nullable = false)
        private String content; // текст комментария
        @Column(name = "post_id", nullable = false)
        private Long postId; // идентификатор поста, к которому относится комментарий
        @Column(name = "user_id", nullable = false)
        private Long userId; // идентификатор пользователя, который оставил комментарий
        @Column(name = "created_at", nullable = false, updatable = false)
        private LocalDateTime createdAt; // дата и время создания комментария
        @Column(name = "updated_at")
        private LocalDateTime updatedAt; // дата и время последнего обновления комментария
        @PrePersist
        protected void onCreate() {
            createdAt = LocalDateTime.now();
            updatedAt = LocalDateTime.now();
        }
        @PreUpdate
        protected void onUpdate() {
            updatedAt = LocalDateTime.now();
        }

}
