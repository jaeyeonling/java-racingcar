package com.jaeyeonling.racingcar.domain;

import com.jaeyeonling.racingcar.exception.*;
import com.jaeyeonling.racingcar.utils.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RacingGameOptionTest {

    @DisplayName("RacingGameOption 객체 생성")
    @Test
    void createOption() {
        assertThat(RacingGameOption.builder().nameOfParticipants("Test").build()).isNotNull();
    }

    @DisplayName("nameOfParticipants 없이 RacingGameOption 객체 생성 예외처리")
    @Test
    void noNameOfParticipants() {
        assertThatExceptionOfType(InvalidNameOfParticipantsException.class)
                .isThrownBy(() -> RacingGameOption.builder().build());
    }

    @DisplayName("nameOfParticipants 값에 빈 값 입력 시 예외처리")
    @Test
    void zeroNumberOfParticipants() {
        assertThatExceptionOfType(InvalidNameOfParticipantsException.class)
                .isThrownBy(() -> RacingGameOption.builder().nameOfParticipants(""));
    }

    @DisplayName("nameOfParticipants 값에 범위를 초과하는 이름 숫자 입력 시 예외처리")
    @ParameterizedTest
    @ValueSource(ints = { 456645, 745567, 3462352, 235252 })
    void overNumberOfParticipants(final int overNumber) {
        final String nameOfParticipants = generateNameOfParticipants(overNumber);

        assertThatExceptionOfType(ParticipantsLongerThanMaxException.class)
                .isThrownBy(() -> RacingGameOption.builder().nameOfParticipants(nameOfParticipants));
    }

    @DisplayName("movingCount 값에 0 입력 시 예외처리")
    @Test
    void zeroMovingCount() {
        assertThatExceptionOfType(MovingCountShorterThanMinException.class)
                .isThrownBy(() -> RacingGameOption.builder().movingCount(0));
    }

    @DisplayName("movingCount 값에 범위를 초과하는 입력 시 예외처리")
    @ParameterizedTest
    @ValueSource(ints = { 456645, 745567, 3462352, 235252 })
    void overMovingCount(final int overNumber) {
        assertThatExceptionOfType(MovingCountLongerThanMaxException.class)
                .isThrownBy(() -> RacingGameOption.builder().movingCount(overNumber));
    }

    @DisplayName("movingCount 값에 음수 값 입력 시 예외처리")
    @ParameterizedTest
    @ValueSource(ints = { -1, -23, -523, -4353, -45645 })
    void negativeMovingCount(final int negativeNumber) {
        assertThatExceptionOfType(MovingCountShorterThanMinException.class)
                .isThrownBy(() -> RacingGameOption.builder().movingCount(negativeNumber));
    }

    @DisplayName("moveStrategy 값에 null 입력 시 예외처리")
    @Test
    void nullMoveStrategy() {
        assertThatExceptionOfType(InvalidMoveStrategyException.class)
                .isThrownBy(() -> RacingGameOption.builder().moveStrategy(null));
    }

    private String generateNameOfParticipants(final int length) {
        return StringUtils.repeat("name,", length) + "last";
    }
}
