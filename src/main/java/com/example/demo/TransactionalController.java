package com.example.demo;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class TransactionalController {
    private final SomeEntityRepository someEntityRepository;

    public TransactionalController(SomeEntityRepository someEntityRepository) {
        this.someEntityRepository = someEntityRepository;
    }

    @GetMapping("/")
    @Transactional(rollbackFor=Exception.class)
    public String index(Model model) throws Exception {
        for (int x = 0; x < 10; x++) {
            SomeEntity someEntity = new SomeEntity();
            someEntityRepository.save(someEntity);
            if (ThreadLocalRandom.current().nextInt(20) == 0)
                throw new Exception("No luck");
        }        

        model.addAttribute("items", someEntityRepository.findAll());

        return "index";
    }
}
