package com.Helpdesk.Maple2.services;

import com.Helpdesk.Maple2.entities.Tickets;

import java.util.List;

public interface TicketsService {

    public Tickets save(Tickets tickets);

    public List<Tickets> findTicketsByUsername(String username);
}
