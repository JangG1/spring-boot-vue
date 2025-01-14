package com.example.second.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.second.order.vo.OrderListVO;

//매핑하기

@Mapper
public interface OrderListMapper {


	
	//모든 자료 조회
	@Select("Select * from s_order") 
	List<OrderListVO> getOrderAllList();
	
}
