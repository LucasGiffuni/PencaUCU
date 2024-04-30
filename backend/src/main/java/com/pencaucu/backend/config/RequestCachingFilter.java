package com.pencaucu.backend.config;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
@WebFilter(filterName = "RequestCachingFilter", urlPatterns = "/*")
public class RequestCachingFilter extends OncePerRequestFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(RequestCachingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        CachedHttpServletRequest cachedHttpServletRequest = new CachedHttpServletRequest(request);

        InputStream bufferedInputStream = new BufferedInputStream(request.getInputStream());

        LOGGER.info("---------------------------------");

        LOGGER.info("REQUEST METHOD: " + cachedHttpServletRequest.getMethod());
        LOGGER.info("REQUEST INPUT: " + bufferedInputStream);

        LOGGER.info("---------------------------------");

        filterChain.doFilter(cachedHttpServletRequest, response);
    }
}