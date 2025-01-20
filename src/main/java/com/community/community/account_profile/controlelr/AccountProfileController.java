package com.community.community.account_profile.controlelr;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.community.community.account.entity.Account;
import com.community.community.account.repository.AccountRepository;
import com.community.community.account_profile.entity.AccountProfile;
import com.community.community.account_profile.repository.AccountProfileRepository;
import com.community.community.board.controller.request_form.CreateBoardRequestForm;
import com.community.community.board.service.response.ReadBoardResponse;
import com.community.community.redis_cache.service.RedisCacheService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class AccountProfileController {
    final private RedisCacheService redisCacheService;
    final private AccountRepository accountRepository;
    final private AccountProfileRepository accountProfileRepository;

    @GetMapping("/userinfo")
    public AccountProfile responseUserInfo(@RequestHeader("Authorization") String userToken) {
        String token = userToken.replace("Bearer ", "");
        Long accountId = redisCacheService.getValueByKey(token);
        log.info("accountId -> {}", accountId);
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account 존재하지 않음"));
        AccountProfile accountProfile = accountProfileRepository.findByAccount(account)
                .orElseThrow(() -> new RuntimeException("AccountProfile not found"));
        return accountProfile;
    }

}
