package com.app.lot_2.domain.check.repository;


import com.app.lot_2.domain.check.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PositionRepository extends JpaRepository<Position, UUID> {
}
