package com.github.whatsame.springweb.study.handlermethodargumentresolver.resolver;

import com.github.whatsame.springweb.study.handlermethodargumentresolver.annotation.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class HeaderUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.getParameterAnnotation(User.class) != null;
    }

    @Override
    public Object resolveArgument(
        final MethodParameter parameter,
        final ModelAndViewContainer mavContainer,
        final NativeWebRequest webRequest,
        final WebDataBinderFactory binderFactory
    )
        throws Exception {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        return Long.valueOf(request.getHeader("memberId"));
    }
}
