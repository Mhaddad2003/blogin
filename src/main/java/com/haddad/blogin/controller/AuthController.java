package com.haddad.blogin.controller;
import com.haddad.blogin.dto.BloggerDTO;
import com.haddad.blogin.dto.RegistrationRequestDTO;
import com.haddad.blogin.model.Blogger;
import com.haddad.blogin.service.BloggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")


public class AuthController {
    private BloggerService bloggerService;

    @Autowired
    public AuthController(BloggerService bloggerService) {
        this.bloggerService = bloggerService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<BloggerDTO> register(@RequestBody RegistrationRequestDTO requestDto) {
        try {

            Blogger newBlogger = bloggerService.createBlogger(requestDto);

            BloggerDTO responseDto = new BloggerDTO(
                    newBlogger.getUsername(),
                    newBlogger.getEmail()
            );

            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
