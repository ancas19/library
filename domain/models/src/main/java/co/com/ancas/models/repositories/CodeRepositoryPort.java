package co.com.ancas.models.repositories;

import co.com.ancas.models.model.Code;

public interface CodeRepositoryPort {
    void save(Code code);
    Code find(String key);
    void delete(String key);
}
