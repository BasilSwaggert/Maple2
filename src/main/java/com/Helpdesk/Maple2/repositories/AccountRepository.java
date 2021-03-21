package com.Helpdesk.Maple2.repositories;

import com.Helpdesk.Maple2.entities.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("accountRepository")
public interface AccountRepository extends CrudRepository<Account, Integer> {

    public Account findByUsername(String username);
}
