package org.niit.UserTrackService.repository;

import org.niit.UserTrackService.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    public User findByUserId(String userId);
}
