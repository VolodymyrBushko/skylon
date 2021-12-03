package org.vbushko.skylon.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import static org.apache.commons.lang3.time.DateUtils.addSeconds;

@Component
public class TimeUtil {

    public LocalDateTime getFuture(int seconds) {
        Date now = Calendar.getInstance().getTime();
        Date expiration = addSeconds(now, seconds);
        return LocalDateTime.ofInstant(expiration.toInstant(), ZoneId.systemDefault());
    }
}
