package cn.kevin.domain.dto;

import com.aliyuncs.ecs.model.v20140526.DescribeInstanceAttributeResponse;
import com.aliyuncs.ecs.model.v20140526.DescribeInstanceMonitorDataResponse;
import lombok.Data;

import java.util.List;

/**
 * 阿里云domain
 * created by yongkang.zhang
 * added at 2017/12/8
 */
@Data
public class AliyunDomain {

    private String instanceId;

    // 实例信息
    private DescribeInstanceAttributeResponse instanceInfo;

    // 监控信息
    private List<DescribeInstanceMonitorDataResponse.InstanceMonitorData> monitorData;


}
