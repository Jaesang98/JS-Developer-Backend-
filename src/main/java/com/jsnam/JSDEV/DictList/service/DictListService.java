package com.jsnam.JSDEV.DictList.service;

import com.jsnam.JSDEV.DictList.dto.DictListDto;
import com.jsnam.JSDEV.DictList.entity.DictList;
import com.jsnam.JSDEV.DictList.repository.DictListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        return dictListRepository.findByDictTitleAndDeleteYn(dictTitle, "N")
                .map(DictListDto::from);
    }


    public DictListDto insertDict(String dictTitle, String dictDescription) {
        Optional<DictList> optionalDict = dictListRepository.findByDictTitle(dictTitle);
        DictList dictList;

        if (optionalDict.isPresent()) {
            // 이미 존재하는 경우: 업데이트
            dictList = optionalDict.get();
            dictList.setDeleteYn("N");
            dictList.setDictDescription(dictDescription);
            dictList.setUpdated(LocalDateTime.now());
        } else {
            // 없는 경우: 새로 생성
            dictList = new DictList();
            dictList.setDictTitle(dictTitle);
            dictList.setDictDescription(dictDescription);
            dictList.setUserId("임시아이디");
            dictList.setUserName("임시이름");
            dictList.setDeleteYn("N");
            dictList.setCreated(LocalDateTime.now());
            dictList.setUpdated(LocalDateTime.now());
        }

        DictList saved = dictListRepository.save(dictList);
        return DictListDto.from(saved);
    }


    public DictListDto deleteDict (String dictId) {
        DictList dictList = dictListRepository.findById(dictId)
                .orElseThrow(() -> new RuntimeException("해당 ID의 단어가 존재하지 않습니다."));
        dictList.setDeleteYn("Y");
        DictList delete = dictListRepository.save(dictList);
        return DictListDto.from(delete);
    }

    public DictListDto updateDict (DictListDto request) {
        DictList dictList = dictListRepository.findById(request.getDictId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 ID입니다: " + request.getDictId()));
        dictList.setUpdated(LocalDateTime.now());
        dictList.setDictTitle(request.getDictTitle());
        dictList.setDictDescription(request.getDictDescription());
        DictList delete = dictListRepository.save(dictList);
        return DictListDto.from(delete);
    }
}
