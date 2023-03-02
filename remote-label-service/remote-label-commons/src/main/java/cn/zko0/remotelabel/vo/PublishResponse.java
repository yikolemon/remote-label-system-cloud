package cn.zko0.remotelabel.vo;

import cn.zko0.remotelabel.enumerate.TerminalResponseCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author duanfuqiang
 * @date 2023/2/27 17:27
 * @description
 */
@Data
public class PublishResponse implements Serializable {

    //201,注册成功
    private Integer code;
    public PublishResponse(TerminalResponseCode terminalResponseCode) {
        this.code = terminalResponseCode.getCode();
    }

}
