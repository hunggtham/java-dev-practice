package org.global.api.controller;

import org.global.api.annotation.ElService;
import org.global.api.service.PracticeService;
import org.global.api.util.AppLog;
import org.global.api.vo.R_PracticeVo;
import org.global.api.vo.req.Req_PostVo;
import org.global.api.vo.res.Res_PostVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/practice")
public class PracticeController {

    @Resource(name = "practiceService")
    private PracticeService practiceService;

    @GetMapping("/users")
    @ElService(key = "/api/practice/users")
    public Map<String, Object> getPracticeUsers() {
        AppLog.info("[PracticeController] Nhận request lấy danh sách users");
        Map<String, Object> response = new HashMap<>();
        try {
            // Gọi Service để xử lý lấy data
            List<R_PracticeVo> resVoList = practiceService.getPracticeUsers();
            
            response.put("status", "SUCCESS");
            response.put("data", resVoList);
        } catch (Exception e) {
            AppLog.error("[PracticeController] Có lỗi xảy ra", e);
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
        }
        return response;
    }

    @GetMapping("/posts")
    @ElService(key = "/api/practice/posts")
    public Map<String, Object> getExternalPosts(Req_PostVo reqVo) {
        AppLog.info("[PracticeController] Nhận request lấy danh sách posts từ API ngoài");
        Map<String, Object> response = new HashMap<>();
        try {
            // Gọi Service để xử lý lấy data (truyền reqVo chứa param như userId)
            List<Res_PostVo> resVoList = practiceService.getExternalPosts(reqVo);
            
            response.put("status", "SUCCESS");
            response.put("data", resVoList);
        } catch (Exception e) {
            AppLog.error("[PracticeController] Có lỗi xảy ra", e);
            response.put("status", "ERROR");
            response.put("message", e.getMessage());
        }
        return response;
    }
}
