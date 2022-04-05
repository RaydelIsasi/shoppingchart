package raydel.isasi.shopping.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import raydel.isasi.shopping.pojo.AuthenticationRequest;
import raydel.isasi.shopping.pojo.AuthenticationResponse;
import raydel.isasi.shopping.pojo.User;
import raydel.isasi.shopping.repository.IUser;
import raydel.isasi.shopping.service.CustomUserDetailService;
import raydel.isasi.shopping.service.JWTService;

import java.util.LinkedHashMap;


@RestController
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private IUser userService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private CustomUserDetailService customUserDetailService;


    @RequestMapping(value = "/registerUser", method = RequestMethod.POST, produces = {
            "application/json"}, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> registerUser(@RequestBody User usuario) throws Exception {


        return new ResponseEntity<Object>(userService.saveUser(usuario), HttpStatus.OK);

    }

    @PostMapping(value = "/login", produces = {
            "application/json"}, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> login(@RequestBody AuthenticationRequest request) throws Exception {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));


        final String token = jwtService.generateToken(customUserDetailService.loadUserByUsername(request.getUsername()), new LinkedHashMap<>());

        return new ResponseEntity<Object>( new AuthenticationResponse(token), HttpStatus.OK);

    }

}
