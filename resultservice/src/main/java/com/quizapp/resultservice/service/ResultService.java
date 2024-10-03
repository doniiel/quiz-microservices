package com.quizapp.resultservice.service;


import com.quizapp.resultservice.web.dto.ResultDTO;

import java.util.List;

public interface ResultService {

    ResultDTO getById(Long id);
    List<ResultDTO> getAll();

    List<ResultDTO> getAllByUserId(Long userId);

    ResultDTO update(Long id, ResultDTO resultDTO);
    ResultDTO create(ResultDTO resultDTO);
    String remove(Long id);
}
