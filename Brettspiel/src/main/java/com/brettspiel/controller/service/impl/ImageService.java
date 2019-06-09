package com.brettspiel.controller.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brettspiel.controller.service.IImageService;
import com.brettspiel.model.Image;
import com.brettspiel.model.repository.IImageRepository;

@Service
public class ImageService implements IImageService {

	@Autowired
	private IImageRepository imageRepository;
	
	@Override
	public Image insert(Image t) {
		return imageRepository.save(t);
			
	}

	@Override
	public Image update(Image t) {
		return imageRepository.save(t);
	}

	@Override
	public void Delete(int id) {
		imageRepository.deleteById(id);
	}

	@Override
	public Optional<Image> findById(int id) {
		return imageRepository.findById(id);
	}

	@Override
	public List<Image> findAll() {
		return imageRepository.findAll();
	}

}
