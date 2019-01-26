package de.sappich.mealomat.Repositories;

import de.sappich.mealomat.Entities.UserComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCommentRepository extends JpaRepository<UserComment, String> {
}
