package com.github.whatasame.java.stream.vo;

public record Student(
        String name,
        double korean,
        double english,
        double math
) {

    public double maxGrade() {
        return Math.max(korean, Math.max(english, math));
    }
}
