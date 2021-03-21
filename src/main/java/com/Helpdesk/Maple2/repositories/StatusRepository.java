package com.Helpdesk.Maple2.repositories;

import com.Helpdesk.Maple2.entities.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("statusRepository")
public interface StatusRepository extends CrudRepository<Status, Integer> {


}
