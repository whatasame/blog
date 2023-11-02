package com.github.whatasame.springdatajpa.student;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "students")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Convert(converter = StudentNameConverter.class)
    private StudentName name;

    @Column(name = "age")
    @Embedded
    @AttributeOverride(name = "age", column = @Column(name = "age"))
    private StudentAge age;

    @Builder
    public Student(
        @NonNull final String name,
        @NonNull final Integer age
    ) {
        this.name = new StudentName(name);
        this.age = new StudentAge(age);
    }

    public String getName() {
        return this.name.getName();
    }

    public int getAge() {
        return this.age.getAge();
    }
}
