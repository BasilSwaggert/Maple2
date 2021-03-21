package com.Helpdesk.Maple2.services;

import com.Helpdesk.Maple2.entities.Status;
import com.Helpdesk.Maple2.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("statusService")
public class StatusServiceImpl implements StatusService{

    @Autowired
    private StatusRepository statusRepository;


    @Override
    public Iterable<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    public Status find(int id) {
        return statusRepository.findById(id).get();
    }

    @Override
    public Status save(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public void delete(int id) {
        statusRepository.deleteById(id);
    }
}
