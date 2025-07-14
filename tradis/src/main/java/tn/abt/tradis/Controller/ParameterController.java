package tn.abt.tradis.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.abt.tradis.Entites.Pnom;
import tn.abt.tradis.Repository.ParameterRepository;

import java.util.List;

@RestController
@RequestMapping("/api/parameters")
public class ParameterController {

    @Autowired
    private ParameterRepository parameterRepository;

    @GetMapping("/cnom/{cnom}")
    public List<Pnom> getParametersByCnom(@PathVariable String cnom) {
        return parameterRepository.findByCnom(cnom);
    }

    @GetMapping("/currency-by-country/{countryCode}")
    public Pnom getCurrencyByCountryCode(@PathVariable String countryCode) {
        return parameterRepository.findByCnomAndLabel5("013", countryCode)
                .orElseThrow(() -> new IllegalArgumentException("Currency not found for country code: " + countryCode));
    }



}