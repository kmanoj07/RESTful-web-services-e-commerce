package com.kumarmanoj.ecommerce.controller;

import com.kumarmanoj.ecommerce.dto.userDTO.ResponseDTO;
import com.kumarmanoj.ecommerce.dto.userDTO.SignUpDTO;
import com.kumarmanoj.ecommerce.dto.userDTO.SigninDTO;
import com.kumarmanoj.ecommerce.dto.userDTO.SigninResponseDTO;
import com.kumarmanoj.ecommerce.model.User;
import com.kumarmanoj.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 2 apis

    // signup
    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> signup(@RequestBody SignUpDTO signUpDTO) {
         User user = userService.signup(signUpDTO);
         if(user != null)
            return new ResponseEntity<ResponseDTO>(new ResponseDTO(true, "User created successfully"), HttpStatus.CREATED);

         return new ResponseEntity<ResponseDTO>(new ResponseDTO(false, "error in signup"), HttpStatus.BAD_REQUEST);
    }

    // signin
    @RequestMapping(path = "/signin", method = RequestMethod.POST)
    public SigninResponseDTO signin(@RequestBody SigninDTO signinDTO) {
        return userService.signin(signinDTO);
    }

}
