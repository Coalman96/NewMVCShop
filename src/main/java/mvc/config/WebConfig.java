package mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//===================== 추가된 Class  ======================//
// Interceptor 등록하는 WebMvcCongigurer 구현 Bean
//=======================================================//
@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	///Field


	public WebConfig() {
		System.out.println("==> WebConfig default Constructor call.............");
	}


}
