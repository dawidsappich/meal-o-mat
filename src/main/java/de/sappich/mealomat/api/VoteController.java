package de.sappich.mealomat.api;

import de.sappich.mealomat.api.viewmodel.VoteViewModel;
import de.sappich.mealomat.entities.Meal;
import de.sappich.mealomat.entities.User;
import de.sappich.mealomat.entities.Vote;
import de.sappich.mealomat.repositories.MealRepository;
import de.sappich.mealomat.repositories.UserRepository;
import de.sappich.mealomat.repositories.VoteRepository;
import de.sappich.mealomat.services.UserDetailsServiceImpl;
import de.sappich.mealomat.utils.ApplicationCode;
import de.sappich.mealomat.utils.ApplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;


@RestController
@Transactional
@RequestMapping("vote")
public class VoteController {

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private VoteRepository voteRepository;

    @GetMapping
    public Collection<Vote> getAllVotes() {
        return this.voteRepository.findAllByDateVoted(Date.valueOf(LocalDate.now()));
    }

    @PostMapping
    public ApplicationResponse createVote(@RequestBody VoteViewModel votemodel, Principal principal) {
        User user = getUser(principal);

        //check if user is allowed to vote
        // user is allowed to vote once per day
        Optional<Vote> foundVotes = this.voteRepository.findAllBydateVotedAndUser(Date.valueOf(LocalDate.now()), user);

        // if already voted then return
        ApplicationResponse.Builder builder = new ApplicationResponse.Builder();
        if (foundVotes.isPresent()) {
            return ApplicationResponse.createResponse("user has already voted", ApplicationCode.ERROR);
        }


        Vote vote = new Vote();
        vote.setUser(user);

        Meal meal = mealRepository.findById(votemodel.getMealId()).get();
        vote.setMeal(meal);

        vote.setDateVoted(Date.valueOf(LocalDate.now()));
        voteRepository.save(vote);
        return ApplicationResponse.createResponse("user has voted", ApplicationCode.OK);
    }

    @DeleteMapping
    public ApplicationResponse revokeVote(Principal principal) {
        User user = getUser(principal);
        this.voteRepository.deleteByUserAndDateVoted(user, Date.valueOf(LocalDate.now()));
        return ApplicationResponse.createResponse("vote for today is revoked", ApplicationCode.OK);
    }

    private User getUser(Principal principal) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(principal.getName());
        String username = userDetails.getUsername();
        return this.userRepository.findByEmailIgnoreCase(username).get();
    }

}
