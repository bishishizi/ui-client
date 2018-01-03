package com.demo.ui.client.config;

import com.google.common.base.Predicate;
import org.springframework.boot.actuate.endpoint.mvc.AbstractEndpointMvcAdapter;
import org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置类,非生产环境启用
 * @author wwchang
 * @date 2017/12/14 11:51
 **/
@Configuration
@EnableSwagger2
@Profile("!pro")
public class SwaggerConfig {

	@Bean
	public Docket swaggerUi(){
		//排除一些不必要的类
		Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
			@Override
			public boolean apply(RequestHandler input) {
				Class<?> declaringClass = input.declaringClass();
				//排除统一异常处理
				if (declaringClass == BasicErrorController.class){
					return false;
				}
				//放行
				if (declaringClass.isAnnotationPresent(RestController.class)){
					return true;
				}
				//mvc断点类
				if (AbstractEndpointMvcAdapter.class.isAssignableFrom(declaringClass) || EndpointMvcAdapter.class.isAssignableFrom(declaringClass)){
					return false;
				}
				return false;
			}
		};
		ApiInfo apiInfo = new ApiInfoBuilder().title("swagger").version("1.0").build();
		Docket docket = new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo).useDefaultResponseMessages(false).select().apis(predicate).build();
		return docket;
	}
}
