package com.haddad.blogin.service;


import com.haddad.blogin.dto.BloggerDTO;
import com.haddad.blogin.dto.RegistrationRequestDTO;
import com.haddad.blogin.model.Role;
import com.haddad.blogin.repository.BloggerRepo;
import com.haddad.blogin.model.Blogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BloggerService {

    private final BloggerRepo bloggerRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public BloggerService(BloggerRepo bloggerRepo, PasswordEncoder passwordEncoder) {
        this.bloggerRepo = bloggerRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Blogger createBlogger(RegistrationRequestDTO requestDto) throws Exception {

        if (bloggerRepo.existsByUsername(requestDto.getUsername())) {
            throw new Exception("Error: Username is already taken!");
        }

        if (bloggerRepo.existsByEmail(requestDto.getEmail())) {
            throw new Exception("Error: Email is already in use!");
        }

        Blogger blogger = new Blogger();
        blogger.setUsername(requestDto.getUsername());
        blogger.setEmail(requestDto.getEmail());

        String hashedPassword = passwordEncoder.encode(requestDto.getPassword());
        blogger.setPassword(hashedPassword);

        blogger.setRole(Role.USER);

        return bloggerRepo.save(blogger);
    }
}
