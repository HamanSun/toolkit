package org.relaxation.common.test;

import lombok.extern.slf4j.Slf4j;
import org.relaxation.common.enums.StringFormatterEnum;

import java.util.Date;

@Slf4j
public class StringFormatterTester {
    public static void main(String[] args) {
        log.info("动态字符串");
        System.out.println(String.format(StringFormatterEnum.STRING.getExpress(), "jjsunw"));
        log.info("动态字符");
        System.out.println(String.format(StringFormatterEnum.CHAR.getExpress(), 'Q'));
        log.info("动态整数");
        System.out.println(String.format(StringFormatterEnum.INT.getExpress(), 10));
        log.info("动态全日期");
        System.out.println(String.format(StringFormatterEnum.FULLDATE.getExpress(), new Date()));
        log.info("动态YYYY-MM-DD");
        System.out.println(String.format(StringFormatterEnum.YMDDATE.getExpress(), new Date()));
        log.info("动态MM/DD/YYYY");
        System.out.println(String.format(StringFormatterEnum.MDYDATE.getExpress(), new Date()));
        log.info("动态HH:MM:SS PM");
        System.out.println(String.format(StringFormatterEnum.HMSTIME12.getExpress(), new Date()));
        log.info("动态HH:MM:SS");
        System.out.println(String.format(StringFormatterEnum.HMSTIME24.getExpress(), new Date()));
        log.info("动态HH:MM");
        System.out.println(String.format(StringFormatterEnum.HMTIME24.getExpress(), new Date()));


        System.out.println(String.format("%d%%", 12));
        System.out.println(String.format("%04d", 9));
        System.out.println(String.format("%,f", 111119.11));
    }
}
