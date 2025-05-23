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
 * 入库记录
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StorageRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 入库单号
     */
    private String code;

    /**
     * 供应商ID
     */
    private Integer supplierId;

    /**
     * 经手人
     */
    private String handlerCode;

    /**
     * 保管员
     */
    private String custodianCode;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 入库时间
     */
    private String createDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态（0.待质检 1.待审批 2.已入库 3.退货）
     */
    private String status;

    /**
     * 质检合格率
     */
    private BigDecimal materialRate;

    /**
     * 经手人
     */
    @TableField(exist = false)
    private String handlerName;

    /**
     * 物料信息
     */
    @TableField(exist = false)
    private String material;

    /**
     * 保管人
     */
    @TableField(exist = false)
    private String custodianName;

    /**
     * 供应商名称
     */
    @TableField(exist = false)
    private String supplierName;
    private String materialList;

}
