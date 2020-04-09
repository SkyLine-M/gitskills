package com.ma.jpa.repository;

import com.ma.jpa.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, String>, JpaSpecificationExecutor<Holiday> {
    /**
     * 查询当前时间所在的假期
     * @param start
     * @param end
     * @return
     */
    Holiday findFirstByEndTimeGreaterThanEqualAndStartTimeLessThanEqualAndDeleteFlagFalse(Long start, Long end);
}
