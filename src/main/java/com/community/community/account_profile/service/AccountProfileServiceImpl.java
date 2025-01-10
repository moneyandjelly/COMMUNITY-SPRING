package com.community.community.account_profile.service;

import org.springframework.stereotype.Service;

import com.community.community.account.entity.Account;
import com.community.community.account_profile.entity.AccountProfile;
import com.community.community.account_profile.repository.AccountProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountProfileServiceImpl implements AccountProfileService {
    final private AccountProfileRepository accountProfileRepository;

    @Override
    public AccountProfile createAccountProfile(Account account, String nickname) {
        AccountProfile accountProfile = new AccountProfile(account, nickname);
        return this.accountProfileRepository.save(accountProfile);
    }

}
