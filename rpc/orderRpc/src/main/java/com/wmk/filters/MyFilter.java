package com.wmk.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/25
 * @TIME: 16:10
 **/
@WebFilter(urlPatterns = {"/*"}, filterName = "loginFilter")    //  /*：拦截所有(写成/**则什么也不拦截，应该是不支持这种写法)
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter:"+request.getParameter("name"));

//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        System.out.println("URI:"+httpServletRequest.getRequestURI());  //  /order/addOrderCount
//        System.out.println("URL:"+httpServletRequest.getRequestURL());  //  http://localhost:8421/order/addOrderCount

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
