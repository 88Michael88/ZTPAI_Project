package pl.edu.pk.ztpai_project.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import pl.edu.pk.ztpai_project.dto.API.ApiResponse;
import pl.edu.pk.ztpai_project.security.principals.*;
import pl.edu.pk.ztpai_project.service.API_Service;

import java.util.UUID;

@RestController
@RequestMapping("/api/getAPIkey")
public class APIController {
    private final API_Service service;

    public APIController(API_Service service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ApiResponse getAPIkey(
        @AuthenticationPrincipal UserPrincipal user) {

        String apiKey = service.generateApiKey(user.getId());

        return new ApiResponse(apiKey);
    }
}
