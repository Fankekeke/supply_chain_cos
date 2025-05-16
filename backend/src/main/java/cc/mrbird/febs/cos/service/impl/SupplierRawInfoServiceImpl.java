package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.SupplierRawInfo;
import cc.mrbird.febs.cos.dao.SupplierRawInfoMapper;
import cc.mrbird.febs.cos.service.ISupplierRawInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 供应商原料表 实现层
 *
 * @author FanK
 */
@Service
public class SupplierRawInfoServiceImpl extends ServiceImpl<SupplierRawInfoMapper, SupplierRawInfo> implements ISupplierRawInfoService {

    /**
     * 分页查询供应商信息
     *
     * @param page            分页对象
     * @param supplierRawInfo 供应商信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> querySupplierRawPage(Page<SupplierRawInfo> page, SupplierRawInfo supplierRawInfo) {
        return baseMapper.querySupplierRawPage(page, supplierRawInfo);
    }
}
