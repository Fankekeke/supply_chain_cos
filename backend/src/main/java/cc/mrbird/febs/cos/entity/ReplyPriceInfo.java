package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 询价信息
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ReplyPriceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 所属供应商
     */
    private Integer supplierId;

    /**
     * 询价内容
     */
    private String content;

    /**
     * 回复内容
     */
    private String replyContent;

    /**
     * 状态（0.未回复 1.已回复）
     */
    private String status;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;


}
