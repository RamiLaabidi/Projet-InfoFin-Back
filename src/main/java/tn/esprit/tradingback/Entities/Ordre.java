package tn.esprit.tradingback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.tradingback.Entities.Enums.TypeOrdre;

import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Ordre implements Serializable {
    @Id
    @Column(name ="idOrdre")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrdre;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private Float  prix;
    private Long quantite;
    @Enumerated(EnumType.STRING)
    private TypeOrdre typeOrdre;
    @JsonIgnore
    @ManyToOne()
    private Portfeuille portfeuille;

    @ManyToOne()
    private ProduitFinancier produitFinancier;
}