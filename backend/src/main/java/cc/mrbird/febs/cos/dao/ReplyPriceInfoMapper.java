package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.ReplyPriceInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 询价信息 mapper层
 *
 * @author FanK
 */
public interface ReplyPriceInfoMapper extends BaseMapper<ReplyPriceInfo> {

    /**
     * 分页查询询价信息
     *
     * @param page           分页对象
     * @param replyPriceInfo 询价信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryReplyPricePage(Page<ReplyPriceInfo> page, @Param("replyPriceInfo") ReplyPriceInfo replyPriceInfo);
}
