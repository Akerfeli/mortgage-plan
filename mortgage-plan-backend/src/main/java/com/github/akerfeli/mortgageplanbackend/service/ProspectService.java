package com.github.akerfeli.mortgageplanbackend.service;

import com.github.akerfeli.mortgageplanbackend.ProspectFileHandler;
import com.github.akerfeli.mortgageplanbackend.model.Prospect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProspectService {

    private final ProspectFileHandler prospectFileHandler;
    @Autowired
    public ProspectService(ProspectFileHandler prospectFileHandler) {
        this.prospectFileHandler = prospectFileHandler;
    }

    public List<Prospect> getAllProspects() {;
        return this.prospectFileHandler.getAllProspects();
    }

    public Prospect createProspect(Prospect prospect) throws IOException {
        prospectFileHandler.writeProspectToFile(prospect);
        return prospect;
    }

}