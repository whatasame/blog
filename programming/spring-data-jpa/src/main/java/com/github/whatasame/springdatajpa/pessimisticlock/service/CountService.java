package com.github.whatasame.springdatajpa.pessimisticlock.service;

import com.github.whatasame.springdatajpa.pessimisticlock.repository.CountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CountService {

    private static final String NOT_FOUND_COUNT = "Count가 존재하지 않습니다.";
    private final CountRepository countRepository;

    @Transactional
    public void increaseCountOne(final Long countId) {
        countRepository.findById(countId)
            .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_COUNT))
            .increaseCountOne();
    }

    @Transactional
    public void increaseCountOneWithPessimisticRead(final Long countId) {
        countRepository.findByIdWithPessimisticRead(countId)
            .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_COUNT))
            .increaseCountOne();
    }

    @Transactional
    public void increaseCountOneWithPessimisticWrite(final Long countId) {
        countRepository.findByIdWithPessimisticWrite(countId)
            .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_COUNT))
            .increaseCountOne();
    }
}
