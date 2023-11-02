package com.github.whatasame.kotlin

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class EvaluationTest : BehaviorSpec({
    given("1부터 10까지 정수 리스트가 주어진다.") {
        val nums = (1..10).toList()

        `when`("Collection을 이용하여 짝수 3개를 필터링하면") {
            val result = nums
                .filter { println("filter target = $it"); it % 2 == 0 }
                .onEach { println("filtered = $it") }
                .take(3)

            then("Eager evaluation으로 처리한다.") {
                result shouldBe listOf(2, 4, 6)
            }
        }

        `when`("Sequence를 이용하여 짝수 3개를 필터링하면") {
            val result = nums.asSequence()
                .filter { println("filter target = $it"); it % 2 == 0 }
                .onEach { println("filtered = $it") }
                .take(3)
                .toList()

            then("Lazy evaluation으로 처리한다.") {
                result shouldBe listOf(2, 4, 6)
            }
        }
    }
})
