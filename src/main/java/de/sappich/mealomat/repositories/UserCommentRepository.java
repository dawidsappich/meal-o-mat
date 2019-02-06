package de.sappich.mealomat.repositories;

import de.sappich.mealomat.entities.UserComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCommentRepository extends JpaRepository<UserComment, Long> {
}
