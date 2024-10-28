package co.com.ancas.postgres.repositories;

import co.com.ancas.postgres.entities.EmailTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplateEntity, Long> {
    @Query("SELECT e.body FROM EmailTemplateEntity e WHERE e.subject = :subject")
    String findBySubject(String subject);
}
