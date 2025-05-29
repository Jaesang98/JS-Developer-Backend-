package com.jsnam.JSDEV.DictList.service;

import com.jsnam.JSDEV.DictList.dto.DictListDto;
import com.jsnam.JSDEV.DictList.entity.DictList;
import com.jsnam.JSDEV.DictList.repository.DictListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DictListService {
    private final DictListRepository dictListRepository;

    public List<DictListDto> getDictList () {
        return dictListRepository.findByDeleteYn("N").stream().map(DictListDto::from).toList();
    }

    public Optional<DictListDto> getDictDetail (String dictId) {
        return dictListRepository.findByDictIdAndDeleteYn(dictId, "N").map(DictListDto::from);
    }
}
