package cn.kevin.dao;

import cn.kevin.domain.AppUpWord;
import java.util.List;

public interface AppUpWordMapper {

    int insert(AppUpWord record);

    void delete(Long id);

    List<AppUpWord> selectAll();
}