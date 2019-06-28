package com.brettspiel.controller.service;

import com.brettspiel.model.Copy;

import java.util.List;

public interface ICopyService extends ICrudService<Copy> {
    List<Copy> selectLimitAvailableByBoardGameId(Integer id, Integer limit);
}
