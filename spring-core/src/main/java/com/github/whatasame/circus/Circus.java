package com.github.whatasame.circus;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Circus {

    private final Owner owner;
    private final Animal main;
    private final Animal sub;

    public Circus(
        final Owner owner,
        @Qualifier("cat") final Animal main,
        @Qualifier("dog") final Animal sub
    ) {
        this.owner = owner;
        this.main = main;
        this.sub = sub;
    }
}
