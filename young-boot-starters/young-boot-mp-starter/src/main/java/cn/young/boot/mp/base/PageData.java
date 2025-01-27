package cn.young.boot.mp.base;

import cn.young.boot.common.model.PageBase;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/1/26 21:33
 */
@Getter
@Setter
public class PageData<T> extends PageBase {

    private List<T> records;

    private Long total;


    public static <T> PageData<T> toYoungPageData(IPage<T> page) {
        if (page == null) {
            throw new IllegalArgumentException("page is null");
        } else {
            PageData<T> pageData = new PageData<>();
            pageData.setCurrentPage(page.getCurrent());
            pageData.setPageSize(page.getSize());
            pageData.setTotal(page.getTotal());
            pageData.setRecords(page.getRecords());
            return pageData;
        }
    }
}
