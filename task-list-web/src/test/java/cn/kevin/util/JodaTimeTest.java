package cn.kevin.util;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDate;
import org.junit.Test;

import java.util.Date;

/**
 * joda time test
 * Created by yongkang.zhang on 2017/8/18.
 */
@Slf4j
public class JodaTimeTest {

    @Test
    public void test() {
        LocalDate localDate = new LocalDate(new Date());
        log.info("时间结果是: {}", localDate.plusDays(3));
    }
}
