package com.example.coffee.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CoffeeV1Dao {

    /* 전체리스트 조회 */
    List<Map<String, String>> doCoffeeList();

    /* 전체리스트 조회 - 함수 오버로딩 */
    List<Map<String, String>> doCoffeeList(String strStart_date, String strEnd_date, String strName, String strKind);

    /* 등록 */
    int doInsert(String name, String kind, String price);


    /* 1 Row 삭제 */
    int doDelete(String strCoffee_id);

    /* 1 Row 가져오기 */
    Map<String, String> doListOne(String strCoffee_id);

    /* 수정하기 Post */
    int doUpdate(String strCoffee_id, String strName, String strKind, String strPrice);

}