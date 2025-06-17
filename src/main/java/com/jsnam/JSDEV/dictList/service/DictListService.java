//package com.jsnam.JSDEV.dictList.service;
//
//import com.jsnam.JSDEV.auth.entity.Member;
//import com.jsnam.JSDEV.auth.repository.MemberRepository;
//import com.jsnam.JSDEV.dictList.dto.DictListDto;
//import com.jsnam.JSDEV.dictList.entity.DictList;
//import com.jsnam.JSDEV.dictList.repository.DictListRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class DictListService {
//    private final DictListRepository dictListRepository;
//    private final MemberRepository memberRepository;
//
//    public List<DictListDto> getDictList (String dictTitle) {
//        return dictListRepository.findByList(dictTitle).stream().map(DictListDto::from).toList();
//    }
//
//    public Optional<DictListDto> getDictDetail (String dictId) {
//        return dictListRepository.findByDictId(dictId).map(DictListDto::from);
//    }
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
//    public DictListDto deleteDict (String dictId) {
//        DictList dictList = dictListRepository.findById(dictId)
//                .orElseThrow(() -> new RuntimeException("해당 ID의 단어가 존재하지 않습니다."));
//        dictList.setDeleteYn("Y");
//        DictList delete = dictListRepository.save(dictList);
//        return DictListDto.from(delete);
//    }
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
//}
