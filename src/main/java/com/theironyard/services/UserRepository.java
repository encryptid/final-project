package com.theironyard.services;

import com.theironyard.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by stephenwilliamson on 2/18/17.
 */
public interface UserRepository extends CrudRepository<User, Integer>{
    User findFirstByName(String name);
    User findFirstBysessionId(String sessionId);

}
