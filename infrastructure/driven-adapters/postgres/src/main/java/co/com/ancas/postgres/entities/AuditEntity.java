package co.com.ancas.postgres.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
public class AuditEntity {
    @Column(name = "user_created", nullable = false, updatable = false)
    private String userCreated;

    @Column(name = "user_updated")
    private String userUpdated;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    public void preUpdateFunction(){
        this.updatedAt = LocalDateTime.now();
        this.userUpdated ="SYSTEM";
    }

    @PrePersist
    public void prePersistFunction(){
        this.createdAt = LocalDateTime.now();
        this.userCreated ="SYSTEM";
    }
}
