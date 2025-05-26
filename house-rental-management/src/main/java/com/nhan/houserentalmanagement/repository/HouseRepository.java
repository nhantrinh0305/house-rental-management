package com.nhan.houserentalmanagement.repository;

import com.nhan.houserentalmanagement.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {
    // bạn có thể thêm các method tìm kiếm tuỳ chỉnh ở đây nếu cần
}
