package com.htg.gateway.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Primary
public class SwaggerProvider implements SwaggerResourcesProvider {

    public static final String API_URI = "/v2/api-docs";

    private final DiscoveryClientRouteDefinitionLocator routeLocator;

    public SwaggerProvider(DiscoveryClientRouteDefinitionLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();

        routeLocator.getRouteDefinitions().subscribe(routeDefinition -> {
            String id = routeDefinition.getId();
            /* log : id is CompositeDiscoveryClient_nacos-consumer*/
            log.info("id is {}", id);
            List<PredicateDefinition> predicates = routeDefinition.getPredicates();
            for (PredicateDefinition definition : predicates) {
                log.info("defintion is {}", definition.toString());
            }
            String serviceId = id.substring("CompositeDiscoveryClient_".length(), id.length());
            String location = predicates.get(0).getArgs().get("pattern").replace("/**", API_URI);
            resources.add(swaggerResource(serviceId, location));

        });
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}
