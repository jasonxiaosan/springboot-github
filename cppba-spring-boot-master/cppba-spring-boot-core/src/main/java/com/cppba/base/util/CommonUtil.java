package com.cppba.base.util;

import com.cppba.base.bean.Result;
import com.cppba.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 开发者
 * nickName:大黄蜂
 * email:245655812@qq.com
 * github:https://github.com/bigbeef
 */
public class CommonUtil {

    private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    //判断是否是ajax请求
    public static boolean isAjaxRequerst(HttpServletRequest request){
        String requestType = request.getHeader("X-Requested-With");
        if(StringUtils.equals(requestType,"XMLHttpRequest")){
            return true;
        }
        return false;
    }
    
    //获取session
    public static HttpSession getSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        //Subject currentUser = SecurityUtils.getSubject();
        //Session session = currentUser.getSession();
        return session;
    }
    
    //从session中获取user
    public static User getUserFromSession(HttpServletRequest request){
        HttpSession session = getSession(request);
        User user = (User) session.getAttribute("user");
        return user;
    }

    public static void responseBuildJson(Result result, HttpServletResponse response){
        String json = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        writeToResponse(json,response);
    }
    
    //获取coockie
    public static String getCookie(String name,HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie c :cookies ){
            if(c.getName().equals(name)){
                return c.getValue();
            }
        }
        return null;
    }

    //判断Integer为空或者等于0
    public static boolean isIntegerNullOrZero(Integer integer){
        return integer==null || !ObjectUtils.notEqual(integer,0);
    }

    //判断Long为空或者等于0
    public static boolean isLongNullOrZero(Long l){
        return l==null || !ObjectUtils.notEqual(l,0L);
    }

    //获取项目根路径
    public static String getBasePath(HttpServletRequest request){
        String path = request.getContextPath();
        return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    }

    //根获取请求终端IP
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            java.net.InetAddress addr = null;
            try {
                addr = java.net.InetAddress.getLocalHost();
            } catch (java.net.UnknownHostException e) {
                e.printStackTrace();
            }
            ip = addr.getHostAddress()==null?"":addr.getHostAddress();// 获得本机IP
        }
        return ip;
    }
    
    protected static void writeToResponse(String json,HttpServletResponse response){
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter writer;
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
