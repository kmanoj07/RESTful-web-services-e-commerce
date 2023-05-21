package com.kumarmanoj.ecommerce.service;

import com.kumarmanoj.ecommerce.dto.userDTO.SignUpDTO;
import com.kumarmanoj.ecommerce.dto.userDTO.SigninDTO;
import com.kumarmanoj.ecommerce.dto.userDTO.SigninResponseDTO;
import com.kumarmanoj.ecommerce.exceptions.AuthenticationFailedException;
import com.kumarmanoj.ecommerce.exceptions.CustomException;
import com.kumarmanoj.ecommerce.model.AuthenticationToken;
import com.kumarmanoj.ecommerce.model.User;
import com.kumarmanoj.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired private AuthenticationTokenService authenticationService;

    @Transactional
    public User signup(SignUpDTO signUpDTO) {
        // check if user is already present
        User alreadyUser = userRepository.findByEmail(signUpDTO.getEmail());
        if(alreadyUser != null){
            throw new CustomException("User already present");
        }
        // hash/encrypt the password
        String encryptedPassword = signUpDTO.getPassword();
        try {
            encryptedPassword = hashPassword(signUpDTO.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
//            throw new CustomException(e.getMessage());
        }
        // save the user
        User user = new User();
        user.setFirstName(signUpDTO.getFirstName());
        user.setLastName(signUpDTO.getLastName());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(encryptedPassword);

        User createdUser =  userRepository.save(user);
        // generate the token
        AuthenticationToken authenticationToken = new AuthenticationToken(createdUser);
        authenticationService.saveConfirmationToken(authenticationToken);
        return createdUser;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());
        byte[]  digest = messageDigest.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SigninResponseDTO signin(SigninDTO signinDTO) {
        // find user by email
        User user = userRepository.findByEmail(signinDTO.getEmail());
        if(Objects.isNull(user)) {
            throw new AuthenticationFailedException("user email or password invalid");
        }
        // we have to hash the password
        try {
            String hashPassword = hashPassword(signinDTO.getPassword());
            // compare the password in db
            if (!user.getPassword().equals(hashPassword)) {
                throw new AuthenticationFailedException("user email or password invalid");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // if password matched
        AuthenticationToken token = authenticationService.getToken(user);
        // retrieve the token
        if(Objects.isNull(token)) {
            throw new CustomException("Authentication token is not present");
        }
        // return the response;
        return new SigninResponseDTO("success",  token.getToken());
    }
}
