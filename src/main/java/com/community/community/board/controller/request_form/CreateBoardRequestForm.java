package com.community.community.board.controller.request_form;

import com.community.community.board.service.request.CreateBoardRequest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class CreateBoardRequestForm {
    final private String title;
    final private String content;
    final private String userToken;
    final private String s3url;

    public CreateBoardRequest toCreateBoardRequest(Long accountId) {
        return new CreateBoardRequest(title, accountId, content, s3url);
    }
}
