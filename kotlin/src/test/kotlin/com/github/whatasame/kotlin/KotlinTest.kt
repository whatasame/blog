package com.github.whatasame.kotlin

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class KotlinTest : BehaviorSpec({
    given("객체가 주어질 때") {
        val person = Person("홍길동", 20)

        /* https://kotlinlang.org/docs/destructuring-declarations.html */
        `when`("Destructuring를 사용하면") {
            val (name, age) = person

            then("객체의 속성을 분해할 수 있다.") {
                name shouldBe "홍길동"
                age shouldBe 20
            }
        }
    }
})
