package com.community.community.account_profile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.community.community.account.entity.Account;
import com.community.community.account_profile.entity.AccountProfile;

public interface AccountProfileRepository extends JpaRepository<AccountProfile, Long> {
    Optional<AccountProfile> findByAccount(Account account);

}
