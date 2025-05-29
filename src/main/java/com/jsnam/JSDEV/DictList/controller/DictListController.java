package com.jsnam.JSDEV.DictList.controller;

import com.jsnam.JSDEV.DictList.dto.DictListDto;
import com.jsnam.JSDEV.DictList.entity.DictList;
import com.jsnam.JSDEV.DictList.repository.DictListRepository;
import com.jsnam.JSDEV.DictList.service.DictListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/dictlist")
    @CrossOrigin(origins = "http://localhost:5173")
    public Map<String, Object> dictList() {
        List<DictListDto> list = dictListService.getDictList();
        Map<String, Object> response = new HashMap<>();
        response.put("dictlist", list);
        return response;
    }
    
    // 리스트 상세
    @GetMapping("/dictlist/{id}")
    @CrossOrigin(origins = "http://localhost:5173")
    public Map<String, Object> dictListDetail(@PathVariable("id") String dictId) {
        Optional<DictListDto> detail = dictListService.getDictDetail(dictId);
        Map<String, Object> response = new HashMap<>();

        if (detail.isPresent()) {
            response.put("dictlistDetail", detail.get());
        } else {
            response.put("error", "해당 ID의 데이터가 없습니다.");
        }
        return response;
    }
}
