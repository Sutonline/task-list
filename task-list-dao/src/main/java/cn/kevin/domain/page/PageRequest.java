package cn.kevin.domain.page;

/**
 * pageRequest 分页请求
 */
public class PageRequest {

    /**
     * 分页页数
     */
    private int pageIndex;

    /**
     * 分页大小
     */
    private int pageSize;


    public PageRequest(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return (this.pageIndex - 1) * pageSize;
    }
}
