package cn.zko0.remotelabel.enumerate;
public enum TerminalResponseCode {
        
        REGISTER_SUC(201);
        TerminalResponseCode(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }

        private Integer code;

}