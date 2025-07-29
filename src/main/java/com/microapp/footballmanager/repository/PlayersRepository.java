package com.microapp.footballmanager.repository;

import com.microapp.footballmanager.model.Player;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayersRepository extends JpaRepository<Player, Long> {
    @EntityGraph(attributePaths = {"team", "positions"})
    Page<Player> findAll(Pageable pageable);

    @Query("SELECT p FROM Player p "
            + "LEFT JOIN FETCH p.team "
            + "LEFT JOIN FETCH p.positions "
            + "WHERE p.id = :id")
    Optional<Player> getPlayerByIdWithTeam(Long id);
}
