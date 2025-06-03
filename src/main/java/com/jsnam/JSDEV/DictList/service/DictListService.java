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

    public List<DictListDto> getDictList (String dictTitle) {
        return dictListRepository.findByList(dictTitle).stream().map(DictListDto::from).toList();
    }

    public Optional<DictListDto> getDictDetail (String dictId) {
        return dictListRepository.findByDictId(dictId).map(DictListDto::from);
    }

    public Optional<DictListDto> getDictDuplicate (String dictTitle) {
        return dictListRepository.findByDictTitle(dictTitle).map(DictListDto::from);
    }

    public DictListDto insertDict (String dictTitle, String dictDescription) {
        DictList dictList = new DictList();
        dictList.setDictTitle(dictTitle);
        dictList.setDictDescription(dictDescription);
        dictList.setUserId("임시아이디");
        dictList.setUserName("임시이름");
        dictList.setDeleteYn("N");
        DictList saved = dictListRepository.save(dictList);
        return DictListDto.from(saved);
    }
}
