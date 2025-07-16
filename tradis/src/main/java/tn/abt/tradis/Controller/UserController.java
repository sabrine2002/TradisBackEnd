package tn.abt.tradis.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.abt.tradis.Config.AdminCreationRequest;
import tn.abt.tradis.Config.AgentCreationRequest;
import tn.abt.tradis.Service.UserManagementService;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private  UserManagementService userManagementService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-agent")
    public ResponseEntity<?> createAgent(@Valid @RequestBody AgentCreationRequest request) {
        return userManagementService.createAgent(request);
    }
    @PreAuthorize("hasRole('SUPERADMIN')")
    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@Valid @RequestBody AdminCreationRequest request) {
        return userManagementService.createAdmin(request);
    }
    @PreAuthorize("hasRole('SUPERADMIN')")
    @GetMapping("/list-admins")
    public ResponseEntity<?> listAdmins() {
        return userManagementService.listAdmins();
    }
}
