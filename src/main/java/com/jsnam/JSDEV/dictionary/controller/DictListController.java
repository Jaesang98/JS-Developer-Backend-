package com.jsnam.JSDEV.dictionary.controller;

import com.jsnam.JSDEV.dictionary.dto.DictListDto;
import com.jsnam.JSDEV.dictionary.entity.DictList;
import com.jsnam.JSDEV.dictionary.repository.DictListRepository;
import com.jsnam.JSDEV.dictionary.service.DictListService;
import com.jsnam.JSDEV.util.ApiResponse;
import com.jsnam.JSDEV.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/js-dev/dictionary")
public class DictListController {
    private final DictListRepository dictListRepository;
    private final DictListService dictListService;


    // 리스트
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<DictListDto>>> dictList(@RequestParam("search") String search) {

        List<DictListDto> result = dictListService.getList(search);

        try {
            return ResponseUtil.success(result);
        }
        catch (Exception e) {
            return ResponseUtil.fail("실패");
        }
    }

    // 리스트 상세
    @GetMapping("/detail")
    public ResponseEntity<ApiResponse<DictListDto>> dictDetail(@RequestParam("id") String id) {
        Optional<DictListDto> result = dictListService.getDetail(id);

        try {
            if(result.isPresent()) {
                return ResponseUtil.success(result.get());
            }
            else {
                return ResponseUtil.fail("없음");
            }
        }
        catch (Exception e) {
            return ResponseUtil.fail("실패");
        }
    }

    // 리스트 상세 삭제
    @PostMapping("/delete")
    public ResponseEntity<ApiResponse<String>> dictDelete(@RequestBody DictListDto request) {
        try {
            dictListService.deleteDict(request.getId());
            return ResponseUtil.success("삭제 완료");
        }
        catch (Exception e) {
            return ResponseUtil.fail("실패");
        }
    }
//
//    // 중복확인
//    @GetMapping("/duplicate")
//    public Map<String, Object> dictListDuplicate(@RequestParam("dictTitle") String dictTitle) {
//        Optional<DictListDto> detail = dictListService.getDictDuplicate(dictTitle);
//        Map<String, Object> response = new HashMap<>();
//
//        if (detail.isPresent()) {
//            response.put("result", false);
//            response.put("dictId", detail.get().getDictId());
//        } else {
//            response.put("result", true);
//        }
//        return response;
//    }
//
//    // 저장
//    @PostMapping("/add")
//    public ResponseEntity<Map<String, Object>> dictListInsert(@RequestBody DictListDto request) {
//        Map<String, Object> response = new HashMap<>();
//
//        try {
//            DictListDto saved = dictListService.insertDict(request.getDictTitle(), request.getDictDescription());
//            response.put("result", true);
//            response.put("message", "저장완료");
//            return ResponseEntity.ok(response);
//        }
//        catch (Exception e) {
//            response.put("result", false);
//            response.put("message", "저장실패");
//            return ResponseEntity.status(500).body(response);
//        }
//    }
//
//
//    // 수정
//    @PostMapping("/update")
//    public ResponseEntity<Map<String, Object>> dictListUpdate(@RequestBody DictListDto request) {
//        Map<String, Object> response = new HashMap<>();
//
//        try {
//            DictListDto updated = dictListService.updateDict(request);
//            response.put("result", true);
//            response.put("message", "수정완료");
//            return ResponseEntity.ok(response);
//        }
//        catch (Exception e) {
//            response.put("result", false);
//            response.put("message", "수정실패");
//            return ResponseEntity.status(500).body(response);
//        }
//    }
}
