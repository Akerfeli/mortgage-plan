package com.github.akerfeli.mortgageplanbackend.service;

import com.github.akerfeli.mortgageplanbackend.ProspectFileHandler;
import com.github.akerfeli.mortgageplanbackend.model.Prospect;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProspectService {

    public List<Prospect> getAllProspects() {
        ProspectFileHandler prospectFileHandler = new ProspectFileHandler("C:\\Users\\Felicia\\IdeaProjects\\Crosskey\\mortgage-plan\\data\\prospects.txt");
        return prospectFileHandler.getAllProspects();
    }

    public Prospect createProspect(Prospect prospect) throws IOException {
        ProspectFileHandler prospectFileHandler = new ProspectFileHandler("C:\\Users\\Felicia\\IdeaProjects\\Crosskey\\mortgage-plan\\data\\prospects.txt");
        prospectFileHandler.writeProspectToFile(prospect);
        return prospect;
    }

}