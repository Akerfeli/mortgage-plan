package com.github.akerfeli.mortgageplanbackend.controller;
import com.github.akerfeli.mortgageplanbackend.dto.ProspectDTO;
import com.github.akerfeli.mortgageplanbackend.model.Prospect;
import com.github.akerfeli.mortgageplanbackend.service.ProspectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.akerfeli.mortgageplanbackend.MortgageCalculator.calculateMonthlyPayment;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
@RequestMapping("/prospects")
public class ProspectController {

    private final ProspectService prospectService;

    @Autowired
    public ProspectController(ProspectService prospectService) {
        this.prospectService = prospectService;
    }

    @GetMapping
    public ResponseEntity<List<ProspectDTO>> getAllProspects() {
        List<Prospect> prospects = prospectService.getAllProspects();
        List<ProspectDTO> prospectDTOs = prospects.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(prospectDTOs, HttpStatus.OK);
    }

    private ProspectDTO convertToDTO(Prospect prospect) {
        int monthlyPayment =
                calculateMonthlyPayment(prospect.getTotalLoanCents(), prospect.getInterestRateBps(), prospect.getYears());

        return new ProspectDTO(
                prospect.getName(),
                prospect.getTotalLoanCents(),
                prospect.getInterestRateBps(),
                prospect.getYears(),
                monthlyPayment
        );
    }

    @PostMapping
    public ResponseEntity<Prospect> createProspect(@RequestBody Prospect prospect) {
        try {
            Prospect createdProspect = prospectService.createProspect(prospect);
            return new ResponseEntity<>(createdProspect, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}