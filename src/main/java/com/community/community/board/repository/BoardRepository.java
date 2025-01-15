package com.community.community.board.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.community.community.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("SELECT b FROM Board b JOIN FETCH b.writer ORDER BY b.boardId DESC")
    Page<Board> findAllWithWriter(PageRequest pageRequest);

    @Query("SELECT b FROM Board b JOIN FETCH b.writer w JOIN FETCH w.account WHERE b.boardId =:boardId")
    Optional<Board> findByIdWithWriter(Long boardId);
}
