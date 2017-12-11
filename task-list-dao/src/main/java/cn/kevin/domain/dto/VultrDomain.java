package cn.kevin.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Bandwidth bandWidth;
    private BandWidthVo bandwidthVo;

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

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BandWidthItem {
        private String date;
        private String bytes;
    }

    @Data
    public static class BandWidthVo {
        private List<BandWidthItem> incomingBytes;
        private List<BandWidthItem> outgoingBytes;
    }


}
