package org.global.api.service;

import org.global.api.annotation.ElDescription;
import org.global.api.annotation.ElService;
import org.global.api.constant.ApiType;
import org.global.api.dao.PracticeDao;
import org.global.api.util.AppLog;
import org.global.api.util.DataUtil;
import org.global.api.vo.ExternalUserDto;
import org.global.api.vo.R_PracticeVo;
import org.global.api.vo.req.Req_PostVo;
import org.global.api.vo.res.Res_PostVo;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@ElService("practiceService")
public class PracticeService {

    @Resource
    private PracticeDao practiceDao;

    @Resource
    private OpenApiService openApiService;

    @ElDescription(sub = "Lấy danh sách người dùng", desc = "Kết hợp dữ liệu từ DB nội bộ và API bên ngoài")
    public List<R_PracticeVo> getPracticeUsers() throws Exception {
        AppLog.debug("[PracticeService] Bắt đầu lấy danh sách user");

        // 1. Lấy dữ liệu từ Database nội bộ (giả lập DAO)
        List<Map<String, Object>> dbUsers = practiceDao.selectAllUsers();
        AppLog.debug("[PracticeService] DB users count: {}", dbUsers.size());

        // 2. Lấy dữ liệu từ External Open Source API (thông qua OpenApiService với Type = USERS)
        ExternalUserDto[] externalDataArray = openApiService.fetchData(ApiType.USERS, ExternalUserDto[].class);
        List<ExternalUserDto> externalUserList = java.util.Arrays.asList(externalDataArray);
        
        AppLog.debug("[PracticeService] External API users count: {}", externalUserList.size());

        // 3. Sử dụng DataUtil để map list data sang R_PracticeVo (từ list ngoài vào VO)
        List<R_PracticeVo> resultList = DataUtil.convertList(externalUserList, R_PracticeVo.class);

        // Map thêm data từ DB vào (ví dụ update company name hoặc override)
        // Trong thực tế, bạn sẽ có logic trộn dữ liệu 2 nguồn ở đây
        for (R_PracticeVo vo : resultList) {
            vo.setCompany("External Company"); // Giả lập dữ liệu
        }

        return resultList;
    }

    @ElDescription(sub = "Lấy danh sách posts", desc = "Lấy dữ liệu post từ API bên ngoài và map sang VO")
    public List<Res_PostVo> getExternalPosts(Req_PostVo reqVo) throws Exception {
        AppLog.debug("[PracticeService] Bắt đầu lấy danh sách posts từ API ngoài");

        // 1. Gọi dữ liệu từ External API (thông qua OpenApiService với Type = POSTS)
        // Vì API trả về JSON mảng nên chúng ta map trực tiếp vào mảng Res_PostVo
        Res_PostVo[] externalDataArray = openApiService.fetchData(ApiType.POSTS, Res_PostVo[].class);
        List<Res_PostVo> resultList = java.util.Arrays.asList(externalDataArray);
        
        // 2. Xử lý logic lọc data theo Req_PostVo nếu Client có truyền lên
        if (reqVo != null && reqVo.getUserId() != null) {
            resultList = resultList.stream()
                                   .filter(post -> reqVo.getUserId().equals(post.getUserId()))
                                   .collect(java.util.stream.Collectors.toList());
        }

        return resultList;
    }
}
