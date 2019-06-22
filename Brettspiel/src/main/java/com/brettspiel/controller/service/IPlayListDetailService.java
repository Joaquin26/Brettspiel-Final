package com.brettspiel.controller.service;

import java.util.List;
import com.brettspiel.model.PlayListDetail;

public interface IPlayListDetailService extends ICrudService<PlayListDetail> {

	public List<PlayListDetail> findByPlayListId(int Id);
}
