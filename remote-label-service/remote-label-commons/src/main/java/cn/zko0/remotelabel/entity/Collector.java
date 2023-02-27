package cn.zko0.remotelabel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class Collector implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 采集器自增ID	
     */

    @TableId(type = IdType.AUTO)
    private Integer collectorId;

    /**
     * 采集器注册登录ID
     */
    private Integer clientId;


    /**
     * 注册时间
     */
    private Date createTime;

    /**
     * 是否可用 1正常  0冻结
     */
    private Boolean isAvailable;
    private Integer spaceId;

    public Integer getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(Integer collectorId) {
        this.collectorId = collectorId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Integer getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Integer spaceId) {
        this.spaceId = spaceId;
    }

    @Override
    public String toString() {
        return "Collector{" +
        ", collectorId = " + collectorId +
        ", userId = " + clientId +
        ", createTime = " + createTime +
        ", isAvailable = " + isAvailable +
        ", spaceId = " + spaceId +
        "}";
    }
}
