package com.Helpdesk.Maple2.services;

import com.Helpdesk.Maple2.entities.Account;
import com.Helpdesk.Maple2.entities.Tickets;
import com.Helpdesk.Maple2.repositories.AccountRepository;
import com.Helpdesk.Maple2.repositories.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ticketsService")
public class TicketsServiceImpl implements TicketsService {

    @Autowired
    private TicketsRepository ticketsRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Tickets save(Tickets tickets) {
        return ticketsRepository.save(tickets);
    }

    @Override
    public List<Tickets> findTicketsByUsername(String username) {
        Account account = accountRepository.findByUsername(username);
        return ticketsRepository.findTicketsById(account.getId());
    }
}
