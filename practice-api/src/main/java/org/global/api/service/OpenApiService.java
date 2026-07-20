package org.global.api.service;

import org.global.api.constant.ApiType;
import org.global.api.util.AppLog;
import org.global.api.util.HttpCommonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenApiService {

    @Value("${open-api.urls.users:https://jsonplaceholder.typicode.com/users}")
    private String usersUrl;

    @Value("${open-api.urls.posts:https://jsonplaceholder.typicode.com/posts}")
    private String postsUrl;

    /**
     * Hàm dùng chung để lấy data từ bất kỳ Open API nào dựa theo Type
     */
    public <T> T fetchData(ApiType type, Class<T> responseType) {
        String url = getUrlByType(type);
        AppLog.debug("[OpenApiService] Chuẩn bị gọi API type: {}, URL: {}", type, url);
        return HttpCommonUtil.get(url, responseType);
    }

    private String getUrlByType(ApiType type) {
        switch (type) {
            case USERS:
                return usersUrl;
            case POSTS:
                return postsUrl;
            default:
                throw new IllegalArgumentException("Chưa cấu hình URL cho API Type: " + type);
        }
    }
}
