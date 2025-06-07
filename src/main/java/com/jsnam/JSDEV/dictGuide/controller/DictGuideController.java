package com.jsnam.JSDEV.dictGuide.controller;

import com.jsnam.JSDEV.dictGuide.dto.MenuDto;
import com.jsnam.JSDEV.dictGuide.dto.MenuNode;
import com.jsnam.JSDEV.dictGuide.reposity.DictGuideRepository;
import com.jsnam.JSDEV.dictGuide.service.DictGuideService;
import com.jsnam.JSDEV.dictList.dto.DictListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/js-dev/guide")
public class DictGuideController {
    private final DictGuideRepository dictGuideRepository;
    private final DictGuideService dictGuideService;

    
    // 리스트
    @GetMapping("/menu")
    public ResponseEntity<Map<String, Object>> getMenuTree() {
        List<MenuDto> flatList = dictGuideService.getMenu();
        List<MenuNode> tree = dictGuideService.buildMenuTree(flatList);
        return ResponseEntity.ok(Map.of("data", tree));

    }


}
