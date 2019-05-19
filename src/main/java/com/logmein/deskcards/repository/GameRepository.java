package com.logmein.deskcards.repository;

import com.logmein.deskcards.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Game Entity Repository
 */

public interface GameRepository extends JpaRepository<Game,Long>{
    @Query(value = "SELECT g FROM Game g")
    List<Game> findAll();
}
