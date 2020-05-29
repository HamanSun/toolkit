package org.relaxation.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StringFormatterEnum {
    STRING("%s", "字符串类型"),
    CHAR("%c","字符类型"),
    BOOL("%b","布尔类型"),
    INT("%d","10进制整型"),
    XINT("%x","16进制整型"),
    OINT("%o","8进制整型"),
    FLOAT("%f","浮点型"),
    XFLOAT("%a","16进制浮点型"),
    EXPONENT("%e","指数类型"),
    PERCENT("%%","百分比类型"),
    FULLDATE("%tc","全部日期与时间信息"),
    YMDDATE("%tF","年-月-日"),
    MDYDATE("%tD","月/日/年"),
    HMSTIME12("%tr","HH:MM:SS PM格式"),
    HMSTIME24("%tT","HH:MM:SS格式"),
    HMTIME24("%tR","HH:MM格式");
    private String express;
    private String desc;

}
