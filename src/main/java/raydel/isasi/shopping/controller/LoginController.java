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
import raydel.isasi.shopping.repository.UserRepository;
import raydel.isasi.shopping.service.CustomUserDetailService;
import raydel.isasi.shopping.service.JWTService;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;


@RestController
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JWTService jwtService;

    @Autowired
    CustomUserDetailService customUserDetailService;


    @RequestMapping(value = "/registerUser", method = RequestMethod.POST, produces = {
            "application/json"}, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> registerUser(@RequestBody User usuario) throws Exception {
        LOGGER.info("Initiating User persistence");
        org.springframework.security.core.userdetails.User user = null;
        usuario.setLast_login(new Date());
        usuario.setModified(new Date());
        usuario.setCreated(new Date());
        usuario.setIsactive(true);
        final String token = jwtService.generateToken(new org.springframework.security.core.userdetails.User(usuario.getName(), usuario.getPassword(), new ArrayList<>()), new LinkedHashMap<>());
        usuario.setToken(token);
        userRepository.save(usuario);


        return new ResponseEntity<Object>(usuario, HttpStatus.OK);

    }

    @PostMapping(value = "/login", produces = {
            "application/json"}, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> login(@RequestBody AuthenticationRequest request) throws Exception {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));


        final String token = jwtService.generateToken(customUserDetailService.loadUserByUsername(request.getUsername()), new LinkedHashMap<>());


        AuthenticationResponse authenticationResponse = new AuthenticationResponse(token);
        return new ResponseEntity<Object>(authenticationResponse, HttpStatus.OK);

    }

}
