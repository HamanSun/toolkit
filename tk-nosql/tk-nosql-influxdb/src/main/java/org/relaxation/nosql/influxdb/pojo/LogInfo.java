package org.relaxation.nosql.influxdb.pojo;

import lombok.Builder;
import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

/**
 * @program LogInfo
 * @description: 使用注解demo
 * @author: jjsunw
 * @create: 2020/1/14
 **/
@Data
@Builder
@Measurement(name = "logInfo")
public class LogInfo {
    /**
     * @description: InfluxDB中时间戳均是以UTC时保存, 在保存以及提取过程中需要注意时区转换
     * Column中的name为measurement中的列名
     * @author: jjsunw
     * @date: 2020/1/14
     */
    @Column(name = "time")
    private String time;
    // 注解中添加tag = true,表示当前字段内容为tag内容
    @Column(name = "module", tag = true)
    private String module;
    @Column(name = "level", tag = true)
    private String level;
    @Column(name = "device_id", tag = true)
    private String deviceId;
    @Column(name = "msg")
    private String msg;
}
