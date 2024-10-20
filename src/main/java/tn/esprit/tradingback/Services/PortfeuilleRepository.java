package tn.esprit.tradingback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import tn.esprit.tradingback.Services.Interfaces.IPortefeuilleService;

@Service
@RequestMapping("/portefeuille")
@RequiredArgsConstructor
public class PortfeuilleRepository implements IPortefeuilleService {
}
