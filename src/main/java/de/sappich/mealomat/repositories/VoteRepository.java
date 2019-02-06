package de.sappich.mealomat.repositories;

import de.sappich.mealomat.entities.User;
import de.sappich.mealomat.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findAllBydateVotedAndUser(Date date, User user);

    Collection<Vote> findAllByDateVoted(Date date);

    void deleteByUserAndDateVoted(User user, Date date);
}
