package com.app.lot_2.domain.user;

import com.app.lot_2.domain.check.Check;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Table(name = "users", indexes = @Index(columnList = "cardNumber"))
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Size(max = 20)
    @Column(unique = true)
    private String cardNumber;

    private BigDecimal points;

    private BigDecimal checksSum;

    @JsonManagedReference
    @OneToMany(mappedBy = "owner", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Check> checks;

    @UpdateTimestamp
    private Date updatedAt;

    public User(String cardNumber, BigDecimal points, BigDecimal checksSum) {
        this.cardNumber = cardNumber;
        this.points = points;
        this.checksSum = checksSum;
    }
}
