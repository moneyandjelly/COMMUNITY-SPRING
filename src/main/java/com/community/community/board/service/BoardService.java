package com.community.community.board.service;

import com.community.community.board.service.request.ListBoardRequest;
import com.community.community.board.service.response.ListBoardResponse;

public interface BoardService {
    ListBoardResponse list(ListBoardRequest request);

}
