package cc.mrbird.febs.cos.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 供应商管理
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SupplierInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * code
     */
    private String code;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 可供采购类型（1.食品生鲜 2.家用电器 3.办公用品 4.日常杂货）
     */
    private Integer purchaseType;

    /**
     * 图片
     */
    private String images;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 所属账户
     */
    private Integer userId;

}
