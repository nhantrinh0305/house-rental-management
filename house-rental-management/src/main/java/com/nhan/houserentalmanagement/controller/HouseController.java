package com.nhan.houserentalmanagement.controller;

import com.nhan.houserentalmanagement.model.House;
import com.nhan.houserentalmanagement.service.HouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/houses")
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    // Lấy danh sách tất cả houses
    @GetMapping
    public ResponseEntity<List<House>> getAllHouses() {
        return ResponseEntity.ok(houseService.findAll());
    }

    // Lấy house theo ID
    @GetMapping("/{id}")
    public ResponseEntity<House> getHouseById(@PathVariable Long id) {
        return houseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Tạo mới house
    @PostMapping
    public ResponseEntity<House> createHouse(@RequestBody House house) {
        House saved = houseService.save(house);
        return ResponseEntity.ok(saved);
    }

    // Cập nhật house
    @PutMapping("/{id}")
    public ResponseEntity<House> updateHouse(@PathVariable Long id, @RequestBody House house) {
        return houseService.findById(id)
                .map(existing -> {
                    existing.setTitle(house.getTitle());
                    existing.setAddress(house.getAddress());
                    existing.setDescription(house.getDescription());
                    existing.setPrice(house.getPrice());
                    existing.setLandlord(house.getLandlord());
                    House updated = houseService.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Xóa house
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHouse(@PathVariable Long id) {
        houseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
