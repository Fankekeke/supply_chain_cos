package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.SupplierInfo;
import cc.mrbird.febs.cos.entity.ReplyPriceInfo;
import cc.mrbird.febs.cos.service.ISupplierInfoService;
import cc.mrbird.febs.cos.service.IReplyPriceInfoService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 询价信息 控制层
 *
 * @author FanK
 */
@RestController
@RequestMapping("/cos/reply-price-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReplyPriceInfoController {

    private final IReplyPriceInfoService replyPriceInfoService;

    /**
     * 分页查询询价信息
     *
     * @param page           分页对象
     * @param replyPriceInfo 询价信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ReplyPriceInfo> page, ReplyPriceInfo replyPriceInfo) {
        return R.ok(replyPriceInfoService.queryReplyPricePage(page, replyPriceInfo));
    }

    /**
     * 添加询价信息
     *
     * @param replyPriceInfo 询价信息
     * @return 结果
     */
    @PostMapping
    public R add(ReplyPriceInfo replyPriceInfo) {
        replyPriceInfo.setStatus("0");
        replyPriceInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(replyPriceInfoService.save(replyPriceInfo));
    }

    /**
     * 修改询价信息
     *
     * @param replyPriceInfo 询价信息
     * @return 结果
     */
    @PutMapping
    public R edit(ReplyPriceInfo replyPriceInfo) {
        if (StrUtil.isNotEmpty(replyPriceInfo.getReplyContent())) {
            replyPriceInfo.setStatus("1");
        }
        return R.ok(replyPriceInfoService.updateById(replyPriceInfo));
    }

    /**
     * 删除询价信息
     *
     * @param ids 询价信息IDS
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public R deleteIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(replyPriceInfoService.removeByIds(ids));
    }
}
