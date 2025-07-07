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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.abt.tradis.Auth.Reponse.JwtResponse;
import tn.abt.tradis.Auth.Reponse.MessageResponse;
import tn.abt.tradis.Auth.Request.LoginRequest;
import tn.abt.tradis.Entites.Role;
import tn.abt.tradis.Entites.User;
import tn.abt.tradis.Enum.RoleName;
import tn.abt.tradis.Repository.RoleRepository;
import tn.abt.tradis.Repository.UserRepository;
import tn.abt.tradis.Service.UserDetailsImpl;
import tn.abt.tradis.Utils.JwtUtils;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

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

//
//
//    @PostMapping("/admin/agents")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<?> createAgent(@Valid @RequestBody AgentCreationRequest agentRequest) {
//        // Vérifier si le nom d'utilisateur existe déjà
//        if (userRepository.existsByUsername(agentRequest.getUsername())) {
//            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
//        }
//        // Vérifier si l'email existe déjà
//        if (userRepository.existsByEmail(agentRequest.getEmail())) {
//            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
//        }
//        // Vérifier si le code d'agence est valide
//        if (!paramRepository.existsByCode(agentRequest.getAgencyCode())) {
//            return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid agency code!"));
//        }
//
//        // Créer un nouvel utilisateur
//        User user = new User();
//        user.setUsername(agentRequest.getUsername());
//        user.setEmail(agentRequest.getEmail());
//        String rawPassword = generateRandomPassword(12);
//        user.setPassword(encoder.encode(rawPassword));
//        user.setLastName(agentRequest.getNom());
//        user.setFirstName(agentRequest.getPrenom());
//        user.setAgencyCode(agentRequest.getAgencyCode());
//
//        // Assigner le rôle AGENT
//        Role agentRole = roleRepository.findByName(RoleName.ROLE_AGENT)
//                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//        user.setRoles(Set.of(agentRole));
//
//        // Enregistrer l'utilisateur
//        userRepository.save(user);
//
//        // Envoyer l'e-mail de création de compte
//        try {
//            emailService.sendAccountCreationEmail(
//                    agentRequest.getEmail(),
//                    agentRequest.getPrenom(),
//                    agentRequest.getNom(),
//                    agentRequest.getEmail(),
//                    rawPassword
//            );
//        } catch (Exception e) {
//            // En cas d'erreur d'envoi d'e-mail, enregistrer l'erreur mais ne pas bloquer la création
//            System.err.println("Failed to send email: " + e.getMessage());
//        }
//
//        return ResponseEntity.ok(new MessageResponse("Agent created successfully! Credentials sent to email."));
//    }
//
//    private String generateRandomPassword(int length) {
//        if (length < 12) {
//            throw new IllegalArgumentException("Password length must be at least 12 characters.");
//        }
//        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
//        StringBuilder password = new StringBuilder();
//        SecureRandom random = new SecureRandom();
//        password.append(getRandomChar("ABCDEFGHIJKLMNOPQRSTUVWXYZ", random)); // Majuscule
//        password.append(getRandomChar("abcdefghijklmnopqrstuvwxyz", random)); // Minuscule
//        password.append(getRandomChar("0123456789", random)); // Chiffre
//        password.append(getRandomChar("!@#$%^&*()", random)); // Caractère spécial
//        for (int i = 4; i < length; i++) {
//            password.append(chars.charAt(random.nextInt(chars.length())));
//        }
//        char[] passwordArray = password.toString().toCharArray();
//        for (int i = passwordArray.length - 1; i > 0; i--) {
//            int j = random.nextInt(i + 1);
//            char temp = passwordArray[i];
//            passwordArray[i] = passwordArray[j];
//            passwordArray[j] = temp;
//        }
//        return new String(passwordArray);
//    }
//
//    private char getRandomChar(String chars, SecureRandom random) {
//        return chars.charAt(random.nextInt(chars.length()));
//    }
}
