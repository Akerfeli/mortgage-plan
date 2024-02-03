package com.github.akerfeli.mortgageplanbackend.service;

import com.github.akerfeli.mortgageplanbackend.ProspectFileHandler;
import com.github.akerfeli.mortgageplanbackend.model.Prospect;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProspectService {

    public List<Prospect> getAllProspects() {
        ProspectFileHandler prospectFileHandler = new ProspectFileHandler();
        return prospectFileHandler.getAllProspects();
    }
}