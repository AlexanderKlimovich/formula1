package com.klimovich.formula1;

import java.time.LocalDateTime;
import java.util.Objects;

public class TimeInfo {
    private final String abbreviation;
    private final LocalDateTime time;

    public TimeInfo(String abbreviation, LocalDateTime time) {
        this.abbreviation = abbreviation;
        this.time = time;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public int hashCode() {
        return Objects.hash(abbreviation, time);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TimeInfo other = (TimeInfo) obj;
        return Objects.equals(abbreviation, other.abbreviation) && Objects.equals(time, other.time);
    }
}
