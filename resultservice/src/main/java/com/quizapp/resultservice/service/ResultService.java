package com.quizapp.resultservice.service;


import com.quizapp.resultservice.web.dto.ResultDTO;

import java.util.List;

public interface ResultService {

    ResultDTO getById(Long id);
    List<ResultDTO> getAll();
    ResultDTO update(ResultDTO resultDTO);
    ResultDTO create(ResultDTO resultDTO);
    String remove(Long id);
}
