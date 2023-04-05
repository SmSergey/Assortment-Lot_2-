package com.app.lot_2

import com.app.lot_2.domain.check.Check
import com.app.lot_2.domain.check.Position
import com.app.lot_2.domain.check.repository.CheckRepository
import com.app.lot_2.domain.user.User
import com.app.lot_2.domain.user.repository.UserRepository
import jakarta.annotation.PostConstruct
import jakarta.transaction.TransactionManager
import org.springframework.beans.factory.annotation.Autowire
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionTemplate

@SpringBootApplication
class Lot2Application {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CheckRepository checkRepository;

    static void main(String[] args) {
        SpringApplication.run(Lot2Application, args)
    }
}
