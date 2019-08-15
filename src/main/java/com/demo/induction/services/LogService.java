package com.demo.induction.services;

import com.demo.induction.entity.Log;

import java.util.Optional;

public interface LogService {
    void incrementLogHitCount(String name);

    Optional<Log> getLogByName(String name);
}
