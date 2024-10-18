package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.locationtech.proj4j.*;


/**
 * 坐标转换工具类  CGCS2000\WGS84 互转
 * @author qzz
 * @date 2024/7/15
 */
@Slf4j
public class CoordinateTransformUtil {

    // 定义CGCS2000的坐标系  4490
    private final static String CGCS2000 = "EPSG:4490";
    // 定义WGS84的坐标系
    final static String WGS84 = "EPSG:4326";

    /**
     * CGCS2000 转 WGS84
     *
     * @param lng CGCS2000经度
     * @param lat CGCS2000纬度
     * @return
     */
    public static ProjCoordinate cgcs2000ToWgs84(double lng, double lat) {

        //创建坐标系工厂
        CRSFactory crsFactory = new CRSFactory();
        // 创建CGCS2000的坐标参考系统
        CoordinateReferenceSystem sourceCRS = crsFactory.createFromName(CGCS2000);
        // 创建WGS84的坐标参考系统
        CoordinateReferenceSystem targetCRS = crsFactory.createFromName(WGS84);

        // 定义坐标转换器
        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
        // 创建转换器
        CoordinateTransform transform = ctFactory.createTransform(sourceCRS, targetCRS);
        // 执行坐标转换
        ProjCoordinate srcCoord = new ProjCoordinate(lng, lat);
        ProjCoordinate targetCoord = new ProjCoordinate();
        transform.transform(srcCoord, targetCoord);
        // 4. 输出转换后的正常经纬度坐标
        return targetCoord;
    }

    /**
     * WGS84 转 CGCS2000
     *
     * @param lng WGS84经度
     * @param lat WGS84纬度
     * @return
     */
    public static ProjCoordinate wgs84ToCgcs2000(double lng, double lat) {
        CRSFactory crsFactory = new CRSFactory();
        // 定义源和目标投影
        CoordinateReferenceSystem sourceCRS = crsFactory.createFromName(WGS84);
        CoordinateReferenceSystem targetCRS = crsFactory.createFromName(CGCS2000);

        // 定义坐标转换器
        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
        // 创建转换器
        CoordinateTransform transform = ctFactory.createTransform(sourceCRS, targetCRS);
        // 执行坐标转换
        ProjCoordinate srcCoord = new ProjCoordinate(lng, lat);
        ProjCoordinate targetCoord = new ProjCoordinate();
        transform.transform(srcCoord, targetCoord);
        // 输出转换后的正常经纬度坐标
        return targetCoord;
    }

    public static void main(String[] args) {
        /**
         * CGCS2000
         */
        double CGCS2000Lon4 = 108.887314;
        double CGCS2000Lat4 = 34.230897;
        ProjCoordinate CGCS2000ToWgs84 = CoordinateTransformUtil.cgcs2000ToWgs84(CGCS2000Lon4, CGCS2000Lat4);
        log.info("中国2000 CGCS2000ToWgs84 : longitude={}, latitude={}", CGCS2000ToWgs84.x, CGCS2000ToWgs84.y);
    }

}


