package com.example.coffee.service;

import com.example.coffee.dao.CoffeeV1Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CoffeeV1Service {

    @Autowired
    CoffeeV1Dao v1Dao;

    /* 전체리스트 조회 */
    public List<Map<String, String>> doCoffeeList() {
        List<Map<String, String>> list = v1Dao.doCoffeeList();
        return list;
    }

    /* 전체리스트 - 오버로딩 함수 사용 */
    public List<Map<String, String>> doCoffeeList(String strStart_date, String strEnd_date, String strName, String strKind) {
        List<Map<String, String>> list = v1Dao.doCoffeeList(strStart_date,strEnd_date,strName,strKind);
        return list;

    }

    /* 데이타 입력 */
    public int doInsert(String name, String kind, String price) {
        int intI = v1Dao.doInsert(name, kind, price);
        return intI;
    }


    /* 1 row 가져오기 */
    public Map<String, String> doListOne(String strCoffee_id) {
        Map<String, String> map = v1Dao.doListOne(strCoffee_id);
        return map;
    }

    /* 수정하기 Post */
    public int doUpdate(String strCoffee_id, String strName, String strKind, String strPrice) {
        int intI = v1Dao.doUpdate(strCoffee_id, strName, strKind, strPrice);
        return  intI;
    }

    /* 데이타 삭제 */
    public int doDelete(String strCoffee_id) {
        int intI = v1Dao.doDelete(strCoffee_id);

        return intI;
    }
}