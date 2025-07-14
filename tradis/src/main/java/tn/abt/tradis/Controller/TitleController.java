package tn.abt.tradis.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.abt.tradis.Entites.Title;
import tn.abt.tradis.Repository.TitleRepository;

import java.util.List;

@RestController
@RequestMapping("/api/titles")
public class TitleController {

    @Autowired
    private TitleRepository titleRepository;

    @GetMapping
    public List<Title> getAllTitles() {
        return titleRepository.findAll();
    }

    @GetMapping("/{numDom}")
    public Title getTitleById(@PathVariable String numDom) {
        return titleRepository.findById(numDom)
                .orElseThrow(() -> new IllegalArgumentException("Title not found: " + numDom));
    }
}