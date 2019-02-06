package de.sappich.mealomat.repositories;

import de.sappich.mealomat.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
