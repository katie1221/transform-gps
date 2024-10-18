package com.example.demo.enity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author qzz
 * @date 2024/10/18
 */
@Data
public class Position {

    /**
     * 地理经度
     */
    private BigDecimal longitude;

    /**
     * 地理纬度
     */
    private BigDecimal latitude;
}
