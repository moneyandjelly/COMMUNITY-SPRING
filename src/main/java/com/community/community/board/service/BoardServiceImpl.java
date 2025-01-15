package com.community.community.board.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.community.community.account.entity.Account;
import com.community.community.account.repository.AccountRepository;
import com.community.community.account_profile.entity.AccountProfile;
import com.community.community.account_profile.repository.AccountProfileRepository;
import com.community.community.board.entity.Board;
import com.community.community.board.repository.BoardRepository;
import com.community.community.board.service.request.CreateBoardRequest;
import com.community.community.board.service.request.ListBoardRequest;
import com.community.community.board.service.response.ListBoardResponse;
import com.community.community.board.service.response.ReadBoardResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    final private BoardRepository boardRepository;
    final private AccountRepository accountRepository;
    final private AccountProfileRepository accountProfileRepository;

    @Override
    public ListBoardResponse list(ListBoardRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getPerPage());

        Page<Board> boardPage = boardRepository.findAllWithWriter(pageRequest);

        return new ListBoardResponse(boardPage.getContent(), boardPage.getTotalElements(), boardPage.getTotalPages());
    }

    @Override
    public Board register(CreateBoardRequest createBoardRequest) {
        Account account = accountRepository.findById(createBoardRequest.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account 존재하지 않음"));

        AccountProfile accountProfile = accountProfileRepository.findByAccount(account)
                .orElseThrow(() -> new RuntimeException("AccountProfile not found"));

        return boardRepository.save(createBoardRequest.toBoard(accountProfile));
    }

    @Override
    public ReadBoardResponse read(Long boardId) {
        Optional<Board> maybeBoard = boardRepository.findByIdWithWriter(boardId);

        if (maybeBoard.isEmpty()) {
            log.info("정보가 없습니다");
            return null;
        }

        Board board = maybeBoard.get();
        return ReadBoardResponse.from(board);
    }

    @Override
    public boolean delete(Long boardId) {
        Optional<Board> maybeBoard = boardRepository.findById(boardId);
        if (maybeBoard.isEmpty()) {
            return false;
        }

        boardRepository.deleteById(boardId);

        return !boardRepository.existsById(boardId);
    }

}
