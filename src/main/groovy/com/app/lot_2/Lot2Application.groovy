package com.app.lot_2

import com.app.lot_2.domain.check.repository.CheckRepository
import com.app.lot_2.domain.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

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
