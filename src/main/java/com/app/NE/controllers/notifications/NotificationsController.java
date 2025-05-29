package com.app.NE.controllers.notifications;

import com.app.NE.dto.responses.ApiResponse;
import com.app.NE.services.notifications.INotificationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/notifications")
public class NotificationsController {
    private final INotificationsService notificationsService;

    @GetMapping("/all/by-meter/{id}")
    public ResponseEntity<ApiResponse> getAllByMeterId(@PathVariable int id){
        ApiResponse apiResponse = notificationsService.getNotificationsByMeter(id);
        return ResponseEntity.ok(apiResponse);
    }
}
