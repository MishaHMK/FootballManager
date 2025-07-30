package com.microapp.footballmanager.repository;

import com.microapp.footballmanager.model.Team;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamsRepository extends JpaRepository<Team, Long> {
    @EntityGraph(attributePaths = {"players", "players.positions"})
    Page<Team> findAll(Pageable pageable);

    @Query("SELECT t FROM Team t "
            + "LEFT JOIN FETCH t.players p "
            + "LEFT JOIN FETCH p.positions "
            + "WHERE t.id = :id")
    Optional<Team> getTeamByIdWithPlayers(Long id);
}
