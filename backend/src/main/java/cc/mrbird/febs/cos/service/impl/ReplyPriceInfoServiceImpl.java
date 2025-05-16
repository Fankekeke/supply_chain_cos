package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ReplyPriceInfo;
import cc.mrbird.febs.cos.dao.ReplyPriceInfoMapper;
import cc.mrbird.febs.cos.service.IReplyPriceInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 询价信息 实现层
 *
 * @author FanK
 */
@Service
public class ReplyPriceInfoServiceImpl extends ServiceImpl<ReplyPriceInfoMapper, ReplyPriceInfo> implements IReplyPriceInfoService {

    /**
     * 分页查询询价信息
     *
     * @param page           分页对象
     * @param replyPriceInfo 询价信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryReplyPricePage(Page<ReplyPriceInfo> page, ReplyPriceInfo replyPriceInfo) {
        return baseMapper.queryReplyPricePage(page, replyPriceInfo);
    }
}
