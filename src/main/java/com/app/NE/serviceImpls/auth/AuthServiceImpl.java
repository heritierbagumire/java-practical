package com.app.NE.serviceImpls.auth;

import com.app.NE.dto.requests.LoginDTO;
import com.app.NE.dto.requests.RegisterDTO;
import com.app.NE.dto.responses.ApiResponse;
import com.app.NE.enums.ERole;
import com.app.NE.exceptions.BadRequestException;
import com.app.NE.exceptions.UnauthorisedException;
import com.app.NE.models.Role;
import com.app.NE.models.User;
import com.app.NE.repositories.IRoleRepository;
import com.app.NE.repositories.IUserRepository;
import com.app.NE.security.jwt.JwtService;
import com.app.NE.services.auth.IAuthService;
import com.app.NE.utils.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private final IRoleRepository roleRepository;
    @Value("${app.security.admin-key}")
    private String adminKey;

    @Override
    public ApiResponse login(LoginDTO dto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        if(authentication.isAuthenticated()){
            // get the user
            User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(
                    () -> new BadRequestException("Invalid credentials given.")
            );


            String token = jwtService.generateToken(user);
            ApiResponse<String> response = new ApiResponse<>(token, "User logged in successfully.");
            return response;
        }
        throw new UnauthorisedException("Invalid credentials given.");
    }

    @Override
    public ApiResponse register(RegisterDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent() || userRepository.findByPhone(dto.getPhone()).isPresent() || userRepository.findByNationalId(dto.getNationalId()).isPresent()) {
            throw new BadRequestException("A user with either this email or phone or national id already exists.");
        }

        ERole requestedRole = dto.getRole() != null ? dto.getRole() : ERole.ROLE_CUSTOMER;
        Role role = roleRepository.findByRole(requestedRole)
                .orElseThrow(() -> new BadRequestException("Role not found."));

        if (requestedRole == ERole.ROLE_ADMIN) {
            String expectedKey = adminKey;
            if (dto.getAdminCreationKey() == null || !dto.getAdminCreationKey().equals(expectedKey)) {
                throw new UnauthorisedException("ADMIN CREATION KEY mismatch, contact the team in charge.");
            }
        }

        User user = new User();
        user.setNames(dto.getNames());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setNationalId(dto.getNationalId());
        user.setPassword(Utility.hash(dto.getPassword()));
        user.setRole(role);

        userRepository.save(user);

        String token = jwtService.generateToken(user);

        return new ApiResponse<>(token, "User registered successfully");
    }

    @Override
    public User getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with email: %s not found.", email)));
    }

    @Override
    public ApiResponse getLoggedInUser() {
        return new ApiResponse(getPrincipal(), null);
    }
}
