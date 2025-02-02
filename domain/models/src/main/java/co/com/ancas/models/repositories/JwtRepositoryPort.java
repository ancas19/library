package co.com.ancas.models.repositories;

import co.com.ancas.models.model.TokenInformation;

public interface JwtRepositoryPort {
    void save(String key, TokenInformation value);
    TokenInformation find(String key);
    void delete(String key);
}
