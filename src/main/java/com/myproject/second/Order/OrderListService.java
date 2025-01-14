package com.example.second.order.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.second.order.mapper.OrderListMapper;
import com.example.second.order.vo.OrderListVO;

@Service
public class OrderListService {
	private OrderListMapper orderListMapper;

	public OrderListService(OrderListMapper orderListMapper) {
		this.orderListMapper = orderListMapper;
	}
	
	public List<OrderListVO> getOrderAllList(){
		return orderListMapper.getOrderAllList();
	}
}

