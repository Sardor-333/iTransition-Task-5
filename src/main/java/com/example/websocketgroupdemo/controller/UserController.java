package com.example.websocketgroupdemo.controller;

import com.example.websocketgroupdemo.payload.req.SearchUserDto;
import com.example.websocketgroupdemo.payload.resp.ApiResponse;
import com.example.websocketgroupdemo.projection.UserProjection;
import com.example.websocketgroupdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse> getMe(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getMe(userDetails);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getMyContacts() {
        return userService.getUsers();
    }

    @PostMapping("/search")
    public ResponseEntity<List<UserProjection>> searchUsers(@Valid @RequestBody SearchUserDto searchUserDto) {
        return userService.searchUsers(searchUserDto);
    }
}
