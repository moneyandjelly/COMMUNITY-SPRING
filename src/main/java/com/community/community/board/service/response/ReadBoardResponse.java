package com.community.community.board.service.response;

import java.time.LocalDateTime;

import com.community.community.board.entity.Board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReadBoardResponse {
    private final Long boardId;
    private final String title;
    private final String nickname;
    private final String content;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;
    private final Long writerAccountId;

    public static ReadBoardResponse from(Board board) {
        return new ReadBoardResponse(board.getBoardId(), board.getTitle(), board.getWriter().getNickname(),
                board.getContent(), board.getCreateDate(), board.getUpdateDate(), board.getWriter().getId());
    }
}
