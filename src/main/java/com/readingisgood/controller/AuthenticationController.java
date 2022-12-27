package com.readingisgood.controller;

import com.readingisgood.configuration.JwtTokenUtil;
import com.readingisgood.dto.request.AuthenticationRequestDTO;
import com.readingisgood.dto.response.AuthenticationResponseDTO;
import com.readingisgood.dto.response.MessageResponse;
import com.readingisgood.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Api(value = "Authentication Api")
@RestController
public class AuthenticationController {

    private final JwtTokenUtil jwtTokenUtil;
    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

    public AuthenticationController(JwtTokenUtil jwtTokenUtil, CustomerService customerService, AuthenticationManager authenticationManager)
    {
        this.jwtTokenUtil = jwtTokenUtil;
        this.customerService = customerService;
        this.authenticationManager = authenticationManager;
    }

    @ApiOperation(value = "Generate JWT Token")
    @RequestMapping(value = "/generatetoken", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody AuthenticationRequestDTO authenticationRequest) throws Exception
    {
        try
        {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (DisabledException e)
        {
            logger.error("USER_DISABLED");
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("USER_DISABLED"));
        } catch (BadCredentialsException e)
        {
            logger.error("INVALID_CREDENTIALS");
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("INVALID_CREDENTIALS"));
        }

        UserDetails userDetails = customerService.loadUserByUsername(authenticationRequest.getUsername());

        String token = jwtTokenUtil.generateToken(userDetails);
        logger.info("jwt token is generated successfully!");
        return ResponseEntity.ok(new AuthenticationResponseDTO(token));
    }

}
