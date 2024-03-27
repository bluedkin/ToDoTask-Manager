package toDoApp.springsecurity.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/test/v1")
    public ResponseEntity<Object> getTest() {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(true);
    }
}
