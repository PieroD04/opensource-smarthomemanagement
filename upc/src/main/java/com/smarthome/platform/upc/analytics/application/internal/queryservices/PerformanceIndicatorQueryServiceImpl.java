package com.smarthome.platform.upc.analytics.application.internal.queryservices;

import com.smarthome.platform.upc.analytics.domain.model.aggregates.PerformanceIndicator;
import com.smarthome.platform.upc.analytics.domain.model.queries.GetPerformanceIndicatorByIdQuery;
import com.smarthome.platform.upc.analytics.domain.services.PerformanceIndicatorQueryService;
import com.smarthome.platform.upc.analytics.infrastructure.persistence.jpa.repositories.PerformanceIndicatorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerformanceIndicatorQueryServiceImpl implements PerformanceIndicatorQueryService {
    private final PerformanceIndicatorRepository performanceIndicatorRepository;

    public PerformanceIndicatorQueryServiceImpl(PerformanceIndicatorRepository performanceIndicatorRepository) {
        this.performanceIndicatorRepository = performanceIndicatorRepository;
    }

    @Override
    public Optional<PerformanceIndicator> handle(GetPerformanceIndicatorByIdQuery query) {
        return performanceIndicatorRepository.findById(query.performanceIndicatorId());
    }
}
