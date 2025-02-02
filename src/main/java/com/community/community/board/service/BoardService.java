package com.community.community.board.service;

import com.community.community.board.entity.Board;
import com.community.community.board.service.request.CreateBoardRequest;
import com.community.community.board.service.request.ListBoardRequest;
import com.community.community.board.service.response.ListBoardResponse;
import com.community.community.board.service.response.ReadBoardResponse;

public interface BoardService {
    ListBoardResponse list(ListBoardRequest request);

    Board register(CreateBoardRequest createBoardRequest);

    ReadBoardResponse read(Long boardId);

    public boolean delete(Long boardId);
}
