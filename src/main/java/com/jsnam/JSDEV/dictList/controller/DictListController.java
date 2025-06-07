package com.jsnam.JSDEV.dictList.controller;

import com.jsnam.JSDEV.dictList.dto.DictListDto;
import com.jsnam.JSDEV.dictList.repository.DictListRepository;
import com.jsnam.JSDEV.dictList.service.DictListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/js-dev/dict")
public class DictListController {
    private final DictListRepository dictListRepository;
    private final DictListService dictListService;

    
    // 리스트
    @GetMapping("/dictList")
    public Map<String, Object> dictList(@RequestParam("dictTitle") String dictTitle) {
        List<DictListDto> list = dictListService.getDictList(dictTitle);
        Map<String, Object> response = new HashMap<>();
        response.put("dictList", list);
        return response;
    }
    
    // 리스트 상세
    @GetMapping("/dictDetail")
    public Map<String, Object> dictListDetail(@RequestParam("dictId") String dictId) {
        Optional<DictListDto> detail = dictListService.getDictDetail(dictId);
        Map<String, Object> response = new HashMap<>();

        if (detail.isPresent()) {
            response.put("dictListDetail", detail.get());
        } else {
            response.put("error", "해당 ID의 데이터가 없습니다.");
        }
        return response;
    }

    // 중복확인
    @GetMapping("/duplicate")
    public Map<String, Object> dictListDuplicate(@RequestParam("dictTitle") String dictTitle) {
        Optional<DictListDto> detail = dictListService.getDictDuplicate(dictTitle);
        Map<String, Object> response = new HashMap<>();

        if (detail.isPresent()) {
            response.put("result", false);
            response.put("dictId", detail.get().getDictId());
        } else {
            response.put("result", true);
        }
        return response;
    }

    // 저장
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> dictListInsert(@RequestBody DictListDto request) {
        Map<String, Object> response = new HashMap<>();

        try {
            DictListDto saved = dictListService.insertDict(request.getDictTitle(), request.getDictDescription());
            response.put("result", true);
            response.put("message", "저장완료");
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            response.put("result", false);
            response.put("message", "저장실패");
            return ResponseEntity.status(500).body(response);
        }
    }

    // 삭제
    @PostMapping("/delete")
    public ResponseEntity<Map<String, Object>> dictListDelete(@RequestBody DictListDto request) {
        Map<String, Object> response = new HashMap<>();

        try {
            DictListDto delete = dictListService.deleteDict(request.getDictId());
            response.put("result", true);
            response.put("message", "삭제완료");
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            response.put("result", false);
            response.put("message", "삭제실패");
            return ResponseEntity.status(500).body(response);
        }
    }

    // 수정
    @PostMapping("/update")
    public ResponseEntity<Map<String, Object>> dictListUpdate(@RequestBody DictListDto request) {
        Map<String, Object> response = new HashMap<>();

        try {
            DictListDto updated = dictListService.updateDict(request);
            response.put("result", true);
            response.put("message", "수정완료");
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            response.put("result", false);
            response.put("message", "수정실패");
            return ResponseEntity.status(500).body(response);
        }
    }
}
