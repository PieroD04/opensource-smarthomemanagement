package com.smarthome.platform.upc.analytics.interfaces.rest;

import com.smarthome.platform.upc.analytics.domain.model.queries.GetPerformanceIndicatorByIdQuery;
import com.smarthome.platform.upc.analytics.domain.services.PerformanceIndicatorCommandService;
import com.smarthome.platform.upc.analytics.domain.services.PerformanceIndicatorQueryService;
import com.smarthome.platform.upc.analytics.interfaces.rest.resources.CreatePerformanceIndicatorResource;
import com.smarthome.platform.upc.analytics.interfaces.rest.resources.PerformanceIndicatorResource;
import com.smarthome.platform.upc.analytics.interfaces.rest.transform.CreatePerformanceIndicatorCommandFromResourceAssembler;
import com.smarthome.platform.upc.analytics.interfaces.rest.transform.PerformanceIndicatorResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="/api/v1/performance-indicators", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Performance Indicators", description = "Performance Indicator Management Endpoints")
public class PerformanceIndicatorsController {
    private final PerformanceIndicatorCommandService performanceIndicatorCommandService;
    private final PerformanceIndicatorQueryService performanceIndicatorQueryService;

    public PerformanceIndicatorsController(PerformanceIndicatorCommandService performanceIndicatorCommandService, PerformanceIndicatorQueryService performanceIndicatorQueryService) {
        this.performanceIndicatorCommandService = performanceIndicatorCommandService;
        this.performanceIndicatorQueryService = performanceIndicatorQueryService;
    }

    @PostMapping
    public ResponseEntity<?> createPerformanceIndicator(@RequestBody CreatePerformanceIndicatorResource createPerformanceIndicatorResource){
        var createPerformanceIndicatorCommand = CreatePerformanceIndicatorCommandFromResourceAssembler.toCommandFromResource(createPerformanceIndicatorResource);
        Long performanceIndicatorId;
        try {
            performanceIndicatorId = performanceIndicatorCommandService.handle(createPerformanceIndicatorCommand);
        } catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(e.getMessage());
        }
        if(performanceIndicatorId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var getPerformanceIndicatorByIdQuery = new GetPerformanceIndicatorByIdQuery(performanceIndicatorId);
        var performanceIndicator = performanceIndicatorQueryService.handle(getPerformanceIndicatorByIdQuery);
        if(performanceIndicator.isEmpty()){ return ResponseEntity.badRequest().build(); }
        var performanceIndicatorResource = PerformanceIndicatorResourceFromEntityAssembler.toResourceFromEntity(performanceIndicator.get());
        return new ResponseEntity<>(performanceIndicatorResource, HttpStatus.CREATED);
    }
}
