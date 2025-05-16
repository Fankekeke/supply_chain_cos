package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.SupplierRawInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 供应商原料表 mapper层
 *
 * @author FanK
 */
public interface SupplierRawInfoMapper extends BaseMapper<SupplierRawInfo> {

    /**
     * 分页查询供应商信息
     *
     * @param page            分页对象
     * @param supplierRawInfo 供应商信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> querySupplierRawPage(Page<SupplierRawInfo> page, @Param("supplierRawInfo") SupplierRawInfo supplierRawInfo);
}
