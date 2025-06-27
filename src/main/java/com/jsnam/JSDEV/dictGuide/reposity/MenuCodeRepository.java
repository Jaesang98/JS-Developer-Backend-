package com.jsnam.JSDEV.dictGuide.reposity;

import com.jsnam.JSDEV.dictGuide.entity.MenuCode;
import com.jsnam.JSDEV.dictGuide.entity.MenuDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuCodeRepository extends JpaRepository<MenuCode, String> {
}


