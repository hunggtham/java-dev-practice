package org.global.api.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import org.global.api.vo.res.R_ProductVo;

@Mapper
public interface ProductDao {
  String GET_ALL_PRODUCTS = "SELECT * FROM practice_product";

  @Select(GET_ALL_PRODUCTS)
  List<R_ProductVo> getAllProducts();
}
