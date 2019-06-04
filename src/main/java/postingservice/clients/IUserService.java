package postingservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import postingservice.model.dto.response.FullUserResponse;

@FeignClient("Gameframe-user-service")
public interface IUserService {

    @RequestMapping(value = "/internal/getuser/{userId}")
    FullUserResponse getUserByUserId(@PathVariable long userId);

}
