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
    public Response login(@RequestBody User user) {
        try {
            logger.debug(user.toString());
            User lookup = adapter.getUser(user.getUser_name());
            if( lookup.getPass().equals(user.getHashedPass())) {
                return Response.ok(new UserResponse(lookup.getUser_id())).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch(Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PostMapping(value="/user")
    public Response addUser(@RequestBody User user) {
        boolean success = adapter.addUser(user.getUser_name(),user.getPass());

        if(success) {
            user = adapter.getUser(user.getUser_name());
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }
}