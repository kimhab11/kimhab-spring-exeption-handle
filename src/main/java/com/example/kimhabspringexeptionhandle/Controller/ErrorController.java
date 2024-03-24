package com.example.kimhabspringexeptionhandle.Controller;

import com.example.kimhabspringexeptionhandle.Exeption.CustomResourceNotFoundException;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("error")
public class ErrorController {
    private boolean error = true;
    Integer integer = null;

    @GetMapping("")
    public void notFound() {
        String user = null;
        if (user == null) throw new CustomResourceNotFoundException("NOT FOUND");
        // if (user == null) throw new CustomResourceNotFoundException();
    }

    @PostMapping
    void runtime() {
        if (error) {
            throw new RuntimeException();
        }
    }

    @PatchMapping
    void runtime2() {
        int a = integer;
    }
}
