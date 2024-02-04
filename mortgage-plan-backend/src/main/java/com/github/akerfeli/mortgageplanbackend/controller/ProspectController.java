package com.github.akerfeli.mortgageplanbackend.controller;
import com.github.akerfeli.mortgageplanbackend.dto.ProspectDTO;
import com.github.akerfeli.mortgageplanbackend.model.Prospect;
import com.github.akerfeli.mortgageplanbackend.service.ProspectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        double monthlyPayment =
                calculateMonthlyPayment(prospect.getTotalLoan(), prospect.getInterest(), prospect.getYears());

        return new ProspectDTO(
                prospect.getName(),
                prospect.getTotalLoan(),
                prospect.getInterest(),
                prospect.getYears(),
                monthlyPayment
        );
    }
}