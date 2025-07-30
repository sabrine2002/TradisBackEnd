package tn.abt.tradis.Controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.abt.tradis.Config.TitleCreationRequest;
import tn.abt.tradis.Entites.Title;
import tn.abt.tradis.Service.TitleService;

import java.util.List;

@RestController
@RequestMapping("/api/titles")
@CrossOrigin(origins = "*")
public class TitleController {

    private final TitleService titleService;

    public TitleController(TitleService titleService) {
        this.titleService = titleService;
    }

    @PostMapping
    public ResponseEntity<?> createTitle(@RequestBody TitleCreationRequest request) {
        try {
            Title title = titleService.createTitle(request);
            return ResponseEntity.ok(title);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Title>> getAllTitles() {
        return ResponseEntity.ok(titleService.getAllTitles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTitleById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(titleService.getTitleById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/codes")
    public ResponseEntity<List<String>> getTitleCodes() {
        return ResponseEntity.ok(List.of("021", "022", "031", "033"));
    }

    @GetMapping("/statuses")
    public ResponseEntity<List<String>> getTitleStatuses() {
        return ResponseEntity.ok(List.of("007", "008"));
    }


    // Classe DTO pour la réponse
    @Data
    @AllArgsConstructor
    class CurrencyResponse {
        private String code;  // USD, EUR, etc.
        private String name;  // Dollar des USA, Euro, etc.
    }
    @GetMapping("/currencies")
    public ResponseEntity<List<String>> getCurrencies() {
        return ResponseEntity.ok(List.of("USD", "EUR", "TND"));
    }
}