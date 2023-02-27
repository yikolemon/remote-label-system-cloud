package cn.zko0.remotelabel.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2023-02-23
 */
@TableName("user_space")
public class UserSapce implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer userId;
    private Integer spaceId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Integer spaceId) {
        this.spaceId = spaceId;
    }

    @Override
    public String toString() {
        return "Space{" +
        ", userId = " + userId +
        ", spaceId = " + spaceId +
        "}";
    }
}
