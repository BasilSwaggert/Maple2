package com.Helpdesk.Maple2.services;

import com.Helpdesk.Maple2.entities.Status;

public interface StatusService {

    public Iterable<Status> findAll();

    public Status find(int id);

    public Status save(Status status);

    public void delete(int id);
}
