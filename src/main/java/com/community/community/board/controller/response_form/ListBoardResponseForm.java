package com.community.community.board.controller.response_form;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.community.community.board.service.response.ListBoardResponse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ListBoardResponseForm {
    private final List<Map<String, Object>> boardList;
    private final int totalItems;
    private final int totalPages;

    public static ListBoardResponseForm from(List<ListBoardResponse> boardListResponse, int totalItems, int totalPages){
        List<Map<String, Object>> combinedBoardList = boardListResponse.stream()
        .flatMap(response -> response.getBoardListWithNicknames().stream())
        .collect(Collectors.toList());

        return new ListBoardResponseForm(combinedBoardList, totalItems, totalPages);
    }
}
