package com.community.community.account.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 32, nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_type_id", nullable = false)
    private AccountRoleType roleType;

    public Account() {
    }

    public Account(String email, AccountRoleType roleType) {
        this.email = email;
        this.roleType = roleType;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountRoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(AccountRoleType roleType) {
        this.roleType = roleType;
    }
}
