package cn.zko0.remotelabel.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2023-03-01
 */
@TableName("space_status")
public class SpaceStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer spaceId;

    /**
     * 0为错误，1为正常
     */
    private Boolean status;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 记录时间
     */
    private Date recordTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Integer spaceId) {
        this.spaceId = spaceId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    @Override
    public String toString() {
        return "Status{" +
        ", id = " + id +
        ", spaceId = " + spaceId +
        ", status = " + status +
        ", errorMsg = " + errorMsg +
        ", recordTime = " + recordTime +
        "}";
    }
}
