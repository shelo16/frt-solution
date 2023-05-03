package com.frt.authservice.controller.user;

import com.frt.authservice.model.user.FrtUserDto;
import com.frt.authservice.model.user.UpdateFrtUserRequest;
import com.frt.authservice.model.user.UpdateFrtUserResponse;
import com.frt.authservice.service.user.FrtUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final FrtUserService frtUserService;

    @GetMapping("/{id}")
    public ResponseEntity<FrtUserDto> getUser(@PathVariable @NotNull @Min(1) Long id) {
        return ResponseEntity.ok(frtUserService.getUser(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateFrtUserResponse> deactivateUser(@PathVariable @NotNull @Min(1) Long id) {
        return ResponseEntity.ok(frtUserService.deactivateUser(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateFrtUserResponse> updateUser(@PathVariable @NotNull @Min(1) Long id,
                                                            @RequestBody @Valid UpdateFrtUserRequest updateFrtUserRequest) {
        return ResponseEntity.ok(frtUserService.updateUser(id, updateFrtUserRequest));
    }

}
