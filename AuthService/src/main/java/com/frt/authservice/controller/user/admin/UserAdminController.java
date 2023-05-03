package com.frt.authservice.controller.user.admin;

import com.frt.authservice.model.user.FrtUserDto;
import com.frt.authservice.model.user.UpdateFrtUserResponse;
import com.frt.authservice.service.user.FrtUserService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/admin")
public class UserAdminController {

    private final FrtUserService frtUserService;

    @GetMapping
    public ResponseEntity<FrtUserDto> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(frtUserService.getUserByEmail(email));
    }

    @GetMapping
    public ResponseEntity<List<FrtUserDto>> getUsersByStatus(@RequestParam boolean status) {
        return ResponseEntity.ok(frtUserService.getUsersByStatus(status));
    }

    @GetMapping
    public ResponseEntity<List<FrtUserDto>> getAllUsers() {
        return ResponseEntity.ok(frtUserService.getAllUsers());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateFrtUserResponse> updateUserRole(@PathVariable @NotNull @Min(1) Long id,
                                                                @RequestParam @NotEmpty(message = "No update Role provided") String role) {
        return ResponseEntity.ok(frtUserService.updateUserRole(id, role));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateFrtUserResponse> activateUser(@PathVariable @NotNull @Min(1) Long id) {
        return ResponseEntity.ok(frtUserService.activateUser(id));
    }

}
