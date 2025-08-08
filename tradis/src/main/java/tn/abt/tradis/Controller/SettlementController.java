package tn.abt.tradis.Controller;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.abt.tradis.Config.*;
import tn.abt.tradis.Entites.Pnom;
import tn.abt.tradis.Entites.Title;
import tn.abt.tradis.Enum.SettlementStatus;
import tn.abt.tradis.Repository.ParameterRepository;
import tn.abt.tradis.Repository.SettlementRepository;
import tn.abt.tradis.Repository.TitleRepository;
import tn.abt.tradis.Service.SettlementService;
import tn.abt.tradis.Entites.Settlement;
import tn.abt.tradis.Service.TitleService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/settlements")
@RequiredArgsConstructor
public class SettlementController {


    @Autowired
    private  EntityManager entityManager;
    @Autowired
    private  SettlementService settlementService;
    @Autowired
    private SettlementRepository settlementRepository;
    @Autowired
    private ParameterRepository paramRepository;

    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/create")
    public Settlement createSettlement(@RequestBody SettlementCreationRequest request) {
        return settlementService.createSettlement(request);
    }

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/getAllSettlements")
    public List<SettlementDTO> getAllSettlements() {
        List<Settlement> settlements = entityManager.createQuery("SELECT s FROM Settlement s", Settlement.class)
                .getResultList();
        return settlements.stream()
                .map(SettlementDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SettlementWithLabelsDTO getSettlementById(@PathVariable Long id) {
        return settlementService.getSettlementById(id);
    }

    @PreAuthorize("hasRole('AGENT')")
    @PutMapping("/{id}")
    public Settlement updateSettlement(@PathVariable Long id, @RequestBody SettlementUpdateRequest request) {
        return settlementService.updateSettlement(id, request);
    }
    @GetMapping("/filter")
    public List<SettlementWithLabelsDTO> filterSettlements(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) BigDecimal minAmountLC,
            @RequestParam(required = false) BigDecimal maxAmountLC,
            @RequestParam(required = false) BigDecimal minAmountFC,
            @RequestParam(required = false) BigDecimal maxAmountFC,
            @RequestParam(required = false) Long countryId,
            @RequestParam(required = false) Long currencyId,
            @RequestParam(required = false) String numDom,
            @RequestParam(required = false) SettlementStatus status
    ) {
        return settlementService.filterSettlements(
                startDate, endDate, minAmountLC, maxAmountLC, minAmountFC, maxAmountFC, countryId, currencyId, numDom, status
        );
    }

    @GetMapping("/countries")
    public List<DropdownOption> getCountries() {
        List<Pnom> countries = paramRepository.findByCnom("013");
        return countries.stream()
                .map(p -> new DropdownOption(
                        p.getIdParam(),
                        p.getLabel3(), // Nom du pays
                        p.getCnom()  // Code du pays
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/currencies")
    public List<DropdownOption> getCurrencies() {
        List<Pnom> currencies = paramRepository.findByCnom("014");
        return currencies.stream()
                .map(p -> new DropdownOption(
                        p.getIdParam(),
                        p.getLabel4(), // Nom de la devise
                        p.getCnom()  // Code de la devise
                ))
                .collect(Collectors.toList());
    }
    @GetMapping("/count")
    public long getTotalSettlements() {
        return settlementService.countAllSettlements();
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getSettlementStats() {
        return ResponseEntity.ok(settlementService.getSettlementStats());
    }



}