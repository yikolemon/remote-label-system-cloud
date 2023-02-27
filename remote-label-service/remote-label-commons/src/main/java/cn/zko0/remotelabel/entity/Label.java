package cn.zko0.remotelabel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2023-02-27
 */
public class Label implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "label_id", type = IdType.AUTO)
    private Integer labelId;


    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 采集器ID
     */
    private Integer collectorId;

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Integer getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(Integer collectorId) {
        this.collectorId = collectorId;
    }

    @Override
    public String toString() {
        return "Label{" +
        ", labelId = " + labelId +
        ", createTime = " + createTime +
        ", collectorId = " + collectorId +
        "}";
    }
}
