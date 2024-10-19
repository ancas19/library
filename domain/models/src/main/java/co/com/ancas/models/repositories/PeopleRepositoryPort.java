package co.com.ancas.models.repositories;

import co.com.ancas.models.model.People;
import co.com.ancas.models.model.PeopleCreation;

public interface PeopleRepositoryPort {

    People save(PeopleCreation people);
    boolean verifyDni(String dni);

    boolean verifyEmail(String email);
}
