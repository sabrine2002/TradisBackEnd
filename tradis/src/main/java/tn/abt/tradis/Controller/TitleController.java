package tn.abt.tradis.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tn.abt.tradis.Config.*;
import tn.abt.tradis.Entites.Client;
import tn.abt.tradis.Entites.Pnom;
import tn.abt.tradis.Entites.Title;
import tn.abt.tradis.Repository.ParameterRepository;
import tn.abt.tradis.Repository.TitleRepository;
import tn.abt.tradis.Service.TitleService;
import org.springframework.http.converter.HttpMessageNotWritableException;
import tn.abt.tradis.Service.UserDetailsImpl;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/titles")
@CrossOrigin(origins = "*")
public class TitleController {

    private static final Logger logger = LoggerFactory.getLogger(TitleController.class);

    private final TitleService titleService;
    @Autowired
    private ParameterRepository paramRepository;
    @Autowired
    private TitleRepository titleRepository;

    public TitleController(TitleService titleService) {
        this.titleService = titleService;
    }

    @PostMapping
    public ResponseEntity<?> createTitle(@RequestBody TitleCreationRequest request) {
        logger.info("Received request: {}", request);
        if (request.getClientId() == null) {
            logger.error("Client ID is null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client ID is required");
        }
        try {
            Title title = titleService.createTitle(request);
            logger.info("Title created successfully: {}", title.getNumDom());
            return ResponseEntity.ok(new TitleWithLabelsDTO(title));
        } catch (IllegalArgumentException e) {
            logger.error("Validation error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (HttpMessageNotWritableException e) {
            logger.error("Serialization error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur de sérialisation: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur serveur: " + e.getMessage());
        }
    }

    @GetMapping
    public List<TitleWithLabelsDTO> getAllTitles() {
        return titleService.getAllTitlesWithLabels();
    }

    @GetMapping("/with-labels")
    public List<TitleWithLabelsDTO> getAllTitlesWithLabels() {
        return titleService.getAllTitlesWithLabels();
    }

    @GetMapping("/{numDom}")
    public TitleDTO getTitleById(@PathVariable String numDom) {
        Title title = titleRepository.findById(numDom)
                .orElseThrow(() -> {
                    logger.error("Title not found: {}", numDom);
                    return new IllegalArgumentException("Title not found: " + numDom);
                });
        return new TitleDTO(title);
    }




    @GetMapping("/codes")
    public ResponseEntity<List<String>> getTitleCodes() {
        return ResponseEntity.ok(List.of("021", "022", "031", "033"));
    }




    @PutMapping("/{numDom}")
    public ResponseEntity<?>  updateTitle(
            @PathVariable String numDom,
            @RequestBody TitleUpdateRequest request
    ){     logger.info("Received request: {}", request);
        if (request.getClientId() == null) {
            logger.error("Client ID is null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client ID is required");
        }
        try {
            Title title = titleService.updateTitle(numDom,request);
            logger.info("Title created successfully: {}", title.getNumDom());
            return ResponseEntity.ok(new TitleWithLabelsDTO(title));
        } catch (IllegalArgumentException e) {
            logger.error("Validation error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (HttpMessageNotWritableException e) {
            logger.error("Serialization error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur de sérialisation: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur serveur: " + e.getMessage());
        }
    }


    @GetMapping("/count")
    public ResponseEntity<Long> countTitles() {
        return ResponseEntity.ok(titleRepository.countAllTitles());
    }


    @GetMapping("/created-by-month")
    public List<Map<String, Object>> getTitlesCreatedByMonth() {
        List<Title> titles = titleRepository.findAll();

        return titles.stream()
                .filter(title -> title.getDomDate() != null)
                .collect(Collectors.groupingBy(
                        title -> title.getDomDate().withDayOfMonth(1),  // Regroupe par début de mois
                        Collectors.counting()
                ))
                .entrySet().stream()
                .map(entry -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("x", entry.getKey());  // LocalDate (ex: 2025-08-01)
                    map.put("y", entry.getValue()); // Nombre de titres
                    return map;
                })
                .sorted(Comparator.comparing(m -> (LocalDate) m.get("x")))
                .collect(Collectors.toList());
    }
    @GetMapping("/currencies")
    public List<DropdownOptionTitle> getCurrencies() {
        List<Pnom> currencies = paramRepository.findByCnom("014");
        return currencies.stream()
                .map(p -> new DropdownOptionTitle(
                        p.getIdParam(),
                        p.getCnom(),
                        p.getLabel4() // Nom de la devise
                        // Code de la devise
                ))
                .collect(Collectors.toList());
    }
    @GetMapping("/statuses")
    public List<PnomDTO> getTitleStatuses() {
        return paramRepository.findByCnom("007")
                .stream()
                .map(pnom -> new PnomDTO(pnom.getCacc(), pnom.getLabel1()))
                .collect(Collectors.toList());
    }


    @GetMapping("/clients")
    public ResponseEntity<List<DropDownOptionT>> getAllClients() {
        List<Client> clients = titleService.getAllClients();
        List<DropDownOptionT> clientOptions = clients.stream()
                .map(client -> new DropDownOptionT(client.getIdCli(), client.getIdCli(), client.getFirstname()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientOptions);
    }

    @GetMapping("/by-user")
    public ResponseEntity<List<TitleWithLabelsDTO>> getTitlesByCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl userDetails) {
            Long userId = userDetails.getId();
            logger.info("Fetching titles for user ID: {}, Roles: {}", userId, userDetails.getAuthorities());
            List<TitleWithLabelsDTO> titles = titleService.getTitlesByUserId(userId);
            return ResponseEntity.ok(titles);
        } else {
            logger.error("Principal is not UserDetailsImpl: {}", principal);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @GetMapping("/count-by-user")
    public ResponseEntity<Long> countTitlesByCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl userDetails) {
            Long userId = userDetails.getId();
            logger.info("Counting titles for user ID: {}, Roles: {}", userId, userDetails.getAuthorities());
            return ResponseEntity.ok(titleRepository.countByUserId(userId));
        } else {
            logger.error("Principal is not UserDetailsImpl: {}", principal);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

}