package com.frt.order.service;

import com.frt.order.model.user.FrtUserDto;

import java.util.List;

public interface FrtUserDetailsService {

    FrtUserDto getFrtUserDto(Long id);

    List<FrtUserDto> getFrtUserDtoList(List<Long> idList);

}
