package co.com.ancas.models.repositories;

import co.com.ancas.models.model.Attempt;

public interface AttemptRepositoryPort {
    void save(Attempt attempt);
    Attempt find(String key);
    void delete(String key);
}
