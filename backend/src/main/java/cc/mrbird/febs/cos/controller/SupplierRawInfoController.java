package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.SupplierInfo;
import cc.mrbird.febs.cos.entity.SupplierRawInfo;
import cc.mrbird.febs.cos.service.ISupplierInfoService;
import cc.mrbird.febs.cos.service.ISupplierRawInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 供应商原料原料表 控制层
 *
 * @author FanK
 */
@RestController
@RequestMapping("/cos/supplier-raw-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupplierRawInfoController {

    private final ISupplierRawInfoService supplierRawInfoService;

    private final ISupplierInfoService supplierInfoService;

    /**
     * 分页查询供应商原料信息
     *
     * @param page            分页对象
     * @param supplierRawInfo 供应商原料信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<SupplierRawInfo> page, SupplierRawInfo supplierRawInfo) {
        return R.ok(supplierRawInfoService.querySupplierRawPage(page, supplierRawInfo));
    }

    /**
     * 添加供应商原料信息
     *
     * @param supplierRawInfo 供应商原料信息
     * @return 结果
     */
    @PostMapping
    public R add(SupplierRawInfo supplierRawInfo) {
        supplierRawInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        SupplierInfo supplierInfo = supplierInfoService.getOne(Wrappers.<SupplierInfo>lambdaQuery().eq(SupplierInfo::getUserId, supplierRawInfo.getSupplierId()));
        if (supplierInfo != null) {
            supplierRawInfo.setSupplierId(supplierInfo.getId());
        }
        return R.ok(supplierRawInfoService.save(supplierRawInfo));
    }

    /**
     * 修改供应商原料信息
     *
     * @param supplierRawInfo 供应商原料信息
     * @return 结果
     */
    @PutMapping
    public R edit(SupplierRawInfo supplierRawInfo) {
        return R.ok(supplierRawInfoService.updateById(supplierRawInfo));
    }

    /**
     * 删除供应商原料信息
     *
     * @param ids 供应商原料信息IDS
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public R deleteIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(supplierRawInfoService.removeByIds(ids));
    }
}
