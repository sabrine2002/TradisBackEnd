package tn.abt.tradis.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.abt.tradis.Config.TitleCreationRequest;
import tn.abt.tradis.Config.TitleDTO;
import tn.abt.tradis.Config.TitleWithLabelsDTO;
import tn.abt.tradis.Entites.Title;
import tn.abt.tradis.Repository.TitleRepository;
import tn.abt.tradis.Service.TitleService;
import org.springframework.http.converter.HttpMessageNotWritableException;

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
    private TitleRepository titleRepository;

    public TitleController(TitleService titleService) {
        this.titleService = titleService;
    }

    @PostMapping
    public ResponseEntity<?> createTitle(@RequestBody TitleCreationRequest request) {
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

    @GetMapping("/statuses")
    public ResponseEntity<List<String>> getTitleStatuses() {
        return ResponseEntity.ok(List.of("007", "008"));
    }

    @GetMapping("/currencies")
    public ResponseEntity<List<String>> getCurrencies() {
        return ResponseEntity.ok(List.of("USD", "EUR", "TND"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTitle(@PathVariable String id, @RequestBody TitleCreationRequest request) {
        try {
            Title updatedTitle = titleService.updateTitle(id, request);
            logger.info("Title updated successfully: {}", id);
            return ResponseEntity.ok(new TitleWithLabelsDTO(updatedTitle));
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

}