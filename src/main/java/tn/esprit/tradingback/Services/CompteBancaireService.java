package tn.esprit.tradingback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import tn.esprit.tradingback.Repositories.CompteBancaireRepository;
import tn.esprit.tradingback.Services.Interfaces.ICompteBancaireService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequestMapping("/compteBancaire")
@RequiredArgsConstructor
public class CompteBancaireService implements ICompteBancaireService {




    @Autowired
    private CompteBancaireRepository compteBancaireRepository;

}
