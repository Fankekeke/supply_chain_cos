package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.SupplierRawInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 供应商原料表 service层
 *
 * @author FanK
 */
public interface ISupplierRawInfoService extends IService<SupplierRawInfo> {

    /**
     * 分页查询供应商信息
     *
     * @param page            分页对象
     * @param supplierRawInfo 供应商信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> querySupplierRawPage(Page<SupplierRawInfo> page, SupplierRawInfo supplierRawInfo);
}
