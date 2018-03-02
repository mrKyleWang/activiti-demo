package top.kylewang;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import top.kylewang.interceptor.LoginIntercepter;

/**
 * @author Kyle.Wang
 * 2018-03-02 9:45
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {


    /**
     * 配置拦截器
     * @param registry
     */
    @Override  
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截  
        registry.addInterceptor(new LoginIntercepter())
                .addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/loginvalidate");
        super.addInterceptors(registry);
    }  
  
}  