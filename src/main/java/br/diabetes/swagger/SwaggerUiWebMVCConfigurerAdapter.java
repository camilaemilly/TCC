package br.diabetes.swagger;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class SwaggerUiWebMVCConfigurerAdapter implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/v2/api-docs", "/v2/api-docs").setKeepQueryParams(true);
        registry.addRedirectViewController("/configuration/ui", "/configuration/ui");
        registry.addRedirectViewController("/configuration/security", "/configuration/security");
        registry.addRedirectViewController("/swagger-resources", "/swagger-resources");
        registry.addRedirectViewController("", "/swagger-ui.html");
        registry.addRedirectViewController("/", "/swagger-ui.html");
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/documentation/**").addResourceLocations("classpath:/META-INF/resources/");
    }

}
