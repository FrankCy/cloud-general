package com.spring.cloud.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description：
 * @version 1.0
 * @author: Yang.Chang
 * @email: cy880708@163.com
 * @date: 2019/2/12 下午3:38
 * @mofified By:
 */
public final class StringUtil {

    private static final Log LOG = LogFactory.getLog(StringUtil.class);

    /**
     * 私有构造方法,将该工具类设为单例模式.
     */
    private StringUtil() {
    }

    /**
     * 函数功能说明 ： 判断字符串是否为空 . 修改者名字： 修改日期： 修改内容：
     * 
     * @参数： @param str
     * @参数： @return
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }

    /**
     * 函数功能说明 ： 判断对象数组是否为空. 修改者名字： 修改日期： 修改内容：
     * 
     * @参数： @param obj
     * @参数： @return
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(Object[] obj) {
        return null == obj || 0 == obj.length;
    }

    /**
     * 函数功能说明 ： 判断对象是否为空. 修改者名字： 修改日期： 修改内容：
     * 
     * @参数： @param obj
     * @参数： @return
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        }
        if (obj instanceof String) {
            return ((String) obj).trim().isEmpty();
        }
        return !(obj instanceof Number);
    }

    /**
     * 函数功能说明 ： 判断集合是否为空. 修改者名字： 修改日期： 修改内容：
     * 
     * @参数： @param obj
     * @参数： @return
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(List<?> obj) {
        return null == obj || obj.isEmpty();
    }

    /**
     * 函数功能说明 ： 判断Map集合是否为空. 修改者名字： 修改日期： 修改内容：
     * 
     * @参数： @param obj
     * @参数： @return
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(Map<?, ?> obj) {
        return null == obj || obj.isEmpty();
    }

    /**
     * 函数功能说明 ： 获得文件名的后缀名. 修改者名字： 修改日期： 修改内容：
     * 
     * @参数： @param fileName
     * @参数： @return
     * @return String
     * @throws
     */
    public static String getExt(String fileName) {
        return fileName.substring  (fileName.lastIndexOf('.') + 1);
    }

    /**
     * 获取去掉横线的长度为32的UUID串.
     */
    public static String get32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取带横线的长度为36的UUID串.
     */
    public static String get36UUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 验证一个字符串是否完全由纯数字组成的字符串，当字符串为空时也返回false.
     */
    public static boolean isNumeric(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        } else {
            return str.matches("\\d*");
        }
    }

    /**
     * 计算采用utf-8编码方式时字符串所占字节数
     * 
     * @param content
     * @return
     */
    public static int getByteSize(String content) {
        int size = 0;
        if (null != content) {
            try {
                // 汉字采用utf-8编码时占3个字节
                size = content.getBytes("utf-8").length;
            } catch (UnsupportedEncodingException e) {
                LOG.error(e);
            }
        }
        return size;
    }

    /**
     * 函数功能说明 ： 截取字符串拼接in查询参数. 修改者名字： 修改日期： 修改内容：
     * 
     * @参数： @param ids
     * @参数： @return
     * @return String
     * @throws
     */
    public static List<String> getInParam(String param) {
        boolean flag = param.contains(",");
        List<String> list = new ArrayList<>();
        if (flag) {
            list = Arrays.asList(param.split(","));
        } else {
            list.add(param);
        }
        return list;
    }

    /**
     * 生成登录token
     * @return
     */
    public static String getLoginToken(){
        //生成固定token 以便于测试用
        /*if (constant.IS_DEBUG) {
            return constant.LOGIN_TOKEN_DEF;
        }*/
        return EncryptUtil.getCode(get32UUID(), DateUtil.getCurUnixTimeStamp());
    }

    /**
     * 判断字符串变量是否不为空或不为Null
     * @param s
     * @return
     */
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }



    /**
     * 生成4位随机数字
     * @return
     */
    public static String getRandomFour() {
        int random =  (int) (Math.random() * (9999 - 999) + 999);
        return String.valueOf(random);
    }

    /**
     * 安全转换int
     * @param str
     * @return 如果失败，则返回0
     */
    public static int getInt(String str) {
        return getInt(str, 0);
    }

    /**
     * 安全转换int
     * @param str 字符串
     * @param default_value 转换失败的默认值
     * @return
     */
    public static int getInt(String str, int default_value) {
        try{
            return Integer.parseInt(str);
        }catch(NumberFormatException e){
            //System.out.println("异常：\"" + str + "\"不是数字/整数...");
            return default_value;
        }
    }
    /**
     * 下划线转驼峰法
     * @param line 源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰
     * @return 转换后的字符串
     */
    public static String underline2Camel(String line,boolean smallCamel){
        if(line==null||"".equals(line)){
            return "";
        }
        StringBuffer sb=new StringBuffer();
        Pattern pattern=Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(smallCamel&&matcher.start()==0?Character.toLowerCase(word.charAt(0)):Character.toUpperCase(word.charAt(0)));
            int index=word.lastIndexOf('_');
            if(index>0){
                sb.append(word.substring(1, index).toLowerCase());
            }else{
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }
    /**
     * 驼峰法转下划线
     * @param line 源字符串
     * @return 转换后的字符串
     */
    public static String camel2Underline(String line){
        if(line==null||"".equals(line)){
            return "";
        }
        line=String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuffer sb=new StringBuffer();
        Pattern pattern=Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(word.toUpperCase());
            sb.append(matcher.end()==line.length()?"":"_");
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        String line="I_HAVE_AN_IPANG3_PIG";
        String camel=underline2Camel(line,true);
        System.out.println(camel);
        System.out.println(camel2Underline(camel));
    }

}
