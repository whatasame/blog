package com.github.whatasame.springdatajpa.dummy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DummyService {

    private final DummyInnerService dummyInnerService;

    @Transactional
    public void doesNotCatchRuntimeException() {
        dummyInnerService.throwRuntimeException();
    }

    @Transactional
    public void catchRuntimeException() {
        try {
            dummyInnerService.throwRuntimeException();
        } catch (Exception e) {
            /* do nothing */
        }
    }

    @Transactional
    public void catchRuntimeExceptionWithPropagationRequiresNew() {
        try {
            dummyInnerService.throwRuntimeExceptionWithRequiresNew();
        } catch (Exception e) {
            /* do nothing */
        }
    }
}
