package com.jsnam.JSDEV.DictList.controller;

import com.jsnam.JSDEV.DictList.dto.DictListDto;
import com.jsnam.JSDEV.DictList.entity.DictList;
import com.jsnam.JSDEV.DictList.repository.DictListRepository;
import com.jsnam.JSDEV.DictList.service.DictListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class DictListController {
    private final DictListRepository dictListRepository;
    private final DictListService dictListService;

    
    // 리스트
    @GetMapping("/dictList")
    @CrossOrigin(origins = "http://localhost:5173")
    public Map<String, Object> dictList(@RequestParam("dictTitle") String dictTitle) {
        List<DictListDto> list = dictListService.getDictList(dictTitle);
        Map<String, Object> response = new HashMap<>();
        response.put("dictList", list);
        return response;
    }
    
    // 리스트 상세
    @GetMapping("/dictDetail")
    @CrossOrigin(origins = "http://localhost:5173")
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
    @CrossOrigin(origins = "http://localhost:5173")
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
    @CrossOrigin(origins = "http://localhost:5173")
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
}
