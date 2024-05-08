package com.example.testweb;

import com.example.testweb.threadlocal.ReqResData;
import com.example.testweb.threadlocal.ThreadLocalDataInfoContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class MDCFilter extends OncePerRequestFilter {
    private final ThreadLocalDataInfoContext threadLocalDataInfoContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String traceId = UUID.randomUUID().toString();
            MDC.put("TRACE_ID", traceId);

            MultiReadHttpServletRequest servletRequest = new MultiReadHttpServletRequest(request);

            ServletInputStream inputStream = servletRequest.getInputStream();
            String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

            ContentCachingResponseWrapper contentCachingResponseWrapper = new ContentCachingResponseWrapper(response);
            filterChain.doFilter(servletRequest, contentCachingResponseWrapper);

            String responseBody = new ObjectMapper().readTree(contentCachingResponseWrapper.getContentAsByteArray()).toString();
            threadLocalDataInfoContext.setData(new ReqResData(messageBody, responseBody));

            log.info("[{}] URI: {}, queryString: {}, requestBody: {}, responseBody: {}", traceId, servletRequest.getRequestURI(), servletRequest.getQueryString(), messageBody, responseBody);
            contentCachingResponseWrapper.copyBodyToResponse();
        } finally {
            MDC.clear();
            threadLocalDataInfoContext.clear();
        }
    }
}
