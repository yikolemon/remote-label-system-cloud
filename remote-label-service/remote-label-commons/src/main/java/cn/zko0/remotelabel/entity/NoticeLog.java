package cn.zko0.remotelabel.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2023-03-01
 */
@TableName("notice_log")
public class NoticeLog implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer noticeId;

    /**
     * 0:email 1:message
     */
    private Integer type;
    private Integer phoneNumber;
    private String email;

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Log{" +
        ", noticeId = " + noticeId +
        ", type = " + type +
        ", phoneNumber = " + phoneNumber +
        ", email = " + email +
        "}";
    }
}
