package com.quizapp.resultservice.service.impl;

import com.quizapp.resultservice.model.entity.Result;
import com.quizapp.resultservice.model.exception.ResultNotFoundException;
import com.quizapp.resultservice.repository.ResultRepository;
import com.quizapp.resultservice.service.ResultService;
import com.quizapp.resultservice.web.dto.ResultDTO;
import com.quizapp.resultservice.web.mapper.ResultMapper;
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
    public List<ResultDTO> getAllByUserId(Long userId) {
        List<Result> results = resultRepository.findByUserId(userId);
        return results.stream().
                map(resultMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResultDTO update(Long id, ResultDTO resultDTO) {
        validate(resultDTO);
        Result exist = resultRepository.findById(id).orElseThrow(
                () -> new ResultNotFoundException("Result not found for id: " + id)
        );
        exist.setUserId(resultDTO.getUserId());
        exist.setQuizId(resultDTO.getQuizId());
        exist.setScore(resultDTO.getScore());

        Result dbResult = resultRepository.save(exist);
        return resultMapper.mapToDto(dbResult);
    }

    @Override
    public ResultDTO create(ResultDTO resultDTO) {
        validate(resultDTO);

        Result result = resultMapper.mapToEntity(resultDTO);
        Result dbResult = resultRepository.save(result);
        return resultMapper.mapToDto(dbResult);
    }

    @Override
    public String remove(Long id) {
        Result result = resultRepository.findById(id).orElseThrow(
                () -> new ResultNotFoundException("Result not found")
        );
        resultRepository.delete(result);
        return "Result with id: " + id + " deleted";
    }

    private void validate(ResultDTO resultDTO) {
        if (resultDTO.getId() == null) {
            throw new IllegalArgumentException("ResultDTO must not be null");
        }
        if (resultDTO.getUserId() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        if (resultDTO.getQuizId() == null) {
            throw new IllegalArgumentException("Quiz ID must not be null");
        }
        if (resultDTO.getScore() == null || resultDTO.getScore() < 0) {
            throw new IllegalArgumentException("Score must be a non-negative integer");
        }
    }
}
