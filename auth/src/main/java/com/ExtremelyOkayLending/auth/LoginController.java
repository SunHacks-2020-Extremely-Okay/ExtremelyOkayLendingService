package com.ExtremelyOkayLending.auth;


import java.util.concurrent.atomic.AtomicLong;



import com.ExtremelyOkayLending.auth.models.User;
import com.ExtremelyOkayLending.auth.models.DynamoAdapter;
import org.springframework.web.bind.annotation.*;


import javax.ws.rs.core.Response;

@RestController
public class LoginController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final DynamoAdapter adapter = new DynamoAdapter();

    @PostMapping("/login")
    public Response login(@RequestBody User user) {
        try {
            User lookup = adapter.getUser(user.getUser_name());
            return Response.status(Response.Status.OK).entity(lookup.toString()).build();
        } catch(Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}