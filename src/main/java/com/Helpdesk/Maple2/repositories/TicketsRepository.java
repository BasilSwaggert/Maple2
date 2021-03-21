package com.Helpdesk.Maple2.repositories;

import com.Helpdesk.Maple2.entities.Tickets;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ticketsRepository")
public interface TicketsRepository extends CrudRepository<Tickets, Integer> {

    @Query("from Ticket where accountByEmployeeId.id = :id")
    public List<Tickets> findTicketsById(@Param("id") int id);
}
