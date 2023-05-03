package com.frt.authservice.service.user;

import com.frt.authservice.model.user.FrtUserDto;
import com.frt.authservice.model.user.UpdateFrtUserRequest;
import com.frt.authservice.model.user.UpdateFrtUserResponse;

import java.util.List;

public interface FrtUserService {

    FrtUserDto getUser(Long frtUserId);

    FrtUserDto getUserByEmail(String email);

    List<FrtUserDto> getAllUsers();

    List<FrtUserDto> getUsersByStatus(boolean isActive);

    UpdateFrtUserResponse updateUserRole(Long frtUserId, String role);

    UpdateFrtUserResponse deactivateUser(Long frtUserId);

    UpdateFrtUserResponse activateUser(Long frtUserId);

    UpdateFrtUserResponse updateUser(Long frtUserId, UpdateFrtUserRequest updateFrtuserRequest);


}
