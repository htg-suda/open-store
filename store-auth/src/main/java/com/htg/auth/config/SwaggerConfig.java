package com.htg.auth.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spi.service.contexts.SecurityContextBuilder;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        //schema
        List<GrantType> grantTypes = new ArrayList<>();
        //密码模式
        String passwordTokenUrl = "http://localhost:18010/oauth/token";
        ResourceOwnerPasswordCredentialsGrant resourceOwnerPasswordCredentialsGrant = new ResourceOwnerPasswordCredentialsGrant(passwordTokenUrl);
        grantTypes.add(resourceOwnerPasswordCredentialsGrant);
        OAuth oAuth = new OAuthBuilder().name("oauth2").grantTypes(grantTypes).build();
        //context
        //scope方位
        List<AuthorizationScope> scopes = new ArrayList<>();
        scopes.add(new AuthorizationScope("read", "read  resources"));
        scopes.add(new AuthorizationScope("write", "write resources"));
        scopes.add(new AuthorizationScope("reads", "read all resources"));
        scopes.add(new AuthorizationScope("writes", "write all resources"));
        scopes.add(new AuthorizationScope("all", " all resources"));

        SecurityReference securityReference = new SecurityReference("oauth2", scopes.toArray(new AuthorizationScope[]{}));

        ArrayList<SecurityReference> references = Lists.newArrayList(securityReference);

        SecurityContextBuilder securityContextBuilder = new SecurityContextBuilder();

        SecurityContext securityContext = securityContextBuilder.securityReferences(references).operationSelector(opt -> selector(opt)).build();
        //schemas
        List<SecurityScheme> securitySchemes = Lists.newArrayList(oAuth);
        //securyContext
        List<SecurityContext> securityContexts = Lists.newArrayList(securityContext);

        return new Docket(DocumentationType.OAS_30)
                .select()
              //  .apis(RequestHandlerSelectors.basePackage("com.htg"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContexts)
                .securitySchemes(securitySchemes)
                .globalRequestParameters(globalRequestParameters())
                .apiInfo(apiInfo());
    }

    boolean selector(OperationContext operationContext) {
        String url = operationContext.requestMappingPattern();
        //这里可以写URL过滤规则
        return true;
    }


    private List<RequestParameter> globalRequestParameters() {
        RequestParameterBuilder parameterBuilder = new RequestParameterBuilder().in(ParameterType.HEADER).name("Token").required(false).query(param -> param.model(model -> model.scalarModel(ScalarType.STRING)));
        return Collections.singletonList(parameterBuilder.build());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("gateway服务API")
                .description("gateway接口文档")
                .termsOfServiceUrl("http://127.0.0.1:8100/")
                .contact(new Contact("htg", "wwww.xxx.com", "1250068829@qq.com"))
                .version("1.0.0")
                .build();
    }
}