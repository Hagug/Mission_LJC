package com.example.myboard.repo;

import com.example.myboard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByTopic(String topic);
}
