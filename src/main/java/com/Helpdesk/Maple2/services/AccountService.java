package com.Helpdesk.Maple2.services;

import com.Helpdesk.Maple2.entities.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {

    public Iterable<Account> findAll();

    public Account save(Account account);

    public Account findByUsername(String username);

    public void delete(int id);

    public Account find(int id);

}
