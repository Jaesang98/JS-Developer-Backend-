package com.jsnam.JSDEV.dictGuide.reposity;

import com.jsnam.JSDEV.dictGuide.entity.Menu;
import com.jsnam.JSDEV.dictList.entity.DictList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DictGuideRepository extends JpaRepository<Menu, String> {
    List<Menu> findByDeleteYn(String deleteYn);
}


