package com.community.community.board.service.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.community.community.board.entity.Board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ListBoardResponse {
    private final List<Board> boardList;
    private final long totalItems;
    private final int totalPages;

    public List<Map<String, Object>> getBoardListWithNicknames() {
        return boardList.stream().map(board -> {
            Map<String, Object> boardMap = new HashMap<>();
            boardMap.put("boardId", board.getBoardId());
            boardMap.put("title", board.getTitle());
            boardMap.put("content", board.getContent());
            boardMap.put("nickname", board.getWriter().getNickname());
            boardMap.put("createDate", formatDate(board.getCreateDate()));
            return boardMap;

        }).collect(Collectors.toList());
    }

    private String formatDate(LocalDateTime dateTime) {
        if (dateTime == null)
            return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }
}
