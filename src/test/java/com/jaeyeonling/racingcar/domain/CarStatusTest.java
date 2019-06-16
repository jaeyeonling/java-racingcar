package com.jaeyeonling.racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class CarStatusTest {

    @DisplayName("이동 후 위치 변경에 따른 결과 확인")
    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 5, 4, 35, 41, 23 })
    void visualize(final int moveCount) {
        final Car car = new Car(() -> true);

        final StringBuilder visualBuilder = new StringBuilder(CarStatus.VISUAL_POSITION_STRING);
        for (int i = 0; i < moveCount; i++) {
            car.moveForward();

            visualBuilder.append(CarStatus.VISUAL_POSITION_STRING);

            final CarStatus status = car.getStatus();
            assertThat(status.visualize()).isEqualTo(visualBuilder.toString());
        }

        final CarStatus status = car.getStatus();
        assertThat(status.visualize()).isEqualTo(visualBuilder.toString());
    }
}
