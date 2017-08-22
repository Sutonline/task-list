package cn.kevin.util;

import cn.kevin.domain.Activity;
import cn.kevin.domain.ActivityNode;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * json工具测试类
 * Created by yongkang.zhang on 2017/8/21.
 */
@Slf4j
public class JSONTest {

    @Test
    public void toJson() throws JsonProcessingException {
        Activity activity = new Activity();
        activity.setActivityName("测试活动");
        List<ActivityNode> list = new ArrayList<>();
        IntStream.range(1, 4).forEach(i -> {
            ActivityNode activityNode = new ActivityNode();
            activityNode.setContent("测试节点".concat(i + ""));
            activityNode.setNeedDays(i);
            list.add(activityNode);
        });
        activity.setActivityNodeList(list);
        activity.setTaskCompletedPercent(50.00);
        String activityJson = new ObjectMapper().writeValueAsString(activity);
        log.info("序列化结果是: {}", activityJson);
        Activity activity1 = JSON.parseObject(activityJson, Activity.class);
        log.info("反序列化结果是: {}", activity1);
    }

    @Test
    public void testNumberFormat() {
        Double d = 0d;
        DecimalFormat format = new DecimalFormat("##0.00'%'");
        log.info("格式化的结果是: {}", format.format(d));
    }
}
