package tn.abt.tradis.Controller;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.abt.tradis.Config.SettlementDTO;
import tn.abt.tradis.Config.SettlementUpdateRequest;
import tn.abt.tradis.Config.SettlementWithLabelsDTO;
import tn.abt.tradis.Enum.SettlementStatus;
import tn.abt.tradis.Repository.SettlementRepository;
import tn.abt.tradis.Service.SettlementService;
import tn.abt.tradis.Entites.Settlement;
import tn.abt.tradis.Config.SettlementCreationRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
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
private SettlementRepository settlementRepository;

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
}