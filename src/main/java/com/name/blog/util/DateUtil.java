package com.name.blog.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtil {
    public Date createUTCDate() {
        Instant nowInstant = Instant.now().truncatedTo(ChronoUnit.SECONDS);

        return Date.from(nowInstant);
    }

    public Date createUTCDatePlus(Long addedAmount, ChronoUnit unit) {
        Instant nowInstant = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        Instant plusInstant = nowInstant.plus(addedAmount, unit);

        return Date.from(plusInstant);
    }

    public Long convertToEpochSecond(Date date) {
        return date.toInstant().getEpochSecond();
    }

    public Date convertToUTCDate(Long epochSecond) {
        return Date.from(Instant.ofEpochSecond(epochSecond));
    }

    public boolean isValid(Long epochSecond) {
        Date nowDate = createUTCDate();
        Long nowAt = convertToEpochSecond(nowDate);

        return nowAt > epochSecond;
    }
}
