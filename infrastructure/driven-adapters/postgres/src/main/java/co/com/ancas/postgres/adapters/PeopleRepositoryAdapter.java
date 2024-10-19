package co.com.ancas.postgres.adapters;

import co.com.ancas.models.model.People;
import co.com.ancas.models.model.PeopleCreation;
import co.com.ancas.models.repositories.PeopleRepositoryPort;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.postgres.entities.PeopleEntity;
import co.com.ancas.postgres.repositories.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PeopleRepositoryAdapter implements PeopleRepositoryPort {
    private final PeopleRepository peopleRepository;
    @Override
    public People save(PeopleCreation people) {
        return Mapper.map(this.peopleRepository.save(Mapper.map(people, PeopleEntity.class)), People.class);
    }

    @Override
    public boolean verifyDni(String dni) {
        return this.peopleRepository.existsByDni(dni);
    }

    @Override
    public boolean verifyEmail(String email) {
        return this.peopleRepository.existsByEmail(email);
    }
}
