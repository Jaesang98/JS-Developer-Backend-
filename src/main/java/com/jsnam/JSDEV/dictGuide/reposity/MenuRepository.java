//package com.jsnam.JSDEV.dictGuide.reposity;
//
//import com.jsnam.JSDEV.dictGuide.entity.Menu;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface MenuRepository extends JpaRepository<Menu, String> {
//    List<Menu> findByDeleteYnOrderByMenuId(String deleteYn);
//    List<Menu> findByParentIdAndDeleteYnOrderByMenuId(String parentId, String deleteYn);
//}
//
//
