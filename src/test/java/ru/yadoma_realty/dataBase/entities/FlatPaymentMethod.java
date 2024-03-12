package ru.yadoma_realty.dataBase.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class FlatPaymentMethod {
    @Column(name = "mortgage")
    private Integer mortgage;

    @Column(name = "military_mortgage")
    private Integer militaryMortgage;

    @Column(name = "subsidy")
    private Integer subsidy;

    @Column(name = "installment")
    private Integer installment;
}
