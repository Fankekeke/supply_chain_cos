package cc.mrbird.febs.cos.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单信息
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderRawInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    private String code;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 经手人
     */
    private String handlerCode;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 所属供应商
     */
    private Integer supplierId;

    /**
     * 状态（0.待质检 1.待审批 2.已入库 3.退货）
     */
    private String status;

    /**
     * 质检合格率
     */
    private BigDecimal materialRate;

    /**
     * 产品信息
     */
    @TableField(exist = false)
    private String material;

    @TableField(exist = false)
    private String handlerName;

}
