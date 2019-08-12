package com.demo.induction.services;

import com.demo.induction.db.LogRepository;
import com.demo.induction.entity.Log;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LogServiceImp implements LogService {
    private final LogRepository repository;


    @Override
    public Optional<Log> getLogByName(String name) {
        return repository.findById(name);
    }

    @Override
    public void incrementLogHitCount(String name) {
        Optional<Log> logOptional = repository.findById(name);
        if (logOptional.isPresent()) {
            Log oldLog = logOptional.get();
            oldLog.setCount(oldLog.getCount() + 1);
            repository.save(oldLog);
        } else {
            repository.save(new Log(name, 1));
        }
        repository.findById(name).ifPresent(s -> System.out.println("HIT COUNTED: " + s));
    }

}
