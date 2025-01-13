package com.community.community.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.community.community.board.entity.Board;
import com.community.community.board.repository.BoardRepository;
import com.community.community.board.service.request.ListBoardRequest;
import com.community.community.board.service.response.ListBoardResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    final private BoardRepository boardRepository;
    @Override
    public ListBoardResponse list(ListBoardRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getPerPage());

        Page<Board> boardPage = boardRepository.findAllWithWriter(pageRequest);

        return new ListBoardResponse(boardPage.getContent(), boardPage.getTotalElements(), boardPage.getTotalPages());
    }

}
