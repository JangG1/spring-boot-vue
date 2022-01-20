package com.example.second.order.vo;

public class OrderListVO {
	private int orderidx;
	private String id;
	private int productno;
	private String selectedoption;
	private int delivery;
	private int totalprice;
	private String orderdate;
	
	
	public OrderListVO(int orderidx, String id, int productno, String selectedoption, int delivery, int totalprice,
			String orderdate) {
		super();
		this.orderidx = orderidx;
		this.id = id;
		this.productno = productno;
		this.selectedoption = selectedoption;
		this.delivery = delivery;
		this.totalprice = totalprice;
		this.orderdate = orderdate;
	}


	public int getOrderidx() {
		return orderidx;
	}


	public void setOrderidx(int orderidx) {
		this.orderidx = orderidx;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getProductno() {
		return productno;
	}


	public void setProductno(int productno) {
		this.productno = productno;
	}


	public String getSelectedoption() {
		return selectedoption;
	}


	public void setSelectedoption(String selectedoption) {
		this.selectedoption = selectedoption;
	}


	public int getDelivery() {
		return delivery;
	}


	public void setDelivery(int delivery) {
		this.delivery = delivery;
	}


	public int getTotalprice() {
		return totalprice;
	}


	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}


	public String getOrderdate() {
		return orderdate;
	}


	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	
	
	
	
	
	
	
}
