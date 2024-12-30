package co.com.ancas.uses_cases.genres;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.repositories.GenresRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCaseResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class FindAllGenresAdapter implements IUseCaseResult<List<String>> {
    private final GenresRepositoryPort genresRepositoryPort;
    @Override
    public List<String> execute() {
        List<String> genresFound = genresRepositoryPort.findAll();
        if(genresFound.isEmpty()){
            throw new NotFoundException(Messages.MESSAGE_GENRES_NOT_FOUND.getMessage());
        }
        return genresFound;
    }
}
