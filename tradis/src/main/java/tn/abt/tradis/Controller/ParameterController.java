package tn.abt.tradis.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.abt.tradis.Config.SettlementWithLabelsDTO;
import tn.abt.tradis.Entites.Pnom;
import tn.abt.tradis.Repository.ParameterRepository;
import tn.abt.tradis.Service.SettlementService;

import java.util.List;

@RestController
@RequestMapping("/api/parameters")
public class ParameterController {

    @Autowired
    private ParameterRepository parameterRepository;

    @Autowired
    private SettlementService settlementService;

    // Existant : récupère les paramètres par cnom (ex: '013' pour pays)
    @GetMapping("/cnom/{cnom}")
    public List<Pnom> getParametersByCnom(@PathVariable String cnom) {
        return parameterRepository.findByCnom(cnom);
    }

    // Nouvelle méthode : récupérer un paramètre par cnom + idParam (idparam dans la base)
    @GetMapping("/{cnom}/id_param/{id_param}")
    public Pnom getParameterByCnomAndIdParam(
            @PathVariable String cnom,
            @PathVariable Long id_param) {

        return parameterRepository.findByCnomAndIdParam(cnom, id_param)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Paramètre non trouvé pour cnom: " + cnom + " et idParam: " + id_param));
    }
    @GetMapping("/label4/{id_param}")
    public String getLabel4ByIdParam(@PathVariable Long id_param) {
        Pnom param = parameterRepository.findByIdParam(id_param)
                .orElseThrow(() -> new IllegalArgumentException("Paramètre non trouvé pour id_param : " + id_param));
        return param.getLabel4();
    }

    @GetMapping("/settlements/with-labels")
    public List<SettlementWithLabelsDTO> getAllSettlementsWithLabels() {
        return settlementService.getAllSettlementsWithLabels();
    }


}