//package com.jsnam.JSDEV.dictGuide.controller;
//
//import com.jsnam.JSDEV.dictGuide.dto.GuideMenuListDto;
//import com.jsnam.JSDEV.dictGuide.dto.GuideMenuDto;
//import com.jsnam.JSDEV.dictGuide.dto.GuideMenuNode;
//import com.jsnam.JSDEV.dictGuide.reposity.MenuRepository;
//import com.jsnam.JSDEV.dictGuide.service.MenuService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/js-dev/guide")
//public class GuideMenuController {
//    private final MenuRepository dictGuideRepository;
//    private final MenuService menuService;
//
//
//    // 메뉴
//    @GetMapping("/menu")
//    public ResponseEntity<Map<String, Object>> getMenuTree() {
//        List<GuideMenuDto> flatList = menuService.getMenu();
//        List<GuideMenuNode> tree = menuService.buildMenuTree(flatList);
//        return ResponseEntity.ok(Map.of("data", tree));
//
//    }
//
//    @GetMapping("/menuDetail")
//    public ResponseEntity<List<GuideMenuDto>> getMenu(@RequestParam(required = false) String parentId) {
//        return ResponseEntity.ok(menuService.getMenuByParentId(parentId));
//    }
//
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
//}
