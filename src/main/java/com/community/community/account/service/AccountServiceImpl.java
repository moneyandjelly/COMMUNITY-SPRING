package com.community.community.account.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.community.community.account.entity.Account;
import com.community.community.account.entity.AccountRoleType;
import com.community.community.account.entity.RoleType;
import com.community.community.account.repository.AccountRepository;
import com.community.community.account.repository.AccountRoleTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    final private AccountRepository accountRepository;
    final private AccountRoleTypeRepository accountRoleTypeRepository;

    @Override
    public Account createAccount(String email) {
        AccountRoleType accountRoleType = new AccountRoleType(RoleType.NORMAL);
        AccountRoleType createdAccountRoleType = this.accountRoleTypeRepository.save(accountRoleType);

        Account account = new Account(email, createdAccountRoleType);
        return this.accountRepository.save(account);
    }

    @Override
    public Account findAccountByEmail(String email) {
        Optional<Account> accountOptional = accountRepository.findByEmail(email);
        return accountOptional.orElse(null);
    }
}
