package com.brettspiel.controller.service.impl;

import java.util.List;
import java.util.Optional;

import com.brettspiel.controller.service.IBillCopyDetailService;
import com.brettspiel.model.BillCopyDetail;
import com.brettspiel.model.BillDetail;
import com.brettspiel.model.repository.IBillCopyDetailRepository;
import com.brettspiel.model.repository.IBillDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brettspiel.controller.service.IBillService;
import com.brettspiel.model.Bill;
import com.brettspiel.model.repository.IBillRepository;
@Service
public class BillService implements IBillService {

	@Autowired
	private IBillRepository billRepository;
	@Autowired
	private IBillDetailRepository billDetailRepository;
	@Autowired
	private IBillCopyDetailRepository billCopyDetailRepository;
	
	@Override
	public Bill insert(Bill t) {
		Bill newBill = billRepository.save(t);
		for (BillDetail billDetail : t.getBillDetails()) {
			billDetail.setBill(newBill);
			billDetailRepository.save(billDetail);
		}
		for (BillCopyDetail billCopyDetail : t.getBillCopyDetails()) {
			billCopyDetail.setBill(newBill);
			billCopyDetailRepository.save(billCopyDetail);
		}
		return newBill;
	}

	@Override
	public Bill update(Bill t) {
		// TODO Auto-generated method stub
		return billRepository.save(t);
	}

	@Override
	public void Delete(int id) {
		billRepository.deleteById(id);
		
	}

	@Override
	public Optional<Bill> findById(int id) {
		// TODO Auto-generated method stub
		return billRepository.findById(id);
	}

	@Override
	public List<Bill> findAll() {
		return billRepository.findAll();
	}
	
}
