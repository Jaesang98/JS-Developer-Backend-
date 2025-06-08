package com.jsnam.JSDEV.dictGuide.controller;

import com.jsnam.JSDEV.dictGuide.dto.GuideListDto;
import com.jsnam.JSDEV.dictGuide.dto.MenuDto;
import com.jsnam.JSDEV.dictGuide.dto.MenuNode;
import com.jsnam.JSDEV.dictGuide.reposity.DictGuideRepository;
import com.jsnam.JSDEV.dictGuide.service.DictGuideService;
import com.jsnam.JSDEV.dictList.dto.DictListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/js-dev/guide")
public class DictGuideController {
    private final DictGuideRepository dictGuideRepository;
    private final DictGuideService dictGuideService;

    
    // 메뉴
    @GetMapping("/menu")
    public ResponseEntity<Map<String, Object>> getMenuTree() {
        List<MenuDto> flatList = dictGuideService.getMenu();
        List<MenuNode> tree = dictGuideService.buildMenuTree(flatList);
        return ResponseEntity.ok(Map.of("data", tree));

    }

    // 리스트
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getGuideList(@RequestParam String parentId) {
        List<GuideListDto> list = dictGuideService.getGuideList(parentId);
        Map<String, Object> response = new HashMap<>();

        if (list != null && !list.isEmpty()) {
            response.put("data", list);
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "해당 ID의 데이터가 없습니다.");
            return ResponseEntity.status(404).body(response);
        }
    }
}
