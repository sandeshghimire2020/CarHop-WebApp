package com.dotoku.carhop.dto.mapper;

import com.dotoku.carhop.dto.HopSessionDto;
import com.dotoku.carhop.dto.SessionResponseDto;
import com.dotoku.carhop.entity.HopSession;
import com.dotoku.carhop.entity.HopUser;
import com.dotoku.carhop.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;


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
}
