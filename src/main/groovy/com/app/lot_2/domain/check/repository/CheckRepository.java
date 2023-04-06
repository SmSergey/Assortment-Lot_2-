package com.app.lot_2.domain.check.repository;

import com.app.lot_2.domain.check.Check;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckRepository extends JpaRepository<Check, Long> {

}
