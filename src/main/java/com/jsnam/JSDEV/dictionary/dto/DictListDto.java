package com.jsnam.JSDEV.dictionary.dto;

import com.jsnam.JSDEV.auth.entity.Member;
import com.jsnam.JSDEV.dictionary.entity.DictList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class DictListDto {
    private String id;
    private String username;
    private String userid;
    private String dictTitle;
    private String dictDescription;
    private LocalDateTime updated;

    public DictListDto(DictList dictList) {
        this.id = dictList.getId();
        this.username = dictList.getMember().getUsername();
        this.userid = dictList.getMember().getEmail();
        this.dictTitle = dictList.getDictTitle();
        this.dictDescription = dictList.getDictDescription();
        this.updated = dictList.getUpdated();
    }
}