package com.haddad.blogin.controller;
import com.haddad.blogin.dto.BloggerDTO;
import com.haddad.blogin.dto.RegistrationRequestDTO;
import com.haddad.blogin.model.Blogger;
import com.haddad.blogin.repository.BloggerRepo;
import com.haddad.blogin.service.BloggerService;
import com.haddad.blogin.service.EmailSenderService;
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
    private final BloggerRepo bloggerRepo;
    private BloggerService bloggerService;

    @Autowired
    public AuthController(BloggerService bloggerService, BloggerRepo bloggerRepo) {
        this.bloggerService = bloggerService;
        this.bloggerRepo = bloggerRepo;
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

    @PostMapping(value = "/forget-password")
    public ResponseEntity forgetPassword(@RequestBody String email) {

        if(email == null || email.isEmpty()){
             return new ResponseEntity("Email is required!", HttpStatus.BAD_REQUEST);
        }
        else{
            if(!bloggerRepo.existsByEmail(email)){
                return new ResponseEntity("Email is not registered!", HttpStatus.BAD_REQUEST);
            }
            else{
                EmailSenderService emailSenderService = new EmailSenderService();
                String subject = "Password Reset Request";
                String body = "";
                emailSenderService.sendEmail(email, subject, body);
                return new ResponseEntity("Password reset email sent successfully!", HttpStatus.OK);
            }
        }
    }


}
