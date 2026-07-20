package org.global.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.global.api.annotation.ElDescription;
import org.global.api.dao.ProductDao;
import org.global.api.vo.res.R_ProductVo;

@Service
@ElDescription
public class ProductService {

  @Autowired
  private ProductDao productDao;

  public List<R_ProductVo> getAllProducts() {
    return productDao.getAllProducts();
  }
}
