package com.example.demo.service.impl;

import com.example.demo.enity.Position;
import com.example.demo.enity.PositionRequestJson;
import com.example.demo.service.PositionService;
import com.example.demo.util.CoordinateTransformUtil;
import com.example.demo.util.GpsUtil;
import org.locationtech.proj4j.ProjCoordinate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author qzz
 * @date 2024/10/18
 */
@Service
public class PositionServiceImpl implements PositionService {


    /**
     * 大地坐标 转 GCJ02
     *   步骤：大地坐标 转 84
     *   步骤2：84 转 GCJ02
     * @param requestJson
     * @return
     */
    @Override
    public Object getTransferGps(PositionRequestJson requestJson) {
        List<Position> positionList = new ArrayList<Position>();
        String[] dataArray = requestJson.getCoordList().replaceAll(",0,", "#")
                .split("#");
        for (String data : dataArray) {
            String[] locationArray = data.split(",");
            if (locationArray.length < 2) {
                break;
            }
            //大地坐标系
            BigDecimal log = new BigDecimal(locationArray[0]);
            BigDecimal lat = new BigDecimal(locationArray[1]);

            //CGCS2000 转 WGS84
            ProjCoordinate cgcs2000ToWgs84 = CoordinateTransformUtil.cgcs2000ToWgs84(log.doubleValue(), lat.doubleValue());

            double[] gcj02 = GpsUtil.gps84_To_Gcj02(cgcs2000ToWgs84.y, cgcs2000ToWgs84.x);

            Position position = new Position();
            position.setLongitude(BigDecimal.valueOf(gcj02[1]));
            position.setLatitude(BigDecimal.valueOf(gcj02[0]));
            positionList.add(position);
//            System.out.println(gcj02[1]);
        }
        //打印经纬度
        System.out.println("---------------------经度-------------------");
        positionList.forEach(position -> {
            System.out.println(position.getLongitude());
        });
        System.out.println("---------------------纬度-------------------");
        positionList.forEach(position -> {
            System.out.println(position.getLatitude());
        });
        return positionList;
    }
}
