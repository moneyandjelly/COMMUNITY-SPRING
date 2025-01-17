package com.community.community.board.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.community.community.board.controller.request_form.CreateBoardRequestForm;
import com.community.community.board.controller.request_form.DeleteBoardRequestForm;
import com.community.community.board.controller.request_form.ListBoardRequestForm;
import com.community.community.board.controller.response_form.ListBoardResponseForm;
import com.community.community.board.entity.Board;
import com.community.community.board.service.BoardService;
import com.community.community.board.service.response.ListBoardResponse;
import com.community.community.board.service.response.ReadBoardResponse;
import com.community.community.redis_cache.service.RedisCacheService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    final private BoardService boardService;
    final private RedisCacheService redisCacheService;

    @GetMapping("/list")
    public ListBoardResponseForm boardList(@ModelAttribute ListBoardRequestForm requestForm) {
        log.info("boardList() -> {}", requestForm);

        ListBoardResponse response = boardService.list(requestForm.toListBoardRequest());

        return ListBoardResponseForm.from(
                List.of(response),
                (int) response.getTotalItems(),
                response.getTotalPages());
    }

    @PostMapping("/create")
    public Board registerBoard(@RequestBody CreateBoardRequestForm createBoardRequestForm) {
        log.info("registerBoard() -> {}", createBoardRequestForm);

        Long accountId = redisCacheService.getValueByKey(createBoardRequestForm.getUserToken());
        log.info("accountId -> {}", accountId);

        return boardService.register(createBoardRequestForm.toCreateBoardRequest(accountId));
    }

    @GetMapping("/{boardId}")
    public ReadBoardResponse readBoard(@PathVariable("boardId") Long boardId) {
        log.info("boardRead()");

        return boardService.read(boardId);
    }

    @DeleteMapping("/{boardId}")
    public void deleteBoard(@PathVariable("boardId") Long boardId,
            @RequestBody DeleteBoardRequestForm deleteBoardRequestForm) {
        log.info("deleteBoard() -> {}", deleteBoardRequestForm);
        Long requestUserAccountId = redisCacheService.getValueByKey(deleteBoardRequestForm.getUserToken());
        log.info("작성자의 유저 ID", deleteBoardRequestForm.getAccountProfileId());
        log.info("요청한 유저 ID", requestUserAccountId);

        if (!deleteBoardRequestForm.getAccountProfileId().equals(requestUserAccountId)) {
            throw new RuntimeException("게시글 삭제 권한이 없습니다.");
        }

        if (!boardService.delete(boardId)) {
            throw new RuntimeException("게시글 존재하지 않거나 이미 삭제되었습니다.");
        }
    }
}
