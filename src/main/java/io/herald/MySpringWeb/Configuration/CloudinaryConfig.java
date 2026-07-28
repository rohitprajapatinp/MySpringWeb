package io.herald.MySpringWeb.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name","s1hw2bii",
                    "api_key","983492852228735",
                    "api_secret","bTMFs_1wdNhB5e4AAXBdMM2GQxg",
                    "secure",true
        ));
    }
}
