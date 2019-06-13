package postingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import postingservice.repository.*;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class GameFramePostingService extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GameFramePostingService.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(GameFramePostingService.class, args);
    }

    @Bean
    @Primary
    public IPostingDao postingDao(@Autowired IPostingJpaImpl postingJpaDao){
        PostingCache postingCache = new PostingCache();
        postingCache.init(postingJpaDao);
        return postingCache;
    }

    @Bean
    @Primary
    public ITagDao tagDao(@Autowired ITagJpaImpl tagJpaDao){
        TagCache tagCache = new TagCache();
        tagCache.init(tagJpaDao);
        return tagCache;
    }
}
