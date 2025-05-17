package cc.mrbird.febs.cos.controller;

import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.OrderRawInfo;
import cc.mrbird.febs.cos.entity.StorageRecord;
import cc.mrbird.febs.cos.entity.SupplierInfo;
import cc.mrbird.febs.cos.service.IOrderRawInfoService;
import cc.mrbird.febs.cos.service.IStorageRecordService;
import cc.mrbird.febs.cos.service.ISupplierInfoService;
import cc.mrbird.febs.system.service.UserService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/supplier-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupplierInfoController {

    private final ISupplierInfoService supplierInfoService;

    private final IOrderRawInfoService orderRawInfoService;

    private final IStorageRecordService storageRecordService;

    private final UserService userService;

    /**
     * 分页查询供应商信息
     *
     * @param page         分页对象
     * @param supplierInfo 供应商信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<SupplierInfo> page, SupplierInfo supplierInfo) {
        return R.ok(supplierInfoService.selectSupplierPage(page, supplierInfo));
    }

    /**
     * 查询供应商详情
     *
     * @param id 供应商ID
     * @return 结果
     */
    @GetMapping("/querySupplierDetail/{id}")
    public R querySupplierDetail(@PathVariable("id") Integer id) {
        // 返回结果
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("supplier", null);
                put("order", Collections.emptyList());
            }
        };

        SupplierInfo supplierInfo = supplierInfoService.getOne(Wrappers.<SupplierInfo>lambdaQuery().eq(SupplierInfo::getUserId, id));
        result.put("supplier", supplierInfo);
        result.put("order", storageRecordService.list(Wrappers.<StorageRecord>lambdaQuery().eq(StorageRecord::getSupplierId, supplierInfo.getId())));
        return R.ok(result);
    }

    /**
     * 获取所有供应商信息
     *
     * @return 结果
     */
    @GetMapping("/list/{materialType}")
    public R list(@PathVariable(value = "materialType", required = false) Integer materialType) {
        return R.ok(supplierInfoService.list(Wrappers.<SupplierInfo>lambdaQuery().eq(materialType != -1, SupplierInfo::getPurchaseType, materialType)));
    }

    /**
//     * 添加供应商信息
     *
     * @param supplierInfo 供应商信息
     * @return 结果
     */
    @PostMapping
    public R add(SupplierInfo supplierInfo) throws Exception {
        supplierInfo.setCode("SUP-" + System.currentTimeMillis());
        supplierInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        userService.registSupplier(supplierInfo.getCode(), "1234qwer", supplierInfo);
        return R.ok(true);
    }

    /**
     * 修改供应商信息
     *
     * @param supplierInfo 供应商信息
     * @return 结果
     */
    @PutMapping
    public R edit(SupplierInfo supplierInfo) {
        return R.ok(supplierInfoService.updateById(supplierInfo));
    }

    /**
     * 删除供应商信息
     *
     * @param ids 供应商信息IDS
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public R deleteIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(supplierInfoService.removeByIds(ids));
    }
}
