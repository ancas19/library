package co.com.ancas.models.repositories;


import co.com.ancas.models.model.Author;

public interface AuthorsRepositoryPort {
    Author save(Author build);
}
