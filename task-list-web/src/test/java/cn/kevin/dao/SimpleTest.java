package cn.kevin.dao;

import org.junit.Test;

/**
 * Created by yongkang.zhang on 2017/5/24.
 */
public class SimpleTest {

    @Test
    public void test() {
        final String CHARGE_SERVICE_APPROVAL_LOG = "%s审核%s, serviceId是:%s, 类型是%s";
        String replaceStr = String.format(CHARGE_SERVICE_APPROVAL_LOG, "我", "通过", "5", "3");
        System.out.println(replaceStr);
        System.out.println(CHARGE_SERVICE_APPROVAL_LOG);
    }

}
