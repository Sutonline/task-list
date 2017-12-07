package cn.kevin.service.impl;

import cn.kevin.common.enums.HttpRequestMethodEnum;
import cn.kevin.common.enums.VultrRequestTypeEnum;
import cn.kevin.common.util.HttpClientUtil;
import cn.kevin.domain.dto.VultrDomain;
import cn.kevin.service.VultrService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * vulterService
 * created by yongkang.zhang
 * added at 2017/12/7
 */
@Service
@Slf4j
public class VultrServiceImpl implements VultrService {


    @Value(value = "${vultr.api}")
    private String apiKey;
    @Value(value = "${vultr.baseUrl}")
    private String baseUrl;
    @Value(value = "${vultr.urls.server-list}")
    private String basicInfoUrl;
    @Value(value = "${vultr.urls.server-bandwidht}")
    private String bandwidthUrl;
    @Value(value = "${vultr.subid}")
    private String subid;

    @Override
    public VultrDomain get(VultrRequestTypeEnum typeEnum) {
        Response basicResponse = null;
        Response bandResponse = null;
        switch (typeEnum) {
            case BASIC_INFO:
                basicResponse = HttpClientUtil.consumeUrl(baseUrl.concat(basicInfoUrl).concat("?api_key=").concat(apiKey), null, HttpRequestMethodEnum.GET);
                break;
            case BAND_WIDTH:
                bandResponse = HttpClientUtil.consumeUrl(baseUrl.concat(bandwidthUrl).concat("?api_key=").concat(apiKey).concat("&SUBID=").concat(subid), null, HttpRequestMethodEnum.GET);
                break;
            case ALL:
                basicResponse = HttpClientUtil.consumeUrl(baseUrl.concat(basicInfoUrl).concat("?api_key=").concat(apiKey), null, HttpRequestMethodEnum.GET);
                bandResponse = HttpClientUtil.consumeUrl(baseUrl.concat(bandwidthUrl).concat("?api_key=").concat(apiKey).concat("&SUBID=").concat(subid), null, HttpRequestMethodEnum.GET);
            default:
                break;
        }

        VultrDomain vultrDomain = new VultrDomain();

        if (basicResponse != null) {
            // 解析basicInfo
            vultrDomain.setBasicInfo(parseBasic(basicResponse));
        }

        if (bandResponse != null) {
            // 解析bandwidth
            vultrDomain.setBandwidth(parseBandWidth(bandResponse));
        }

        return vultrDomain;
    }

    @SuppressWarnings("unchecked")
    private VultrDomain.BasicInfo parseBasic(Response response) {
        log.info("返回的response是: {}", response.body());
        String json = parseString(response);
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }

        Map<String, JSONObject> map = (Map<String, JSONObject>) JSON.parse(json);

        return JSON.parseObject(map.get(subid).toString(), VultrDomain.BasicInfo.class);
    }

    private VultrDomain.Bandwidth parseBandWidth(Response response) {
        log.info("返回的response是: {}", response);
        String json = parseString(response);
        if (Strings.isNullOrEmpty(json)) {
            return null;
        }

        return JSON.parseObject(json, VultrDomain.Bandwidth.class);
    }

    private String parseString(Response response) {
        try {
            if (response == null || response.body() == null) {
                return "";
            }
            // 判断response的是否返回成功
            if (!response.isSuccessful()) {
                return "";
            }

            return response.body().string();
        } catch (IOException e) {
            log.error("读取response: {}出错", response, e);
            throw new RuntimeException("读取错误");
        }
    }


}
