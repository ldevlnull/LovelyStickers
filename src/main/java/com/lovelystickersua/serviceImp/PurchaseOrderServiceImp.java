package com.lovelystickersua.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovelystickersua.entity.PurchaseOrder;
import com.lovelystickersua.repository.PurchaseOrderRepository;
import com.lovelystickersua.service.PurchaseOrderService;
@Service
public class PurchaseOrderServiceImp implements PurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository pService;
	
	@Override
	public void save(PurchaseOrder purchaseOrder) {
		pService.save(purchaseOrder);
	}

	@Override
	public PurchaseOrder findOne(Long ID) {
		return pService.findOne(ID);
	}

	@Override
	public List<PurchaseOrder> findAll() {
		return pService.findAll();
	}

	@Override
	public void delete(Long ID) {
		pService.delete(ID);
	}

}
