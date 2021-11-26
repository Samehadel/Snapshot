package com.nasnav.snapshot.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasnav.snapshot.models.LoginRequestModel;
import com.nasnav.snapshot.services.implementations.UserPrincipal;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private AuthenticationManager authManager;

    public AuthenticationFilter(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            LoginRequestModel creds = new ObjectMapper()
                    .readValue(request.getInputStream(), LoginRequestModel.class);


            return authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>()));

        }catch(BadCredentialsException ex) {

            // Respond status Unauthorized
            response.setStatus(401);

        }catch(Exception e) {
            e.printStackTrace();

            // Internal server error
            response.setStatus(500);
        }

        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        JwtUtils jwtUtils = new JwtUtils();
        String token = jwtUtils.getJWT(auth);

        List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) auth.getAuthorities();

        res.addHeader("Role", authorities.get(0).getAuthority());
        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
    }

}