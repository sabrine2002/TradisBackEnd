package tn.abt.tradis.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.abt.tradis.Config.AdminCreationRequest;
import tn.abt.tradis.Config.AgentCreationRequest;
import tn.abt.tradis.Config.AgentDTO;
import tn.abt.tradis.Config.TitleUpdateDTO;
import tn.abt.tradis.Entites.Title;
import tn.abt.tradis.Repository.TitleRepository;
import tn.abt.tradis.Repository.UserRepository;
import tn.abt.tradis.Service.UserManagementService;

import java.util.List;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(TitleController.class);
    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private UserManagementService userManagementService;
    @Autowired
    private UserRepository userRepository;

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

    @PreAuthorize("hasRole('ADMIN') ")
    @GetMapping("/list-agents")
    public ResponseEntity<List<AgentDTO>> listAgents() {
        List<AgentDTO> agents = userManagementService.getAllAgents();
        return ResponseEntity.ok(agents);
    }
    @GetMapping("/count-agents")
    public ResponseEntity<Long> countAgents() {
        return ResponseEntity.ok(userManagementService.countAgents());
    }
    @GetMapping("/{numDom}")
    public ResponseEntity<TitleUpdateDTO> getTitleToUpdateById(@PathVariable String numDom) {
        try {
            Title title = titleRepository.findById(numDom)
                    .orElseThrow(() -> {
                        logger.error("Title not found: {}", numDom);
                        return new IllegalArgumentException("Title not found: " + numDom);
                    });
            return ResponseEntity.ok(new TitleUpdateDTO(title));
        } catch (IllegalArgumentException e) {
            logger.error("Title not found: {}", numDom);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
