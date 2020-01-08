package org.relaxation.mybatisplus.config.multipledatasource;

/**
 * @program DbContextHolder
 * @description: 设置、获取数据源
 * @author: jjsunw
 * @create: 2020/1/6
 **/
public class DbContextHolder {
    private static final ThreadLocal contextHolder = new ThreadLocal<>();

    /**
     * 设置数据源
     *
     * @param dbTypeEnum
     */
    public static void setDbType(DBTypeEnum dbTypeEnum) {
        contextHolder.set(dbTypeEnum.getValue());
    }

    /**
     * 取得当前数据源
     *
     * @return
     */
    public static String getDbType() {
        return (String) contextHolder.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clearDbType() {
        contextHolder.remove();
    }
}
