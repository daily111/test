package com.example.test.config;

import com.example.test.tool.Constant;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @Authof: Huangchenyang
 * @Date: Create in 16:32 2018/8/8
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String OSName = System.getProperty("os.name");
        String profilesPath = OSName.toLowerCase().startsWith("win") ? Constant.WINDOWS_PROFILES_PATH
                : Constant.LINUX_PROFILES_PATH;
        if (OSName.toLowerCase().startsWith("win")){
            registry.addResourceHandler("/image/**").addResourceLocations("file:C:/super_meeting/profiles/");
        }else {
            registry.addResourceHandler("/image/**").addResourceLocations("file:/root/usr/dj/imgs/");
        }



    }
}
