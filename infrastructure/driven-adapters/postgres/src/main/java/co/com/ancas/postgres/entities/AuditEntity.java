package co.com.ancas.postgres.entities;

import co.com.ancas.models.utils.Constants;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class AuditEntity {
    @Column(name = "user_created", nullable = false, updatable = false)
    private String userCreated;

    @Column(name = "updated_user")
    private String userUpdated;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    public void preUpdateFunction(){
        String user =  SecurityContextHolder.getContext().getAuthentication().getName();
        this.updatedAt = LocalDateTime.now();
        this.userUpdated = Objects.isNull(user)? Constants.SYSTEM: user;
    }

    @PrePersist
    public void prePersistFunction(){
        String user =  SecurityContextHolder.getContext().getAuthentication().getName();
        this.createdAt = LocalDateTime.now();
        this.userCreated =Objects.isNull(user)? Constants.SYSTEM: user;
    }
}
