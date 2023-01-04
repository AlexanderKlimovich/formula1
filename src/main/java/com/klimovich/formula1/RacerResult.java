package com.klimovich.formula1;

import java.time.Duration;
import java.util.Objects;

public class RacerResult {
    private String name;
    private String team;
    private Duration lapTime;

    public RacerResult(String name, String team, Duration lapTime) {
        this.name = name;
        this.team = team;
        this.lapTime = lapTime;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public Duration getLapTime() {
        return lapTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lapTime, name, team);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RacerResult other = (RacerResult) obj;
        return Objects.equals(lapTime, other.lapTime) && Objects.equals(name, other.name) && Objects.equals(team,
                other.team);
    }
}
