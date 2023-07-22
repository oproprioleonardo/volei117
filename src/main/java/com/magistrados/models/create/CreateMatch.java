package com.magistrados.models.create;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public record CreateMatch(
        String idTimeA,
        String idTimeB,
        String data,
        String horario,
        String local
) {

    public Long getIdTimeA() {
        return Long.valueOf(this.idTimeA);
    }

    public Long getIdTimeB() {
        return Long.valueOf(this.idTimeB);
    }

    public LocalDate getData() {
        return LocalDate.parse(this.data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public LocalTime getHorario() {
        return LocalTime.parse(this.horario, DateTimeFormatter.ofPattern("HH:mm"));
    }

}
