package postingservice.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import postingservice.model.dto.response.FullUserResponse;

import java.util.HashMap;

@ApplicationScope
@Component
public class UserServiceCache {

    @Autowired
    private IUserService userService;


    private HashMap<Long, FullUserResponse> userResponseHashMap;


    public FullUserResponse getUserByUserId(long userId) {

        if(this.userResponseHashMap == null)
            this.userResponseHashMap = new HashMap<>();

        if(this.userResponseHashMap.containsKey(userId)){
            return this.userResponseHashMap.get(userId);
        }

        FullUserResponse userResponse = this.userService.getUserByUserId(userId);
        this.userResponseHashMap.put(userId, userResponse);
        return userResponse;
    }
}
