package com.endava.springrestapi.data.entitie;

public enum ReservationPeriod {
    ONE_WEEK(1),
    TWO_WEEKS(2),
    THREE_WEEKS(3),
    ONE_MONTH(4);
   public final long offsetInWeeks;

    private ReservationPeriod(long offsetInWeek) {
        this.offsetInWeeks = offsetInWeek;
    }
}
