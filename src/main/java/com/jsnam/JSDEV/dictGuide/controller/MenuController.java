package com.jsnam.JSDEV.dictGuide.controller;

import com.jsnam.JSDEV.dictGuide.dto.MenuDescriptionDto;
import com.jsnam.JSDEV.dictGuide.dto.MenuDto;
import com.jsnam.JSDEV.dictGuide.reposity.MenuRepository;
import com.jsnam.JSDEV.dictGuide.service.MenuService;
import com.jsnam.JSDEV.util.ApiResponse;
import com.jsnam.JSDEV.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/js-dev/guide")
public class MenuController {
    private final MenuRepository dictGuideRepository;
    private final MenuService menuService;


    // 메뉴
    @GetMapping("/menuAll")
    public ResponseEntity<ApiResponse<List<MenuDto>>> getMenuAll() {
        List<MenuDto> result = menuService.getMenuAll();
        try {
            return ResponseUtil.success(result);
        }
        catch (Exception e) {
            return ResponseUtil.fail("실패");
        }
    }

    // 메뉴의 대한 설명
    @GetMapping("/menuDescription")
    public ResponseEntity<ApiResponse<List<MenuDescriptionDto>>> getMenuDetail(@RequestParam(required = false) String parentId) {
        List<MenuDescriptionDto> result = menuService.getMenuDescription(parentId);
        try {
            return ResponseUtil.success(result);
        }
        catch (Exception e) {
            return ResponseUtil.fail("실패");
        }
    }

    // 메뉴
    @GetMapping("/menu")
    public ResponseEntity<ApiResponse<List<MenuDto>>> getMenu(@RequestParam(required = false) String parentId) {
        List<MenuDto> result = menuService.getMenu(parentId);

        try {
            return ResponseUtil.success(result);
        }
        catch (Exception e) {
            return ResponseUtil.fail("실패");
        }
    }

//
//    // 리스트
//    @GetMapping("/list")
//    public ResponseEntity<Map<String, Object>> getGuideList(@RequestParam String parentId) {
//        List<GuideMenuListDto> list = menuService.getDescriptions(parentId);
//        Map<String, Object> response = new HashMap<>();
//
//        if (list != null && !list.isEmpty()) {
//            response.put("data", list);
//            return ResponseEntity.ok(response);
//        } else {
//
//            response.put("error", "해당 ID의 데이터가 없습니다.");
//            return ResponseEntity.status(404).body(response);
//        }
//    }
}
