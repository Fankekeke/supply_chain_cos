package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ReplyPriceInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 询价信息 service层
 *
 * @author FanK
 */
public interface IReplyPriceInfoService extends IService<ReplyPriceInfo> {

    /**
     * 分页查询询价信息
     *
     * @param page           分页对象
     * @param replyPriceInfo 询价信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryReplyPricePage(Page<ReplyPriceInfo> page, ReplyPriceInfo replyPriceInfo);
}
