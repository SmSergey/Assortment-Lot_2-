package com.app.lot_2.domain.user.repository;

import com.app.lot_2.domain.user.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCardNumber(String cardNumber);

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    Optional<User> findLockedByCardNumber(String cardNumber);


}
