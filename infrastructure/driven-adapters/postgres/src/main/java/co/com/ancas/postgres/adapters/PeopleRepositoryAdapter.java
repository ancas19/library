package co.com.ancas.postgres.adapters;

import co.com.ancas.models.model.People;
import co.com.ancas.models.model.PeopleCreation;
import co.com.ancas.models.model.PeopleFullInfomration;
import co.com.ancas.models.model.PeopleSearchCriteria;
import co.com.ancas.models.repositories.PeopleRepositoryPort;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.postgres.entities.PeopleEntity;
import co.com.ancas.postgres.repositories.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public Page<People> findPeopleByCriteria(PeopleSearchCriteria peopleSearchCriteria) {
        return this.peopleRepository
                .findPeopleByCriteria(
                        formatString(peopleSearchCriteria.getSearch()),
                        peopleSearchCriteria.getRole(),
                        peopleSearchCriteria.getPageable()
                )
                .map(peopleEntity -> Mapper.map(peopleEntity, People.class));
    }

    @Override
    public Optional<PeopleFullInfomration> findPeopleFullInformation(Long idPeople) {
        return this.peopleRepository
                .findPeopleFullInformation(idPeople);
    }

    @Override
    public Optional<People> findPeopleById(Long aLong) {
        return this.peopleRepository.findById(aLong).map(peopleEntity -> Mapper.map(peopleEntity, People.class));
    }

    @Override
    public People update(People people) {
        return Mapper.map(this.peopleRepository.save(Mapper.map(people, PeopleEntity.class)),People.class);
    }

    @Override
    public boolean verifyDniExists(String dni, Long id) {
        return this.peopleRepository.existsByDniAndNotId(dni,id);
    }

    private String formatString(String string) {
        return "%%%s%%".formatted(string.toLowerCase());
    }
}
