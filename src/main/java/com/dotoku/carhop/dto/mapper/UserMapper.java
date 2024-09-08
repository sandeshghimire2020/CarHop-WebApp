package com.dotoku.carhop.dto.mapper;

import com.dotoku.carhop.dto.HopUserDto;
import com.dotoku.carhop.dto.IdentificationDto;
import com.dotoku.carhop.dto.VehicleDto;
import com.dotoku.carhop.entity.HopUser;
import com.dotoku.carhop.entity.Identification;
import com.dotoku.carhop.entity.Review;
import com.dotoku.carhop.entity.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public HopUser mapDtoToEntity(HopUserDto userDto, HopUser hopUser){
        Identification identification = mapDtoToIdentification(userDto.getIdentification());
        Vehicle vehicle = mapDtoToVehicle(userDto.getVehicle());

        hopUser.setFirstName(userDto.getFirstName());
        hopUser.setMiddleName(userDto.getMiddleName());
        hopUser.setLastName(userDto.getLastName());
        hopUser.setDateOfBirth(userDto.getDateOfBirth());
        hopUser.setGender(userDto.getGender());
        hopUser.setVerified(userDto.isVerified());
        hopUser.setIdentification(identification);
        hopUser.setVehicle(vehicle);
        return hopUser;
    }

    public HopUser mapDtoToEntity(HopUserDto userDto){

        HopUser hopUser = new HopUser();
        Identification identification = mapDtoToIdentification(userDto.getIdentification());

        if(userDto.getVehicle() != null) {
            Vehicle vehicle = mapDtoToVehicle(userDto.getVehicle());
            hopUser.setVehicle(vehicle);
        }

                hopUser.setFirstName(userDto.getFirstName());
                hopUser.setMiddleName(userDto.getMiddleName());
                hopUser.setLastName(userDto.getLastName());
                hopUser.setDateOfBirth(userDto.getDateOfBirth());
                hopUser.setGender(userDto.getGender());
                hopUser.setVerified(userDto.isVerified());
                hopUser.setIdentification(identification);
        return hopUser;
    }

    public Identification mapDtoToIdentification(IdentificationDto identificationDto){
        Identification identification = new Identification();
        identification.setCountry(identificationDto.getCountry());
        identification.setExpiration(identificationDto.getExpiration());
        identification.setNumber(identificationDto.getNumber());
        identification.setType(identificationDto.getType());

        return identification;
    }

    public Vehicle mapDtoToVehicle(VehicleDto vehicleDto){
        Vehicle vehicle = new Vehicle();
        vehicle.setType(vehicleDto.getType());
        vehicle.setMake(vehicleDto.getMake());
        vehicle.setModel(vehicleDto.getModel());
        vehicle.setYear(vehicleDto.getYear());
        vehicle.setPlateNumber(vehicleDto.getPlateNumber());
        return vehicle;
    }

    public HopUserDto mapEntityToDto(HopUser hopUser){

        HopUserDto hopUserDto = new HopUserDto();
        IdentificationDto identificationDto = mapEntityToIdentification(hopUser.getIdentification());

        if(hopUser.getVehicle() != null) {
            VehicleDto vehicleDto = mapEntityToVehicle(hopUser.getVehicle());
            hopUserDto.setVehicle(vehicleDto);
        }

        List<Long> reviewIds = hopUser.getReviews()
                .stream()
                .map(Review::getId)
                .collect(Collectors.toList());

        hopUserDto.setId(hopUser.getId());
        hopUserDto.setFirstName(hopUser.getFirstName());
        hopUserDto.setMiddleName(hopUser.getMiddleName());
        hopUserDto.setLastName(hopUser.getLastName());
        hopUserDto.setDateOfBirth(hopUser.getDateOfBirth());
        hopUserDto.setGender(hopUser.getGender());
        hopUserDto.setVerified(hopUser.isVerified());
        hopUserDto.setReviewsId(reviewIds);
        hopUserDto.setIdentification(identificationDto);

        return hopUserDto;
    }

    public IdentificationDto mapEntityToIdentification(Identification identification){
        IdentificationDto identificationDto = new IdentificationDto();

        identificationDto.setCountry(identification.getCountry());
        identificationDto.setExpiration(identification.getExpiration());
        identificationDto.setNumber(identification.getNumber());
        identificationDto.setType(identification.getType());

        return identificationDto;
    }

    public VehicleDto mapEntityToVehicle(Vehicle vehicle){
        VehicleDto vehicleDto = new VehicleDto();

        vehicleDto.setType(vehicle.getType());
        vehicleDto.setMake(vehicle.getMake());
        vehicleDto.setModel(vehicle.getModel());
        vehicleDto.setYear(vehicle.getYear());
        vehicleDto.setPlateNumber(vehicle.getPlateNumber());

        return vehicleDto;
    }

}
