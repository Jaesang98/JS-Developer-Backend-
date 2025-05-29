package com.jsnam.JSDEV.DictList.dto;

import com.jsnam.JSDEV.DictList.entity.DictList;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DictListDto {
    private String dictId;
    private String userId;
    private String dictTitle;
    private String dictDescription;
    private String userName;
    private LocalDateTime updated;

    public static DictListDto from(DictList entity) {
        return new DictListDto(
                entity.getDictId(),
                entity.getUserId(),
                entity.getDictTitle(),
                entity.getDictDescription(),
                entity.getUserName(),
                entity.getUpdated()
        );
    }
}