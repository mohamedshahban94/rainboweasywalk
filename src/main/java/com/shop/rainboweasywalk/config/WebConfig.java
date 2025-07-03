package com.shop.rainboweasywalk.config;
import com.shop.rainboweasywalk.interceptor.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${frontend.url}")
    private String frontendUrl;

    @Autowired
    private AuthFilter authFilter;

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(authFilter)
                    .excludePathPatterns("/api/auth/login","/api/auth/logout","/api/auth/login-required");
        }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authFilter)
//                .excludePathPatterns("/**"); // Temporarily disable for all
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(frontendUrl)
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
