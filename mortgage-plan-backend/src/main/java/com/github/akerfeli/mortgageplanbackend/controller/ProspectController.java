package com.github.akerfeli.mortgageplanbackend.controller;
import com.github.akerfeli.mortgageplanbackend.model.Prospect;
import com.github.akerfeli.mortgageplanbackend.service.ProspectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prospects")
public class ProspectController {

    private final ProspectService prospectService;

    @Autowired
    public ProspectController(ProspectService prospectService) {
        this.prospectService = prospectService;
    }

    @GetMapping
    public ResponseEntity<List<Prospect>> getAllProspects() {
        List<Prospect> prospects = prospectService.getAllProspects();
        return new ResponseEntity<>(prospects, HttpStatus.OK);
    }
}