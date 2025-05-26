package com.nhan.houserentalmanagement.controller;

import com.nhan.houserentalmanagement.model.House;
import com.nhan.houserentalmanagement.service.HouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.nhan.houserentalmanagement.service.UserService;
import com.nhan.houserentalmanagement.model.User;

import java.util.List;

@RestController
@RequestMapping("/api/houses")
public class HouseController {

    private final HouseService houseService;
    private final UserService userService;

    public HouseController(HouseService houseService, UserService userService) {
        this.houseService = houseService;
        this.userService = userService;
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
        // Lấy landlordId từ JSON body
        Long landlordId = house.getLandlord().getId();

        // Fetch User từ DB
        User landlord = userService.findById(landlordId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user với id=" + landlordId));

        // Set lại landlord cho house trước khi save
        house.setLandlord(landlord);

        // Lưu House
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
