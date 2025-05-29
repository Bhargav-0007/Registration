package com.billspltr.Repo;

import com.billspltr.Entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
}
