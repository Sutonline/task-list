package cn.kevin.service.impl;

import cn.kevin.dao.AppUpWordMapper;
import cn.kevin.domain.AppUpWord;
import cn.kevin.service.AppUpWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * upword service实现类
 * created by yongkang.zhang
 * added at 2017/11/30
 */
@Service
public class AppUpWordServiceImpl implements AppUpWordService {

    private final AppUpWordMapper mapper;

    @Autowired
    public AppUpWordServiceImpl(AppUpWordMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<AppUpWord> listAll() {
        return mapper.selectAll();
    }

    @Override
    public Boolean delete(Long id) {
        mapper.delete(id);
        return Boolean.TRUE;
    }

    @Override
    public Boolean save(AppUpWord upWord) {
        return mapper.insert(upWord) == 1;
    }
}
