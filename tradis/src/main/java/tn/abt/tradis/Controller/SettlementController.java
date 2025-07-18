package tn.abt.tradis.Controller;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.abt.tradis.Config.SettlementDTO;
import tn.abt.tradis.Config.SettlementUpdateRequest;
import tn.abt.tradis.Service.SettlementService;
import tn.abt.tradis.Entites.Settlement;
import tn.abt.tradis.Config.SettlementCreationRequest;

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
    public Settlement getSettlementById(@PathVariable Long id) {
        return settlementService.getSettlementById(id);
    }

    @PreAuthorize("hasRole('AGENT')")
    @PutMapping("/{id}")
    public Settlement updateSettlement(@PathVariable Long id, @RequestBody SettlementUpdateRequest request) {
        return settlementService.updateSettlement(id, request);
    }
}