package cn.kevin.service;

import cn.kevin.common.enums.VultrRequestTypeEnum;
import cn.kevin.domain.dto.VultrDomain;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * created by yongkang.zhang
 * added at 2017/12/7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-config.xml", "classpath:spring-config-test.xml"})
@Slf4j
public class VultrServiceTest {

    @Autowired
    private VultrService vultrService;

    @Test
    public void get() {
        VultrDomain vultrDomain = vultrService.get(VultrRequestTypeEnum.ALL);
        beautifullyLog("查询到的信息是: {}", vultrDomain);
    }

    private void beautifullyLog(String msg, Object... params) {
        log.info("===============================================================");
        log.info(msg, params);
        log.info("===============================================================");
    }
}
