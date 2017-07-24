package cn.kevin.domain.page;

import lombok.Data;

import java.util.List;

/**
 * 分页实体
 */
@Data
public class Page<T> {

    private int pageIndex;
    private int pageSize;
    private int totalItems;
    private List<T> list;
}
