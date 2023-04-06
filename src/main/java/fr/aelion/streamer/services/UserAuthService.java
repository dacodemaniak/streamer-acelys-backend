package fr.aelion.streamer.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import fr.aelion.streamer.dto.request.UserRequestDto;
import fr.aelion.streamer.entities.StreamerUser;
import fr.aelion.streamer.entities.UserRole;
import fr.aelion.streamer.errors.jwt.InvalidCredentialsException;
import fr.aelion.streamer.repositories.StreamerUserRepository;
import fr.aelion.streamer.repositories.UserRoleRepository;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAuthService implements UserDetailsService {
    @Autowired
    private StreamerUserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        // Get User from it's login
        StreamerUser user = userRepository.findByLogin(login).orElseThrow(
                () -> new InvalidCredentialsException()
        );

        // Get role for user
       UserRole role = user.getRole();

        // Get Granted Authorities from UserRoles
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));

        // Finally returns a full Core User
        return new org.springframework.security.core.userdetails.User(login, user.getPassword(), grantedAuthorities);
    }
    @Transactional
    public void add(UserRequestDto userDto) {
        if (this.userRepository.findByLogin((userDto.getLogin())).isPresent()) {
            // @todo Move to explicit exception (UserAlreadyExistsException)
            throw new RuntimeException("User already exists");
        }

        // Create a new instance of User
        StreamerUser user = modelMapper.map(userDto, StreamerUser.class);
        // Encrypt password
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        this.userRepository.save(user);

        user.setRole(userDto.getRole());


    }
}
