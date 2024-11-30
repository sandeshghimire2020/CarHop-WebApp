package com.dotoku.carhop.service;

import com.dotoku.carhop.dto.HopUserDto;
import com.dotoku.carhop.dto.mapper.UserMapper;
import com.dotoku.carhop.entity.HopUser;
import com.dotoku.carhop.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;



    @Transactional
    public ResponseEntity<HopUserDto> addUser(HopUserDto userDto){

        HopUser hopUser = userMapper.mapDtoToEntity(userDto);
        userRepository.save(hopUser);
        userDto.setId(hopUser.getId());
        return ResponseEntity.ok(userDto);
    }

    public ResponseEntity<HopUserDto> getUser(Long userId){

        HopUser hopUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));
        HopUserDto userDto = userMapper.mapEntityToDto(hopUser);
        return ResponseEntity.ok(userDto);
    }

    public ResponseEntity<HopUserDto> updateUser(HopUserDto userDto, Long userId){

        HopUser hopUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        userMapper.mapDtoToEntity(userDto, hopUser);
        userRepository.save(hopUser);
        userDto.setId(hopUser.getId());
        return ResponseEntity.ok(userDto);
    }

    public ResponseEntity<String> deleteUser(Long userId) {

        HopUser hopUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        userRepository.delete(hopUser);
        return ResponseEntity.ok("User deleted successfully.");
    }

    public List<HopUserDto> getAllUsers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<HopUser> users = userRepository.findAll(pageable);
        List<HopUserDto> usersDto = users.stream()
                .map(userMapper::mapEntityToDto)
                .collect(Collectors.toList());
        return usersDto;
    }

}
