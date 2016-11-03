package com.lovelystickersua.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.lovelystickersua.entity.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long>{

}
