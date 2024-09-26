package com.quizapp.resultservice.service.impl;

import com.quizapp.resultservice.model.entity.Result;
import com.quizapp.resultservice.model.exception.ResultNotFoundException;
import com.quizapp.resultservice.repository.ResultRepository;
import com.quizapp.resultservice.service.ResultService;
import com.quizapp.resultservice.web.dto.ResultDTO;
import com.quizapp.resultservice.web.dto.ResultMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final ResultMapper resultMapper;

    @Override
    public ResultDTO getById(Long id) {
        Result result = resultRepository.findById(id).orElseThrow(
                () -> new ResultNotFoundException("Result not found")
        );
        return resultMapper.mapToDto(result);
    }

    @Override
    public List<ResultDTO> getAll() {
        List<Result> results = resultRepository.findAll();
        return results.stream().
                map(resultMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResultDTO update(ResultDTO resultDTO) {
        Result result = resultMapper.mapToEntity(resultDTO);
        resultRepository.save(result);
        return resultMapper.mapToDto(result);
    }

    @Override
    public ResultDTO create(ResultDTO resultDTO) {
        if (resultDTO == null) {
            throw new IllegalArgumentException("ResultDTO must not be null");
        }
        Result result = resultMapper.mapToEntity(resultDTO);
        resultRepository.save(result);
        return resultMapper.mapToDto(result);
    }

    @Override
    public String remove(Long id) {
        Result result = resultRepository.findById(id).orElseThrow(
                () -> new ResultNotFoundException("Result not found")
        );
        resultRepository.delete(result);
        return "Result with id: " + id + " deleted";
    }
}
