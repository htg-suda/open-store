package com.htg.gateway.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// https://www.cnblogs.com/vieruodis/p/13343782.html
@Slf4j
@Component
@Primary
public class SwaggerProvider implements SwaggerResourcesProvider {
    private GatewayProperties gatewayProperties;
    private RouteLocator routeLocator;
    public static final String API_URI = "/v3/api-docs";


    // private DiscoveryClientRouteDefinitionLocator routeLocator;


    public SwaggerProvider(RouteLocator routeLocator, GatewayProperties gatewayProperties) {
        this.routeLocator = routeLocator;
        this.gatewayProperties = gatewayProperties;
    }


    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resourceList = new ArrayList<>();
     //   resourceList.add(resource("project-user", "/admin" + API_URI, "default"));
        //Add the registered microservices swagger docs as additional swagger resources
        List<RouteDefinition> routes = gatewayProperties.getRoutes();
        routes.stream().forEach(route -> {
            /*过滤出所有的路径*/
            List<String> pathList = route.getPredicates().stream().filter(pre -> pre.getName().equals("Path")).map(pre -> pre.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")).collect(Collectors.toList());
            log.info("path => {}", pathList);
            /* 将路径中如 /admin/**  替换为 /admin/v3/api-docs  */
            pathList.stream().map(path->path.replace("/**",API_URI)).forEach(path->{
                resourceList.add(resource(route.getId(),path,"default"));
            });
        });

        return resourceList;
    }


    /**
     * @param name    显示的名字
     * @param oas3Url 文档格式：/manage/product/v3/api-docs(?group=...)
     */
    private SwaggerResource resource(String name, String oas3Url, String swaggerGroup) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        //url: 要看其他微服务是否设置了group 设置了的话
        if (Docket.DEFAULT_GROUP_NAME.equals(swaggerGroup)) {
            swaggerResource.setUrl(oas3Url);
        } else {
            //其他微服务是否设置了group 需要加上"?group=" + 组名
            swaggerResource.setUrl(oas3Url + "?group=" + swaggerGroup);
        }
        System.out.println("name: " + name + " url: " + oas3Url);
        swaggerResource.setSwaggerVersion("3.0.3");
        return swaggerResource;
    }

}