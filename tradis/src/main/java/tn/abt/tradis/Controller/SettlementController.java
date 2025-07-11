package tn.abt.tradis.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.abt.tradis.Service.SettlementService;
import tn.abt.tradis.Entites.Settlement;
import tn.abt.tradis.Config.SettlementCreationRequest;

import java.util.List;

@RestController
@RequestMapping("/api/settlements")
public class SettlementController {


    @Autowired
    private  SettlementService settlementService;

    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/create")
    public Settlement createSettlement(@RequestBody SettlementCreationRequest request) {
        return settlementService.createSettlement(request);
    }

    @GetMapping
    public List<Settlement> getAllSettlements() {
        return settlementService.getAllSettlements();
    }

    @GetMapping("/{id}")
    public Settlement getSettlementById(@PathVariable Long id) {
        return settlementService.getSettlementById(id);
    }
}