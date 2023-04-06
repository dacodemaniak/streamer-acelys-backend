package fr.aelion.streamer.controllers;

import fr.aelion.streamer.components.JwtUtil;
import fr.aelion.streamer.dto.SignupMessage;
import fr.aelion.streamer.dto.request.UserRequestDto;
import fr.aelion.streamer.dto.request.UserResponseDto;
import fr.aelion.streamer.entities.StreamerUser;
import fr.aelion.streamer.errors.jwt.DisabledUserException;
import fr.aelion.streamer.errors.jwt.InvalidCredentialsException;
import fr.aelion.streamer.services.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    UserAuthService userAuthService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("login")
    public UserResponseDto generateJwtToken(@RequestBody UserRequestDto request) {
        try {
            Authentication authentication = this.authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getLogin(),
                                    request.getPassword()
                            )
                    );
            // Got a Spring Security User
            User user = (User) authentication.getPrincipal();

            Set<String> roles = user
                    .getAuthorities()
                    .stream().map(r -> r.getAuthority())
                    .collect(Collectors.toSet());

            // Make a token from "authentication" object
            String token = jwtUtil.generateToken(authentication);

            // Create a Response DTO to send to client
            UserResponseDto response = new UserResponseDto();
            response.setJwtToken(token);
            response.setRoles(roles.stream().collect(Collectors.toList()));

            return response;

        } catch (DisabledException e) {
            throw new DisabledUserException();
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException();
        }
    }

    @PostMapping("signup")
    public SignupMessage signup(@RequestBody UserRequestDto request) {
        this.userAuthService.add(request);
        SignupMessage message = new SignupMessage("User was successfully registred");
        return message;
    }
}
