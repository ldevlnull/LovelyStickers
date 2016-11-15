package com.lovelystickersua.service;

import java.util.List;

import com.lovelystickersua.POJO.PurchaseOrder;

public interface PurchaseOrderService {

	void save(PurchaseOrder purchaseOrder);
	PurchaseOrder findOne(Long ID);
	List<PurchaseOrder> findAll();
	void delete(Long ID);
}
