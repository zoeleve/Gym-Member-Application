package com.example.workout;

import org.junit.Assert;
import org.junit.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class MembershipTest {

    @Test
    public void calcTestSmallNumber() {
        Clock clockForTest = Clock.fixed(LocalDateTime.of(2017, 2, 15, 12, 00)
                .toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));

        Membership membership = new Membership(5, clockForTest);  // or Clock.systemUTC() for real time
        membership.calcMembershipDuration();

        Assert.assertEquals(membership.getEndYear(), 2017);
        Assert.assertEquals(membership.getEndMonth(), 7);
    }

    @Test
    public void calcTestBigNumber() {
        Clock clockForTest = Clock.fixed(LocalDateTime.of(2017, 2, 15, 12, 00)
                .toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));

        Membership membership = new Membership(23, clockForTest);  // or Clock.systemUTC() for real time
        membership.calcMembershipDuration();

        Assert.assertEquals(membership.getEndYear(), 2019);
        Assert.assertEquals(membership.getEndMonth(), 1);
    }

    @Test
    public void checkStatusMembershipIsOverTest() {
        Clock clockOnMembershipDate = Clock.fixed(LocalDateTime.of(2017, 2, 15, 12, 00)
                .toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));

        Membership membership = new Membership(2, clockOnMembershipDate);  // or Clock.systemUTC() for real time
        membership.calcMembershipDuration();

        Duration duration = Duration.ofDays(90);
        Clock clockOnADayInsideMembership = Clock.offset(clockOnMembershipDate, duration);

        membership.setClock(clockOnADayInsideMembership);
        membership.checkStatus();
        Assert.assertEquals(membership.getStatus(), false);
    }

    @Test
    public void checkStatusMembershipIsActiveTest() {
        Clock clockOnMembershipDate = Clock.fixed(LocalDateTime.of(2017, 2, 15, 12, 00)
                .toInstant(ZoneOffset.UTC), ZoneId.of("UTC"));

        Membership membership = new Membership(5, clockOnMembershipDate);  // or Clock.systemUTC() for real time
        membership.calcMembershipDuration();

        Duration duration = Duration.ofDays(30);
        Clock clockOnADayInsideMembership = Clock.offset(clockOnMembershipDate, duration);

        membership.setClock(clockOnADayInsideMembership);
        membership.checkStatus();
        Assert.assertEquals(membership.getStatus(), true);
    }

}