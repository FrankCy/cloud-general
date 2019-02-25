package com.spring.cloud.common.util;

import com.spring.cloud.common.po.Company;
import org.apache.commons.beanutils.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: bdjr-cloud-general
 * @package: com.spring.cloud.common.util、
 * @email: cy880708@163.com
 * @date: 2019/2/25 下午1:33
 * @mofified By:
 */
public class MapUtil {

    /**
     * 要被包装的Map
     */
    Map map;

    public MapUtil() {
        this.map = new HashMap();
    }

    /**
     * @description：构建HashMap
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/2/25 下午1:39
     * @mofified By:
     */
    public MapUtil(Map map) {
        if (map == null) {
            this.map = new HashMap();
        } else {
            this.map = map;
        }
    }

    public MapUtil(Map map, Map spare) {
        if (map == null) {
            this.map = spare;
        } else {
            this.map = map;
        }
    }

    public Map getMap() {
        return map;
    }

    public boolean notEmpty(){
        return this.map.size() > 0;
    }

    public boolean notEmpty(Object key){
        return this.map.containsKey(key) && this.map.get(key) != null && !TextUtil.isEmpty(this.getString(key));
    }

    public boolean isEmpty(){
        return this.map.size() <= 0;
    }

    public boolean isEmpty(Object key){
        return !this.map.containsKey(key) || TextUtil.isEmpty(this.getString(key));
    }

    public Object get(Object key){
        return this.map.get(key);
    }

    public void put(Object key,Object value){
        this.map.put(key,value);
    }

    public Integer getInteger(Object key) {
        return getInteger(key, null);
    }

    public Integer getInteger(Object key, Integer spare) {
        if (this.map.containsKey(key)) {
            Object o = this.map.get(key);
            if (o instanceof Integer) {
                return (Integer) o;
            } else {
                try {
                    return Integer.valueOf(o.toString());
                } catch (NumberFormatException e) {
                    return spare;
                }
            }
        }
        return spare;
    }

    public Long getLong(Object key) {
        return getLong(key, null);
    }

    public Long getLong(Object key, Long spare) {
        if (this.map.containsKey(key)) {
            Object o = this.map.get(key);
            if (o instanceof Long) {
                return (Long) o;
            } else {
                try {
                    return Long.valueOf(o.toString());
                } catch (NumberFormatException e) {
                    return spare;
                }
            }
        }
        return spare;
    }

    public Double getDouble(Object key){
        return getDouble(key,null);
    }

    public Double getDouble(Object key, Double spare) {
        if (this.map.containsKey(key)) {
            Object o = this.map.get(key);
            if (o instanceof Double) {
                return (Double) o;
            } else {
                try {
                    return Double.valueOf(o.toString());
                } catch (NumberFormatException e) {
                    return spare;
                }
            }
        }
        return spare;
    }

    public String getString(Object key) {
        return getString(key, null);
    }

    public String getString(Object key, String spare) {
        if (this.map.containsKey(key)) {
            return String.valueOf(this.map.get(key));
        }
        return spare;
    }

    public int size(){
        return this.map.size();
    }

    public boolean valueEquation(Object key, Object val) {
        Object o = this.map.get(key);
        if (o == val) {
            return true;
        } else {
            return val.equals(val);
        }
    }

    /**
     * @function 保留键存在于参数中的的值
     * @param keys
     */
    public void retain(Object... keys){
        Map tmp = this.map;
        this.map.clear();
        for(Object key:keys){
            this.map.put(key,tmp.get(key));
        }
    }
}

class TextUtil {
    /**
     * Returns true if the string is null or 0-length.
     *
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the length that the specified CharSequence would have if
     * spaces and control characters were trimmed from the start and end,
     * as by {@link String#trim}.
     */
    public static int getTrimmedLength(CharSequence s) {
        int len = s.length();

        int start = 0;
        while (start < len && s.charAt(start) <= ' ') {
            start++;
        }

        int end = len;
        while (end > start && s.charAt(end - 1) <= ' ') {
            end--;
        }

        return end - start;
    }

    public static String stringOf(Object object) {
        if (object == null) {
            return null;
        } else {
            return object.toString();
        }

    }

    public static Boolean isInteger(String str) {
        return str != null && str.matches("[0-9]*");
    }

    public static Boolean isDecimal(String str){
        return str!=null && str.matches("^[0-9]+\\.([0-9]+)?$");
    }

    public static Boolean isNumber(String str){
        return str!= null && str.matches("^[0-9]+(\\.[0-9]+)?$");
    }

    public static Integer parseInt(String str,int standby){
        if(str == null) return standby;
        try {
            return Integer.parseInt(str);
        }catch (NumberFormatException e){
            return standby;
        }
    }

    public static String getFileRandomName(String superaddition){
        String name = System.currentTimeMillis() + "_";
        int random = (int) (Math.random() * 9000 + 1000);
        name += random;
        if(superaddition != null){
            name += "_" + superaddition;
        }
        return name;
    }

    /**
     * @description：Map转Bean
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/2/25 下午1:33
     * @mofified By:
     */
    public static <T> T map2Bean(Map<String, String> map, Class<T> class1) {
        T bean = null;
        try {
            bean = class1.newInstance();
            BeanUtils.populate(bean, map);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * @description：bean转Map
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/2/25 下午1:35
     * @mofified By:
     */
    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if(obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter!=null ? getter.invoke(obj) : null;
            map.put(key, value);
        }

        return map;
    }

    public static void main(String[] args) {
        Company company = new Company();
        company.setcId(1);
        company.setcName("公司名");
        company.setcDes("描述");
        company.setcCode("code");

        try {
            Map<String, Object> map = objectToMap(company);
            for (String key :map.keySet()) {
                System.out.println(key + " : " + map.get(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

