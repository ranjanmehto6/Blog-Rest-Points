//package com.example.blogrestpoints.controller;
//
//
//import com.example.blogrestpoints.security.JwtAuthResponse;
//import com.example.blogrestpoints.security.JwtTokenHelper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/auth")
//public class AuthController {
//
//
//    @Autowired
//    private JwtTokenHelper jwtTokenHelper;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//
//    @PostMapping("/login")
//    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request){
//
//        this.authenticate(request.getUserName(), request.getPassword());
//        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUserName());
//
//      String token =   this.jwtTokenHelper.generateToken(userDetails);
//        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
//        jwtAuthResponse.setToken(token);
//        return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse, HttpStatus.OK);
//
//
//    }
//
//    private void authenticate(String username, String password){
//
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);
//        try {
//            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//
//
//        }catch (DisabledException d){
//            d.printStackTrace();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//
//}
