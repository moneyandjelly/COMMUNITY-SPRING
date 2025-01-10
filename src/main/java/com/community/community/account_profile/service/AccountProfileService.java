package com.community.community.account_profile.service;

import com.community.community.account.entity.Account;
import com.community.community.account_profile.entity.AccountProfile;

public interface AccountProfileService {
    AccountProfile createAccountProfile(Account account, String nickname);
}
