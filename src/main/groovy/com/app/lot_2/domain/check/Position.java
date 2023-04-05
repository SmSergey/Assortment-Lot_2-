package com.app.lot_2.domain.check;

import com.app.lot_2.domain.check.Check;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Position {

    @Id
    @GeneratedValue
    private UUID id;

    private BigDecimal sum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Check check;

    public Position(BigDecimal sum, Check check) {
        this.sum = sum;
        this.check = check;
    }
}
