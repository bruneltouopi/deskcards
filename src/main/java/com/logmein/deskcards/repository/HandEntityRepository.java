package com.logmein.deskcards.repository;

import com.logmein.deskcards.domain.HandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Hand Entity Repository
 */

public interface HandEntityRepository extends JpaRepository<HandEntity,Long>{
}
