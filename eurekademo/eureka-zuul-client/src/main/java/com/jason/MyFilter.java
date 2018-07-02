package com.jason;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * Created by Administrator on 2018/6/25.
 */
@Component
public class MyFilter  extends ZuulFilter{

    private  static org.slf4j.Logger log = LoggerFactory.getLogger(MyFilter.class);
    @Override
    public String filterType(){
        return PRE_TYPE;
    }
    @Override
    public int filterOrder(){
        return 0;
    }
    @Override
    public boolean shouldFilter(){
        return true;
    }
    @Override
    public  Object run(){
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Object accessToken = request.getParameter("token");
        if(accessToken == null)
        {
            log.warn("token is null");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try{
                ctx.getResponse().getWriter().write("token is null");
            }catch(Exception e){
                return  null;
            }
        }
        log.info("ok");
        return null;
    }
}
