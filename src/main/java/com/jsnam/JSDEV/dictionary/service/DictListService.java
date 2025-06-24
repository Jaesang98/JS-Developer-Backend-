package com.jsnam.JSDEV.dictionary.service;

import com.jsnam.JSDEV.auth.repository.MemberRepository;
import com.jsnam.JSDEV.dictionary.dto.DictListDto;
import com.jsnam.JSDEV.dictionary.entity.DictList;
import com.jsnam.JSDEV.dictionary.repository.DictListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DictListService {
    private final DictListRepository dictListRepository;
    private final MemberRepository memberRepository;

    // 리스트 조회
    public List<DictListDto> getList(String search) {
        return dictListRepository.findByList(search).stream()
                .map(DictListDto::new)
                .collect(Collectors.toList());
    }

    // 리스트 상세 조회
    public Optional<DictListDto> getDetail (String id) {
        return dictListRepository.findById(id).map(DictListDto::new);
    }

    // 리스트 상세 삭제
    public void deleteDict(String id) {
        DictList dictList = dictListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 단어장이 없습니다."));

//        dictList.setDeleteYn("Y");
//        dictListRepository.save(dictList);
    }

//
//    public Optional<DictListDto> getDictDuplicate (String dictTitle) {
//        return dictListRepository.findByDictTitleAndDeleteYn(dictTitle, "N")
//                .map(DictListDto::from);
//    }
//
//
//    public DictListDto insertDict(String dictTitle, String dictDescription) {
////        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        Optional<DictList> optionalDict = dictListRepository.findByDictTitle(dictTitle);
//        DictList dictList;
//
//        if (optionalDict.isPresent()) {
//            dictList = optionalDict.get();
//            dictList.setDeleteYn("N");
//            dictList.setDictDescription(dictDescription);
//            dictList.setUpdated(LocalDateTime.now());
//        } else {
//            dictList = new DictList();
//            dictList.setDictTitle(dictTitle);
//            dictList.setDictDescription(dictDescription);
//            dictList.setDeleteYn("N");
//            dictList.setCreated(LocalDateTime.now());
////            dictList.setMember(member);
//        }
//
//        dictListRepository.save(dictList);
//        return DictListDto.from(dictList);
//    }
//
//
//
//
//
//    public DictListDto updateDict (DictListDto request) {
//        DictList dictList = dictListRepository.findById(request.getDictId())
//                .orElseThrow(() -> new RuntimeException("존재하지 않는 ID입니다: " + request.getDictId()));
//        dictList.setUpdated(LocalDateTime.now());
//        dictList.setDictTitle(request.getDictTitle());
//        dictList.setDictDescription(request.getDictDescription());
//        DictList delete = dictListRepository.save(dictList);
//        return DictListDto.from(delete);
//    }
}
