package org.global.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.global.api.service.ProductService;
import org.global.api.util.AppLog;
import org.global.api.vo.res.R_ProductVo;

@RestController
@RequestMapping("/api/product")
public class ProductController {
  
    @Resource(name = "productService")
    private ProductService productService;

    @GetMapping("/getAll")
     public Map<String, Object> getAllProducts() {
      AppLog.info("[PracticeController] Nhận request lấy danh sách product");
        Map<String, Object> response = new HashMap<>();
        try {
            // Gọi Service để xử lý lấy data
            List<R_ProductVo>resVoList = productService.getAllProducts();
            
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
