package com.jsnam.JSDEV.DictList.repository;

import com.jsnam.JSDEV.DictList.entity.DictList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DictListRepository extends JpaRepository<DictList, String> {
    List<DictList> findByDeleteYn(String deleteYn);
    Optional<DictList> findByDictIdAndDeleteYn(String dictId, String deleteYn);
}


