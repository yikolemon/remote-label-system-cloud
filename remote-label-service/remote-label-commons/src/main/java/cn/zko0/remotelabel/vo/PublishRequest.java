package cn.zko0.remotelabel.vo;

import cn.zko0.remotelabel.enumerate.TerminalResponseCode;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author duanfuqiang
 * @date 2023/2/27 17:27
 * @description
 */
@Data
public class PublishRequest implements Serializable {

    //201,注册成功
    private Integer code;

    public PublishRequest(TerminalResponseCode terminalResponseCode) {
        this.code = terminalResponseCode.getCode();
    }

}
