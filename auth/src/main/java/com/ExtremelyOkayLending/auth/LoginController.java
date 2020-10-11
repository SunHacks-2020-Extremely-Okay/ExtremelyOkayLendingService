package com.ExtremelyOkayLending.auth;


import java.util.concurrent.atomic.AtomicLong;



import com.ExtremelyOkayLending.auth.models.User;
import com.ExtremelyOkayLending.auth.models.DynamoAdapter;
import com.ExtremelyOkayLending.auth.models.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

@RestController
public class LoginController {



    private final DynamoAdapter adapter = new DynamoAdapter();
    final Logger logger = LoggerFactory.getLogger(DynamoAdapter.class);

    @PostMapping(value="/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserResponse login(@RequestBody User user) {
        try {
            logger.debug(user.toString());
            User lookup = adapter.getUser(user.getUser_name());
            if( lookup.getPass().equals(user.getHashedPass())) {
                return new UserResponse(lookup.getUser_id(),200);
            } else {
                return new UserResponse(404);
            }
        } catch(Exception e) {
            return new UserResponse(400);
        }
    }

    @PostMapping(value="/user")
    public Integer addUser(@RequestBody User user) {
        boolean success = adapter.addUser(user.getUser_name(),user.getPass());

        if(success) {
            user = adapter.getUser(user.getUser_name());
            return 203;
        } else {
            return 400;
        }

    }
}