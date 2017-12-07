package cn.kevin.common.util;

import cn.kevin.common.enums.HttpRequestMethodEnum;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * http client util
 * created by yongkang.zhang
 * added at 2017/12/7
 */
@Slf4j
public class HttpClientUtil {

    private static OkHttpClient okHttpClient;

    private synchronized static void init () {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    /**
     * params 现在不启用
     * @param url 请求url
     * @param params 参数
     * @return Response
     */
    public static Response consumeUrl(String url, Map<String, Object> params, HttpRequestMethodEnum methodEnum) {
        if (okHttpClient == null) {
            init();
        }

        Request request = new Request.Builder().url(url).method(methodEnum.getCode(), null)
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36")
                .build();
        try {
            return okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            log.error("请求{}, 参数{}, 方法{},执行发生了错误", url, params, methodEnum, e);
            throw new RuntimeException("请求错误");
        }
    }



}
