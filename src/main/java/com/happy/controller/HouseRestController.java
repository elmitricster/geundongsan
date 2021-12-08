package com.happy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happy.service.HouseService;
import com.happy.vo.Dongcode;
import com.happy.vo.Guguncode;
import com.happy.vo.HouseDeal;
import com.happy.vo.HouseInfo;
import com.happy.vo.Member;
import com.happy.vo.SidoCode;

@RestController
@RequestMapping("/city")
@CrossOrigin("*")
public class HouseRestController {

	@Autowired
	HouseService service;
	
	@GetMapping(value = "/city")
	public ResponseEntity<List<SidoCode>> getCity() {
		List<SidoCode> list=service.getCity();
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<SidoCode>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/gugun/{citycode}")
	public ResponseEntity<List<Guguncode>> getGugun(@PathVariable String citycode){
		citycode=new String(citycode.substring(0, 2));
		List<Guguncode> list=service.getGugun(citycode);
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<Guguncode>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping(value = "/dong/{gugun}")
	public ResponseEntity<List<Dongcode>> getDong(@PathVariable String gugun){
		List<Dongcode> list=service.getDong(gugun);
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<Dongcode>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	@PostMapping(value="/searchByDong")
	public ResponseEntity<List<HouseDeal>> searchByDong(@RequestBody Map<String, String> map){
		Dongcode dongcode=service.selectOne(map.get("dong"));
		List<HouseDeal> pricelist=service.getHouseDealsDong(dongcode);
		if(pricelist != null && !pricelist.isEmpty()) {
			return new ResponseEntity<List<HouseDeal>>(pricelist, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	@PostMapping(value="/searchByName")
	public ResponseEntity<List<HouseDeal>> searchByName(@RequestBody Map<String, String> map){
		List<HouseDeal> pricelist=service.getHouseDeals(map.get("name"));
		if(pricelist != null && !pricelist.isEmpty()) {
			return new ResponseEntity<List<HouseDeal>>(pricelist, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping(value="/infoByDong")
	public ResponseEntity<List<HouseInfo>> infoByDong(@RequestBody Map<String, String> map){
		Dongcode dongcode=service.selectOne(map.get("dong"));
		List<HouseInfo> pricelist=service.getHouseInfoDong(dongcode);
		
		///
		
		
		///
		
		if(pricelist != null && !pricelist.isEmpty()) {
			return new ResponseEntity<List<HouseInfo>>(pricelist, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping(value="/infoByName")
	public ResponseEntity<List<HouseInfo>> infoByName(@RequestBody Map<String, String> map){
		List<HouseInfo> pricelist=service.getHouseInfo(map.get("name"));
		if(pricelist != null && !pricelist.isEmpty()) {
			return new ResponseEntity<List<HouseInfo>>(pricelist, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

}
