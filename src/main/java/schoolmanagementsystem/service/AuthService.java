package schoolmanagementsystem.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import schoolmanagementsystem.dto.AuthRequest;
import schoolmanagementsystem.dto.AuthResponse;
import schoolmanagementsystem.dto.RegisterRequest;
import schoolmanagementsystem.entity.User;
import schoolmanagementsystem.repository.UserRepository;
import schoolmanagementsystem.security.JwtUtil;
import schoolmanagementsystem.util.Role;

import java.util.Set;

@Component
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtUtil.generateToken(user, Set.of(user.getRoles().toString()));
        return new AuthResponse(token);
    }

    public void register(RegisterRequest request) {
        User user = new User(request.getUsername(), request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getFullName(),request.getPhoneNumber(), Set.of(Role.STUDENT));
        userRepository.save(user);
    }
}
