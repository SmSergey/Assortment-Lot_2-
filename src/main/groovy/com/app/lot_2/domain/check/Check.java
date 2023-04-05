package com.app.lot_2.domain.check;


import com.app.lot_2.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "user_checks")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Check {

    @Id
    private UUID id;

    @ManyToOne
    @JsonBackReference
    private User owner;

    private BigDecimal sum;

    private String cardNumber;

    @JsonManagedReference
    @OneToMany(mappedBy = "check", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Position> positions;

    public Check(UUID id) {
        this.id = id;
    }

    public Check(User owner, BigDecimal sum, String cardNumber) {
        this.id = UUID.randomUUID();
        this.owner = owner;
        this.sum = sum;
        this.cardNumber = cardNumber;
    }
}
