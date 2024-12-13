package com.dotoku.carhop.dto.mapper;

import com.dotoku.carhop.dto.HopRequestDto;
import com.dotoku.carhop.dto.HopSessionDto;
import com.dotoku.carhop.dto.UserSessionResponseDto;
import com.dotoku.carhop.entity.HopRequest;
import com.dotoku.carhop.entity.HopSession;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class SessionMapper {

    public HopSession mapDtoToEntity(HopSessionDto hopSessionDto){

        HopSession hopSession = new HopSession();

        hopSession.setAvailableSeats(hopSessionDto.getAvailableSeats());
        hopSession.setOriginAddress(hopSessionDto.getOriginAddress());
        hopSession.setOriginCity(hopSessionDto.getOriginCity());
        hopSession.setOriginZip(hopSessionDto.getOriginZip());
        hopSession.setDestinationAddress(hopSessionDto.getDestinationAddress());
        hopSession.setDestinationCity(hopSessionDto.getDestinationCity());
        hopSession.setDestinationZip(hopSessionDto.getDestinationZip());
        hopSession.setReturnBack(hopSessionDto.getReturnBack());
        hopSession.setReturnTime(hopSessionDto.getReturnTime());

        return hopSession;

    }

    public HopSessionDto mapEntityToDto(HopSession hopSession){

        HopSessionDto hopSessionDto = new HopSessionDto();
        hopSessionDto.setId(hopSession.getId());
        hopSessionDto.setAvailableSeats(hopSession.getAvailableSeats());
        hopSessionDto.setOriginAddress(hopSession.getOriginAddress());
        hopSessionDto.setOriginCity(hopSession.getOriginCity());
        hopSessionDto.setOriginZip(hopSession.getOriginZip());
        hopSessionDto.setDestinationAddress(hopSession.getDestinationAddress());
        hopSessionDto.setDestinationCity(hopSession.getDestinationCity());
        hopSessionDto.setDestinationZip(hopSession.getDestinationZip());
        hopSessionDto.setReturnBack(hopSession.getReturnBack());
        hopSessionDto.setReturnTime(hopSession.getReturnTime());
        hopSessionDto.setUserId(hopSession.getHopUser().getId());

        return hopSessionDto;

    }

    public UserSessionResponseDto mapEntityToResponseDto(HopSession hopSession){

        UserSessionResponseDto userSessionResponseDto = new UserSessionResponseDto();
        userSessionResponseDto.setId(hopSession.getId());
        userSessionResponseDto.setAvailableSeats(hopSession.getAvailableSeats());
        userSessionResponseDto.setOriginAddress(hopSession.getOriginAddress());
        userSessionResponseDto.setOriginCity(hopSession.getOriginCity());
        userSessionResponseDto.setOriginZip(hopSession.getOriginZip());
        userSessionResponseDto.setDestinationAddress(hopSession.getDestinationAddress());
        userSessionResponseDto.setDestinationCity(hopSession.getDestinationCity());
        userSessionResponseDto.setDestinationZip(hopSession.getDestinationZip());
        userSessionResponseDto.setReturnBack(hopSession.getReturnBack());
        userSessionResponseDto.setReturnTime(hopSession.getReturnTime());
        userSessionResponseDto.setUserId(hopSession.getHopUser().getId());

        List<HopRequestDto> hopRequestDtos = hopSession.getRideRequests()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        userSessionResponseDto.setHopRequest(hopRequestDtos);

        return userSessionResponseDto;
    }

    private HopRequestDto convertToDto(HopRequest hopRequest) {
        HopRequestDto dto = new HopRequestDto();
        dto.setId(hopRequest.getId());
        dto.setHopUserId(hopRequest.getHopUser() != null ? hopRequest.getHopUser().getId() : null);
        dto.setHopSessionId(hopRequest.getHopSession() != null ? hopRequest.getHopSession().getId() : null);
        dto.setStatus(hopRequest.getStatus());
        dto.setRequestTime(hopRequest.getRequestTime());
        return dto;
    }
}
