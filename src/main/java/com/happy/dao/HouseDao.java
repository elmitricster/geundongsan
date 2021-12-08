package com.happy.dao;

import java.util.ArrayList;
import java.util.List;

import com.happy.vo.Dongcode;
import com.happy.vo.Guguncode;
import com.happy.vo.HouseDeal;
import com.happy.vo.HouseInfo;
import com.happy.vo.SidoCode;


public interface HouseDao {
	//실거래가 정보 HouseDeal 
	//시도, 군구, 동 검색 
	public ArrayList<HouseDeal> getHouseDealsDong(Dongcode dongcode);
	// 아파트 이름으로 검색 
	public ArrayList<HouseDeal> getHouseDeals(String name);
	
	//지도에 표시할 HouseInfo
	//시도, 군구, 동 검색
	public ArrayList<HouseInfo> getHouseInfoDong(Dongcode dongcode);
	// 이름으로 검색
	public ArrayList<HouseInfo> getHouseInfo(String name);
	public List<SidoCode> getCity();
	public List<Guguncode> getGugun(String citycode);
	public List<Dongcode> getDong(String gugun);
	public Dongcode selectOne(String dong);
	
}
