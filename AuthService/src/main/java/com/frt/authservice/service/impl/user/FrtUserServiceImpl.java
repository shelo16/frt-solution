package com.frt.authservice.service.impl.user;

import com.frt.authservice.exception.model.customexception.GeneralException;
import com.frt.authservice.exception.util.FrtError;
import com.frt.authservice.model.user.FrtUserDto;
import com.frt.authservice.model.user.UpdateFrtUserRequest;
import com.frt.authservice.model.user.UpdateFrtUserResponse;
import com.frt.authservice.persistence.entity.FrtUser;
import com.frt.authservice.persistence.repository.FrtUserRepository;
import com.frt.authservice.persistence.util.Role;
import com.frt.authservice.service.user.FrtUserService;
import com.frt.authservice.service.util.FrtSuccess;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FrtUserServiceImpl implements FrtUserService {

    private final FrtUserRepository userRepository;

    @Override
    public FrtUserDto getUser(Long frtUserId) {

        return FrtUserDto
                .transformEntityToDto(getFrtUserById(frtUserId));
    }

    @Override
    public FrtUserDto getUserByEmail(String email) {

        FrtUser frtUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new GeneralException(FrtError.USER_NOT_FOUND));

        return FrtUserDto.transformEntityToDto(frtUser);
    }

    @Override
    public List<FrtUserDto> getAllUsers() {
        List<FrtUser> frtUserList = userRepository.findAll();

        return frtUserList.stream()
                .map(FrtUserDto::transformEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FrtUserDto> getUsersByStatus(boolean isActive) {

        List<FrtUser> frtUserList = userRepository.findByIsActive(isActive)
                .orElseThrow(() -> new GeneralException(FrtError.USER_NOT_FOUND));

        return frtUserList.stream()
                .map(FrtUserDto::transformEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UpdateFrtUserResponse updateUserRole(Long frtUserId, String role) {
        FrtUser frtUser = getFrtUserById(frtUserId);
        frtUser.setRole(Role.getRole(role));
        userRepository.save(frtUser);
        return UpdateFrtUserResponse.builder()
                .message(FrtSuccess.OK.getDescription())
                .frtUserId(frtUserId)
                .build();
    }

    @Override
    public UpdateFrtUserResponse deactivateUser(Long frtUserId) {
        return changeUserStatus(frtUserId, false);
    }

    @Override
    public UpdateFrtUserResponse activateUser(Long frtUserId) {
        return changeUserStatus(frtUserId, true);
    }

    @Override
    public UpdateFrtUserResponse updateUser(Long frtUserId, UpdateFrtUserRequest updateFrtuserRequest) {
        FrtUser frtUser = getFrtUserById(frtUserId);
        validateUserUpdate(updateFrtuserRequest, frtUser);
        BeanUtils.copyProperties(updateFrtuserRequest, frtUser);

        return UpdateFrtUserResponse.builder()
                .frtUserId(frtUserId)
                .message(FrtSuccess.OK.getDescription())
                .build();
    }


    private FrtUser getFrtUserById(Long frtUserId) {
        return userRepository
                .findById(frtUserId)
                .orElseThrow(() -> new GeneralException(FrtError.USER_NOT_FOUND));
    }

    private UpdateFrtUserResponse changeUserStatus(Long frtUserId, boolean isActive) {
        FrtUser frtUser = getFrtUserById(frtUserId);
        if (frtUser.getIsActive().equals(isActive)) {
            throw new GeneralException(FrtError.USER_NO_CHANGES_DETECTED);
        }
        return UpdateFrtUserResponse.builder()
                .frtUserId(frtUserId)
                .message(FrtSuccess.OK.getDescription())
                .build();
    }

    private void validateUserUpdate(UpdateFrtUserRequest updateFrtuserRequest, FrtUser frtUser) {
        if (!updateFrtuserRequest.getPassword().isEmpty()) {
            if (frtUser.getPassword().equals(updateFrtuserRequest.getPassword())) {
                throw new GeneralException(FrtError.SAME_PASSWORD_UPDATE);
            }
        }

        if (!updateFrtuserRequest.getEmail().isEmpty()) {
            if (frtUser.getEmail().equalsIgnoreCase(updateFrtuserRequest.getEmail())) {
                throw new GeneralException(FrtError.SAME_EMAIL_UPDATE);
            }
        }
    }
}
