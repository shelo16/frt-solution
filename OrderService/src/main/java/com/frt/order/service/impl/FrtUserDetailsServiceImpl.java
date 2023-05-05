package com.frt.order.service.impl;

import com.frt.order.exception.model.customexception.GeneralException;
import com.frt.order.exception.util.FrtError;
import com.frt.order.model.user.FrtUserDto;
import com.frt.order.persistence.repository.FrtUserDetailsRepository;
import com.frt.order.service.FrtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FrtUserDetailsServiceImpl implements FrtUserDetailsService {

    private final FrtUserDetailsRepository userRepository;

    @Override
    public FrtUserDto getFrtUserDto(Long userId) {
        return userRepository.getUserById(userId)
                .orElseThrow(() -> new GeneralException(FrtError.USER_NOT_FOUND));
    }

    @Override
    public List<FrtUserDto> getFrtUserDtoList(List<Long> userIdList) {
        return userRepository.getUsersById(userIdList)
                .orElseThrow(() -> new GeneralException(FrtError.USER_NOT_FOUND));
    }
}
