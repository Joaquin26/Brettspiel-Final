package com.brettspiel.controller.service;

import com.brettspiel.model.Batch;
import com.brettspiel.model.Promotion;

import java.util.Optional;

public interface IBatchService  extends ICrudService<Batch> {
    Optional<Batch> selectBySnackId(Integer id);
}
