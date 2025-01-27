package cn.young.boot.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Mole. meiko_ooo@163.com
 * @since 2025/1/26 21:49
 */
@Data
public class PageBase implements Serializable {

    private Long currentPage = 1L;

    private Long pageSize = 10L;
}
