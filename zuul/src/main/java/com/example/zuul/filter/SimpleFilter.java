package com.example.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class SimpleFilter extends ZuulFilter {


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info("{} Request to {}", request.getMethod(), request.getRequestURL().toString());
        var headersName = request.getHeaderNames();
        headersName.asIterator().forEachRemaining((var headerKey) ->
                log.info("Header: {} value: {}", headerKey, request.getHeader(headerKey)));
        return null;
    }
}
