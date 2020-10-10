package com.ExtremelyOkayLending.auth;


import java.util.concurrent.atomic.AtomicLong;


import com.ExtremelyOkayLending.auth.models.User;
import org.springframework.web.bind.annotation.*;


import javax.ws.rs.core.Response;

@RestController
public class LoginController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @PostMapping("/login")
    public Response login(@RequestBody User user) {
        //User user = new User((Long)payload.get("user_id"),(String)payload.get("user_name"),(String)payload.get("pass"));


        return Response.status(Response.Status.OK).entity(user.toString()).build();
    }
}