package com.nasnav.snapshot.services;

import com.nasnav.snapshot.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public long registerNewUser(User user);
    public void deleteExistingUser(long id);

}
