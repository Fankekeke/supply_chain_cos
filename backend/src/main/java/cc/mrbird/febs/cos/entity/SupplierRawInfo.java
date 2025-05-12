package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 供应商原料表
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SupplierRawInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 物料类型
     */
    private Integer materialType;

    /**
     * 计量单位
     */
    private String measurementUnit;

    /**
     * 型号
     */
    private String model;

    /**
     * 所属供应商
     */
    private Integer supplierId;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;


}
