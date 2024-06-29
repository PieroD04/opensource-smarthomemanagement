package com.smarthome.platform.upc.analytics.domain.services;

import com.smarthome.platform.upc.analytics.domain.model.aggregates.PerformanceIndicator;
import com.smarthome.platform.upc.analytics.domain.model.queries.GetPerformanceIndicatorByIdQuery;

import java.util.Optional;

public interface PerformanceIndicatorQueryService {
    Optional<PerformanceIndicator> handle(GetPerformanceIndicatorByIdQuery query);
}
