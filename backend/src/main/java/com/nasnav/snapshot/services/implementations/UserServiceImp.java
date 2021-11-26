package com.nasnav.snapshot.services.implementations;

import com.nasnav.snapshot.entities.User;
import com.nasnav.snapshot.entities.UserRole;
import com.nasnav.snapshot.repositories.UserRepository;
import com.nasnav.snapshot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public long registerNewUser(User user) {

        if(user.getUsername().equals("admin"))
            user.setRole(UserRole.Admin);
        else
            user.setRole(UserRole.User);

        // Encrypt password
        user.setPassword(encoder.encode(user.getPassword()));

        user = userRepository.save(user);

        return user.getId();
    }

    @Override
    public void deleteExistingUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent())
            userRepository.delete(optionalUser.get());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        if(user == null)
            throw new UsernameNotFoundException("User " + username + " Not Found");

        return new UserPrincipal(user);
    }
}
