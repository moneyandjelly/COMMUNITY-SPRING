package com.community.community.board.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.community.community.board.controller.request_form.ListBoardRequestForm;
import com.community.community.board.controller.response_form.ListBoardResponseForm;
import com.community.community.board.service.BoardService;
import com.community.community.board.service.response.ListBoardResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    final private BoardService boardService;
    @GetMapping("/list")
    public ListBoardResponseForm boardList(@ModelAttribute ListBoardRequestForm requestForm) {
        log.info("boardList() -> {}", requestForm);

        ListBoardResponse response = boardService.list(requestForm.toListBoardRequest());

        return ListBoardResponseForm.from(
            List.of(response),
            (int) response.getTotalItems(),
            response.getTotalPages());
    }

}
