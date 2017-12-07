package cn.kevin.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * vultr 领域
 * created by yongkang.zhang
 * added at 2017/12/7
 */
@Data
public class VultrDomain {

    private String subid;
    private BasicInfo basicInfo;
    private Bandwidth bandwidth;

    /**
     * server的基本信息
     */
    @Data
    public static class BasicInfo {

        private String os;
        private String main_ip;
        private String location;
        private String cost_per_month;
        private String current_bandwidth_gb;
        private String allowed_bandwidth_gb;
        private String power_status;
        private String server_status;
        private String label;
        private String tag;
    }

    @Data
    public static class Bandwidth {
        private List<List<String>> incoming_bytes;
        private List<List<String>> outgoing_bytes;
    }


}
