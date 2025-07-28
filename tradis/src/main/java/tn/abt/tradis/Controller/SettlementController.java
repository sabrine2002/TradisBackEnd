package tn.abt.tradis.Controller;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.abt.tradis.Config.*;
import tn.abt.tradis.Entites.Pnom;
import tn.abt.tradis.Repository.ParameterRepository;
import tn.abt.tradis.Service.SettlementService;
import tn.abt.tradis.Entites.Settlement;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;
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
    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/filter")
    public List<SettlementDTO> filterSettlements(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Integer countryId,
            @RequestParam(required = false) Integer currencyId,
            @RequestParam(required = false) Integer productId,
            @RequestParam(required = false) String numDom,
            @RequestParam(required = false) BigDecimal minAmountLC,
            @RequestParam(required = false) BigDecimal maxAmountLC,
            @RequestParam(required = false) BigDecimal minAmountFC,
            @RequestParam(required = false) BigDecimal maxAmountFC,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        List<Settlement> settlements = settlementService.filterSettlements(
                id, countryId, currencyId, productId, numDom,
                minAmountLC, maxAmountLC, minAmountFC, maxAmountFC,
                startDate, endDate
        );

        return settlements.stream()
                .map(SettlementDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/countries")
    public List<DropdownOption> getCountries() {
        List<Pnom> countries = paramRepository.findByCnom("013");
        return countries.stream()
                .map(p -> new DropdownOption(
                        p.getIdParam(),
                        p.getLabel3(), // Nom du pays
                        p.getLabel2()  // Code du pays
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
                        p.getLabel2()  // Code de la devise
                ))
                .collect(Collectors.toList());
    }


}