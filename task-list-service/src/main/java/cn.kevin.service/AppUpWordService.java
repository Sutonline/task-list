package cn.kevin.service;

import cn.kevin.domain.AppUpWord;

import java.util.List;

/**
 * up word
 * created by yongkang.zhang
 * added at 2017/11/30
 */
public interface AppUpWordService {

    List<AppUpWord> listAll();

    Boolean delete(Long id);

    Boolean save(AppUpWord upWord);

}
