package com.microapp.footballmanager.repository;

import com.microapp.footballmanager.model.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    @EntityGraph(attributePaths = {"player", "fromTeam", "toTeam"})
    Page<Transfer> findAll(Pageable pageable);

    @Query("SELECT t FROM Transfer t "
            + "LEFT JOIN FETCH t.player "
            + "LEFT JOIN FETCH t.fromTeam "
            + "LEFT JOIN FETCH t.toTeam "
            + "WHERE t.id = :id")
    Transfer getTransferWithData(Long id);
}
