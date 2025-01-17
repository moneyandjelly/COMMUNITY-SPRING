package com.community.community.board.controller.request_form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class DeleteBoardRequestForm {
    final private String userToken;
    final private Long accountProfileId;
}
