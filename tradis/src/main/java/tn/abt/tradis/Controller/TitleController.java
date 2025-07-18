package tn.abt.tradis.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.abt.tradis.Config.TitleCreationRequest;
import tn.abt.tradis.Entites.Title;
import tn.abt.tradis.Service.TitleService;

@RestController
@RequestMapping("/api/titles")
@CrossOrigin(origins = "*") // facultatif : pour autoriser les requêtes CORS
public class TitleController {

    private final TitleService titleService;

    // Constructeur explicite (pas besoin de Lombok)
    public TitleController(TitleService titleService) {
        this.titleService = titleService;
    }

    // Endpoint pour créer un titre
    @PostMapping("/titles")
    public ResponseEntity<Title> createTitle(@RequestBody TitleCreationRequest request) {
        Title title = titleService.createTitle(request);
        return ResponseEntity.ok(title);
    }

    // (optionnel) Récupérer tous les titres
    @GetMapping
    public ResponseEntity<?> getAllTitles() {
        return ResponseEntity.ok(titleService.getAllTitles());
    }

    // (optionnel) Récupérer un titre par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getTitleById(@PathVariable String id) {
        return ResponseEntity.ok(titleService.getTitleById(id));
    }
}
