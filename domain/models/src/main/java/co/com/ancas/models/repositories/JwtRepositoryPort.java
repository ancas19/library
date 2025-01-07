package co.com.ancas.models.repositories;

public interface JwtRepositoryPort {
    void save(String key, String value);
    String find(String key);
    void delete(String key);
}
