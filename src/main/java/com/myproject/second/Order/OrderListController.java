package com.example.second.order.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.second.order.mapper.OrderListMapper;
import com.example.second.order.vo.OrderListVO;


@RestController
@RequestMapping(value="/api/*")
public class OrderListController {
	private OrderListMapper mapper;
	
	public OrderListController( OrderListMapper mapper) {// mapper와 controller 연동
		this.mapper = mapper; 
	}
	

	private Map<String, OrderListVO> orderListMap;
	

	//모든 자료 조회하기 
	@RequestMapping("/order/all")
	public List<OrderListVO> getOrderAllList() {
		//List<OrderListVO> list = mapper.getOrderAllList();
		return mapper.getOrderAllList();
	}
	

}


