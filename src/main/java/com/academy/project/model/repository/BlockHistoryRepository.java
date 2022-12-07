package com.academy.project.model.repository;

import com.academy.project.model.entity.BlockHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockHistoryRepository extends JpaRepository<BlockHistory, Integer> {
}
