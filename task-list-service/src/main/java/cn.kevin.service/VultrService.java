package cn.kevin.service;

import cn.kevin.common.enums.VultrRequestTypeEnum;
import cn.kevin.domain.dto.VultrDomain;

/**
 * vultr service
 * created by yongkang.zhang
 * added at 2017/12/7
 */
public interface VultrService {

    /**
     * 获取所有信息
     * @return vulter信息
     */
    VultrDomain get(VultrRequestTypeEnum typeEnum);
}
