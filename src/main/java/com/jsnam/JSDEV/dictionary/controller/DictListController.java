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
    public ResponseEntity<ApiResponse<String>> dictDelete(@RequestBody DictList request) {
        try {
            dictListService.deleteDict(request.getId());
            return ResponseUtil.success("삭제 완료");
        }
        catch (Exception e) {
            return ResponseUtil.fail("실패");
        }
    }

    // 중복확인
    @GetMapping("/check-id")
    public ResponseEntity<ApiResponse<String>> dictCheck(@RequestParam("title") String title) {
        try {
            boolean exists = dictListService.isTitleExist(title);
            if (exists) {
                return ResponseUtil.fail("현재 존재하는 단어입니다.");
            } else {
                return ResponseUtil.success("사용 가능한 단어입니다.");
            }
        } catch (Exception e) {
            return ResponseUtil.fail("중복 확인에 실패하였습니다.");
        }
    }

    // 저장 수정 둘다됨
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<String>> dictInsert(@RequestBody DictList request) {
        try {
            dictListService.insertDict(request);
            return ResponseUtil.success("저장 완료");
        }
        catch (Exception e) {
            return ResponseUtil.fail("저장 실패");
        }
    }
}
