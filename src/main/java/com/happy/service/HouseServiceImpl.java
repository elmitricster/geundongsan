package com.happy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happy.dao.HouseDao;
import com.happy.vo.Dongcode;
import com.happy.vo.Guguncode;
import com.happy.vo.HouseDeal;
import com.happy.vo.HouseInfo;
import com.happy.vo.SidoCode;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	HouseDao dao;
	

	@Override
	public ArrayList<HouseDeal> getHouseDealsDong(Dongcode dongcode) {
		return dao.getHouseDealsDong(dongcode);
	}

	@Override
	public ArrayList<HouseDeal> getHouseDeals(String name) {
		return dao.getHouseDeals(name);
	}

	@Override
	public ArrayList<HouseInfo> getHouseInfoDong(Dongcode dongcode) {
		return dao.getHouseInfoDong(dongcode);
	}

	@Override
	public ArrayList<HouseInfo> getHouseInfo(String name) {
		return dao.getHouseInfo(name);
	}

	@Override
	public List<SidoCode> getCity() {
		return dao.getCity();
	}

	@Override
	public List<Guguncode> getGugun(String citycode) {
		return dao.getGugun(citycode);
	}

	@Override
	public List<Dongcode> getDong(String gugun) {
		return dao.getDong(gugun);
	}

	@Override
	public Dongcode selectOne(String dong) {
		return dao.selectOne(dong);
	}

}
