package com.spring.cloud.common.typehandler;

import com.spring.cloud.common.em.IEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: cloud-general
 * @package: com.spring.cloud.common.typehandler、
 * @email: cy880708@163.com
 * @date: 2019/3/11 上午11:40
 * @mofified By:
 */
public class EnumKeyTypeHandler extends BaseTypeHandler<IEnum> {

    private static Pattern NUMBER_PATTERN = Pattern.compile("^[-\\+]?[\\d]*$");

    private Class<IEnum> type;

    private final IEnum[] enums;

    /**
     * 设置配置文件设置的转换类以及枚举类内容，供其他方法更便捷高效的实现
     * @param type 配置文件中设置的转换类
     */
    public EnumKeyTypeHandler(Class<IEnum> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }

        this.type = type;
        this.enums = type.getEnumConstants();
        if (this.enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }
    }

    @Override
    public IEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if(isInteger(columnName)) {
            // 根据数据库存储类型决定获取类型，本例子中数据库中存放INT类型
            int i = rs.getInt(columnName);
            if (rs.wasNull()) {
                return null;
            } else {
                // 根据数据库中的code值，定位IEnum子类
                return locateIEnum(i);
            }
        } else {
            return locateIEnum(rs.getString(columnName));
        }
    }

    @Override
    public IEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // 根据数据库存储类型决定获取类型，本例子中数据库中存放INT类型
        int i = rs.getInt(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值，定位IEnum子类
            return locateIEnum(i);
        }
    }

    @Override
    public IEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        // 根据数据库存储类型决定获取类型，本例子中数据库中存放INT类型
        int i = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值，定位IEnum子类
            return locateIEnum(i);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, IEnum parameter, JdbcType jdbcType)
            throws SQLException {
        // baseTypeHandler已经帮我们做了parameter的null判断
        if(parameter.getKey() instanceof Integer) {
            ps.setInt(i, (Integer) parameter.getKey());
        } else
        if(parameter.getKey() instanceof String) {
            ps.setString(i, (String)parameter.getKey());
        }
    }

    /**
     * 枚举类型转换，由于构造函数获取了枚举的子类enums，让遍历更加高效快捷
     * @param key 数据库中存储的自定义code属性
     * @return code对应的枚举类
     */
    private IEnum locateIEnum(Object key) {
        for(IEnum status : enums) {
            if(status.getKey() instanceof Integer) {
                if((Integer)status.getKey()== key) {
                    return status;
                }
            } else
            if(status.getKey() instanceof String) {
                if(status.getKey().toString().equals(key)) {
                    return status;
                }
            }

        }
        throw new IllegalArgumentException("未知的枚举类型：" + key + ",请核对" + type.getSimpleName());
    }

    /*
     * 判断是否为整数
     * @param str 传入的字符串
     * @return 是整数返回true,否则返回false
     */

    public static boolean isInteger(String str) {
        return NUMBER_PATTERN.matcher(str).matches();
    }
}
