package com.example.demo.enity;

import lombok.Data;

/**
 * @author qzz
 * @date 2024/10/18
 */
@Data
public class PositionRequestJson {

    private Long projectId;

    private String fenceCode;

    private String coordList;
}
