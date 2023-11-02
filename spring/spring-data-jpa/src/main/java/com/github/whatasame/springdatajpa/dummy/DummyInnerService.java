package com.github.whatasame.springdatajpa.dummy;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DummyInnerService {

    @Transactional
    public void throwRuntimeException() {
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void throwRuntimeExceptionWithRequiresNew() {
        throw new RuntimeException();
    }
}
