package com.nhan.houserentalmanagement.service;

import com.nhan.houserentalmanagement.model.House;
import com.nhan.houserentalmanagement.repository.HouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;

    public HouseServiceImpl(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @Override
    public House save(House house) {
        return houseRepository.save(house);
    }

    @Override
    public Optional<House> findById(Long id) {
        return houseRepository.findById(id);
    }

    @Override
    public List<House> findAll() {
        return houseRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        houseRepository.deleteById(id);
    }
}
