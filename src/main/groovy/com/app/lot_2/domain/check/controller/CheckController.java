package com.app.lot_2.domain.check.controller;

import com.app.lot_2.domain.check.Check;
import com.app.lot_2.domain.check.Position;
import com.app.lot_2.domain.check.controller.dto.CheckAddedDto;
import com.app.lot_2.domain.check.controller.dto.CreateCheckDto;
import com.app.lot_2.domain.check.controller.dto.GetPointsResponse;
import com.app.lot_2.domain.check.repository.CheckRepository;
import com.app.lot_2.domain.check.repository.PositionRepository;
import com.app.lot_2.domain.user.User;
import com.app.lot_2.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class CheckController {
    private final UserRepository userRepository;
    private final CheckRepository checkRepository;
    private final PositionRepository positionRepository;

    private final BigDecimal BIG_DECIMAL_50_000 = BigDecimal.valueOf(50000);
    private final BigDecimal BIG_DECIMAL_100_000 = BigDecimal.valueOf(50000);
    private final BigDecimal BIG_DECIMAL_50 = BigDecimal.valueOf(50);
    private final BigDecimal BIG_DECIMAL_40 = BigDecimal.valueOf(40);
    private final BigDecimal BIG_DECIMAL_30 = BigDecimal.valueOf(30);

    @GetMapping("/")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/check")
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public CheckAddedDto addNewRandom(@RequestBody @Valid CreateCheckDto addCheckDto) {

        User user;
        try {
            user = userRepository.findLockedByCardNumber(addCheckDto.getCardNumber())
                    .orElseGet(() -> userRepository.saveAndFlush(new User(addCheckDto.getCardNumber(), BigDecimal.ZERO, BigDecimal.ZERO)));

        } catch (Exception ignored) {
            //might occur when 2 thread tries to create user with the same cardNumber
            user = userRepository.findLockedByCardNumber(addCheckDto.getCardNumber())
                    .orElseThrow(() -> new EntityNotFoundException("user wasn't found"));
        }

        Check check = new Check(user, addCheckDto.getSum(), user.getCardNumber());

        List<Position> checkPositions = addCheckDto.getPositionList()
                .stream()
                .map(positionItemDto -> new Position(positionItemDto.getSum(), check))
                .toList();

        check.setPositions(checkPositions);
        checkRepository.save(check);

        BigDecimal newSum = user.getChecksSum().add(addCheckDto.getSum());

        user.setChecksSum(newSum);
        BigDecimal newPoints;

        if (newSum.compareTo(BIG_DECIMAL_50_000) <= 0) {
            newPoints = check.getSum().divide(BIG_DECIMAL_50, MathContext.DECIMAL32);

        } else if (newSum.compareTo(BIG_DECIMAL_50_000) >= 0 && newSum.compareTo(BIG_DECIMAL_100_000) <= 0) {
            newPoints = check.getSum().divide(BIG_DECIMAL_40, MathContext.DECIMAL32);

        } else {
            newPoints = check.getSum().divide(BIG_DECIMAL_30, MathContext.DECIMAL32);
        }

        user.setPoints(user.getPoints().add(newPoints));
        userRepository.save(user);
        
        return new CheckAddedDto(check.getId());
    }

    @GetMapping("/points")
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public GetPointsResponse getUserPoints(@RequestParam("cardNumber") String curdNumber) {
        User user;
        try {
            user = userRepository.findByCardNumber(curdNumber)
                    .orElseGet(() -> userRepository.saveAndFlush(new User(curdNumber, BigDecimal.ZERO, BigDecimal.ZERO)));
        } catch (Exception ex) {
            user = userRepository.findByCardNumber(curdNumber)
                    .orElseThrow(() -> new EntityNotFoundException("card wasn't found"));
        }

        return new GetPointsResponse(user.getPoints(), user.getUpdatedAt());
    }
}
