package com.juanjo.user_service.repository;

import com.juanjo.user_service.entity.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<User,Integer> {
}
