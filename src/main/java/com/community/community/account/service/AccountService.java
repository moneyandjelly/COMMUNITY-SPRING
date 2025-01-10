package com.community.community.account.service;

import com.community.community.account.entity.Account;

public interface AccountService {
    Account createAccount(String email);

    Account findAccountByEmail(String email);
}
