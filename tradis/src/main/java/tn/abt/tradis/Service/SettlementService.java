package tn.abt.tradis.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.abt.tradis.Entites.Settlement;
import tn.abt.tradis.Repository.SettlementRepository;

import java.util.*;

@Service
public class SettlementService {
    @Autowired
    private SettlementRepository settlementRepository;

    public List<Settlement> GetAllSettlements() {
        return settlementRepository.findAll();
    }

    public Optional<Settlement> FindSettlementById(Long id) {
        return settlementRepository.findById(id);
    }

   public void CreateSettlement(Long id,Settlement settlement) {
       Settlement existingSettlement = settlementRepository.findById(id)
               .orElseThrow(() -> new IllegalArgumentException("Règlement non trouvé."));





       settlementRepository.save(settlement);
   }




}
