package co.com.ancas.uses_cases.genres;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.Genre;
import co.com.ancas.models.repositories.GenresRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindGenreByValueAdapter implements IUseCase<String, Genre> {
    private final GenresRepositoryPort genresRepositoryPort;


    @Override
    public Genre execute(String s) throws MessagingException, IOException {
        Optional<Genre> genreFound = genresRepositoryPort.findByValue(s);
        if(genreFound.isEmpty()){
            throw new NotFoundException(Messages.MESSAGE_ERROR_GENRE_NOT_FOUND_BY_VALUE.getMessage().formatted(s));
        }
        return genreFound.get();
    }
}
