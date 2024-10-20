package tn.esprit.tradingback.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Amortissement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idAmor;
    Float num;
    Date dateEcheance;
    Float montantEcheance;
    Float capitalRembourse;
    Float capitalRestantDu;

    @ManyToOne
    Obligation obligation;
}
