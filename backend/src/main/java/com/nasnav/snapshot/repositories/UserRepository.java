package com.nasnav.snapshot.repositories;

import com.nasnav.snapshot.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    @Query("SELECT u FROM users u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);

    /*@Query("DELETE FROM users u WHERE u.id = :id")
    public void deleteUserById(@Param("id") long id);*/
}
