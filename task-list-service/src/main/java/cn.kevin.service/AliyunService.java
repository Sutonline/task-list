package cn.kevin.service;

import cn.kevin.common.enums.AliyunRequestTypeEnum;
import cn.kevin.domain.dto.AliyunDomain;

/**
 * aliyun service
 * created by yongkang.zhang
 * added at 2017/12/8
 */
public interface AliyunService {

    AliyunDomain get(AliyunRequestTypeEnum typeEnum);

}
