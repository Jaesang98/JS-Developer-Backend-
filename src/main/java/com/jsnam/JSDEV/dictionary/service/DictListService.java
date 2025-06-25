package com.jsnam.JSDEV.dictionary.service;

import com.jsnam.JSDEV.auth.entity.Member;
import com.jsnam.JSDEV.auth.repository.MemberRepository;
import com.jsnam.JSDEV.dictionary.dto.DictListDto;
import com.jsnam.JSDEV.dictionary.entity.DictList;
import com.jsnam.JSDEV.dictionary.repository.DictListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

        dictList.setDeleteYn("Y");
        dictListRepository.save(dictList);
    }
    
    // DB에 타이틀 있는지 확인
    public Boolean isTitleExist(String title) {
        Optional<DictList> dictList = dictListRepository.findByTitle(title);

        if(dictList.isPresent()) {
            return true;
        }
        else {
            return false;
        }
    }


    // 저장, 수정
    public void insertDict(DictList request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) authentication.getPrincipal();
        Optional<DictList> dictList_exit = dictListRepository.findByTitle(request.getDictTitle());

        if (dictList_exit.isPresent()) {
            System.out.println("여기까지 오나");
            DictList dictList = dictList_exit.get();
            dictList.setDeleteYn("N");
            dictList.setDictDescription(request.getDictDescription());
            dictList.setUpdated(LocalDateTime.now());
            dictListRepository.save(dictList);
        } else {
            System.out.println("여기까지 오나2");
            DictList dictList = new DictList();
            dictList.setDictTitle(request.getDictTitle());
            dictList.setDictDescription(request.getDictDescription());
            dictList.setDeleteYn("N");
            dictList.setCreated(LocalDateTime.now());
            dictList.setMember(member);
            dictListRepository.save(dictList);
        }
    }
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
