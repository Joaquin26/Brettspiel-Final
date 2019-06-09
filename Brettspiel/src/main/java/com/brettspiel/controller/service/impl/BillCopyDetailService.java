package com.brettspiel.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brettspiel.controller.service.IBillCopyDetailService;
import com.brettspiel.model.BillCopyDetail;
import com.brettspiel.model.repository.IBillCopyDetailRepository;
@Service
public class BillCopyDetailService implements IBillCopyDetailService {

	@Autowired
	IBillCopyDetailRepository billCopyDetailRepository;
	
	@Override
	public BillCopyDetail insert(BillCopyDetail t) {
		// TODO Auto-generated method stub
		return billCopyDetailRepository.save(t);
	}

	@Override
	public BillCopyDetail update(BillCopyDetail t) {
		// TODO Auto-generated method stub
		return billCopyDetailRepository.save(t);
	}

	@Override
	public void Delete(int id) {
		billCopyDetailRepository.deleteById(id);
		
	}

	@Override
	public Optional<BillCopyDetail> findById(int id) {
		// TODO Auto-generated method stub
		return billCopyDetailRepository.findById(id);
	}

	@Override
	public List<BillCopyDetail> findAll() {
		return billCopyDetailRepository.findAll();
	}
	
}
