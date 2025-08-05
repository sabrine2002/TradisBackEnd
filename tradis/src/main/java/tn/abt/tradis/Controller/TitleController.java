package tn.abt.tradis.Controller;

import lombok.AllArgsConstructor;
import lombok.Data;
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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/titles")
@CrossOrigin(origins = "*")
public class TitleController {

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
            return ResponseEntity.ok(title);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Title> getAllTitles() {
        return titleRepository.findAll();
    }
    @GetMapping("/with-labels")
    public List<TitleWithLabelsDTO> getAllTitlesWithLabels() {
        List<Title> titles = titleRepository.findAll();

        return titles.stream()
                .map(TitleWithLabelsDTO::new)  // pour chaque Title on crée un DTO
                .collect(Collectors.toList());
    }

    @GetMapping("/{numDom}")
    public TitleDTO getTitleById(@PathVariable String numDom) {
        Title title = titleRepository.findById(numDom)
                .orElseThrow(() -> new IllegalArgumentException("Title not found: " + numDom));
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
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTitle(@PathVariable String id, @RequestBody TitleCreationRequest request) {
        try {
            Title updatedTitle = titleService.updateTitle(id, request);
            return ResponseEntity.ok(updatedTitle);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error: " + e.getMessage());
        }
    }
}