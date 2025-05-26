package com.nhan.houserentalmanagement.service;

import com.nhan.houserentalmanagement.model.House;
import java.util.List;
import java.util.Optional;

public interface HouseService {
    House save(House house);
    Optional<House> findById(Long id);
    List<House> findAll();
    void deleteById(Long id);
}
