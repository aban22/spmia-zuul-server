package com.thoughtmechanix.core.spmiazuulserver.filters;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Component
public class TrackingFilter extends ZuulFilter {

	private static final int FILTER_ORDER = 1;
	private static final boolean SHOULD_FILTER = true;
	
	private final FilterUtils filterUtils;
	
	@Override
	public boolean shouldFilter() {
		return SHOULD_FILTER;
	}

	@Override
	public Object run() throws ZuulException {
		if(isCorrelationIdPresent()) {
			log.info("tmx-correlation-id found in tracking filter: {}. ", filterUtils.getCorrelationId());
		} else {
            filterUtils.setCorrelationId(generateCorrelationId());
            log.debug("tmx-correlation-id generated in tracking filter: {}.", filterUtils.getCorrelationId());
		}
        RequestContext ctx = RequestContext.getCurrentContext();
        log.debug("Processing incoming request for {}.",  ctx.getRequest().getRequestURI());
		return null;
	}

	@Override
	public String filterType() {
		return FilterUtils.PRE_FILTER_TYPE;
	}

	@Override
	public int filterOrder() {
		return FILTER_ORDER;
	}

	private boolean isCorrelationIdPresent(){
		return (filterUtils.getCorrelationId() != null);
	}
	
	private String generateCorrelationId(){
		return java.util.UUID.randomUUID().toString();
	}
	
}
