package de.sappich.mealomat.Repositories;

import de.sappich.mealomat.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
