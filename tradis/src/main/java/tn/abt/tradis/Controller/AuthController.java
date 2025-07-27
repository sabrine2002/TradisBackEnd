package tn.abt.tradis.Controller;

import java.security.SecureRandom;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.abt.tradis.Auth.Reponse.JwtResponse;
import tn.abt.tradis.Auth.Request.LoginRequest;

import tn.abt.tradis.Service.UserDetailsImpl;
import tn.abt.tradis.Utils.JwtUtils;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

// @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping("/admin")
//    public ResponseEntity<?> createAgent(@Valid @RequestBody AgentCreationRequest agentRequest) {
//
//        if (userRepository.existsByUsername(agentRequest.getUsername())) {
//            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
//        }
//
//        if (userRepository.existsByEmail(agentRequest.getEmail())) {
//            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
//        }
//
//        // Utilisation correcte de findByCacc au lieu de findByCode
//        Pnom agency = paramRepository.findByCacc(agentRequest.getAgencyCode())
//                .orElseThrow(() -> new RuntimeException("Error: Invalid agency code!"));
//
//        String rawPassword = generateRandomPassword(12);
//
//        User user = new User();
//        user.setUsername(agentRequest.getUsername());
//        user.setFirstname(agentRequest.getFirstname());
//        user.setLastname(agentRequest.getLastname());
//        user.setEmail(agentRequest.getEmail());
//        user.setPassword(encoder.encode(rawPassword));
//        user.setAgencyCode(agency);
//
//        Role agentRole = roleRepository.findByName(RoleName.ROLE_AGENT)
//                .orElseThrow(() -> new RuntimeException("Error: Role not found."));
//
//        user.setRoles(Set.of(agentRole));
//
//        userRepository.save(user);
//
//        try {
//            emailService.sendAccountCreationEmail(
//                    agentRequest.getEmail(),
//                    agentRequest.getUsername(),
//                    rawPassword
//            );
//        } catch (Exception e) {
//            System.err.println("Failed to send email: " + e.getMessage());
//        }
//
//        return ResponseEntity.ok(new MessageResponse("Agent created successfully!"));
//    }
//
//    private String generateRandomPassword(int length) {
//        if (length < 12) {
//            throw new IllegalArgumentException("Password length must be at least 12 characters.");
//        }
//
//        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        String lower = "abcdefghijklmnopqrstuvwxyz";
//        String digits = "0123456789";
//        String special = "!@#$%^&*()";
//        String allChars = upper + lower + digits + special;
//
//        SecureRandom random = new SecureRandom();
//        StringBuilder password = new StringBuilder();
//
//        password.append(upper.charAt(random.nextInt(upper.length())));
//        password.append(lower.charAt(random.nextInt(lower.length())));
//        password.append(digits.charAt(random.nextInt(digits.length())));
//        password.append(special.charAt(random.nextInt(special.length())));
//
//        for (int i = 4; i < length; i++) {
//            password.append(allChars.charAt(random.nextInt(allChars.length())));
//        }
//
//        char[] pwdArray = password.toString().toCharArray();
//        for (int i = pwdArray.length - 1; i > 0; i--) {
//            int j = random.nextInt(i + 1);
//            char tmp = pwdArray[i];
//            pwdArray[i] = pwdArray[j];
//            pwdArray[j] = tmp;
//        }
//
//        return new String(pwdArray);
//    }
}
