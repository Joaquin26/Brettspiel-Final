package com.brettspiel.controller.service;

import java.util.List;

import com.brettspiel.model.PlayList;

public interface IPlayListService extends ICrudService<PlayList> {

	public List<PlayList> findByUserId(int id);
}
