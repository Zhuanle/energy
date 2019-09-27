package com.xiyou.energy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiyou.energy.pojo.Energy;
import com.xiyou.energy.service.EnergyService;
import com.xiyou.energy.util.Result;

@RestController
@RequestMapping("/energy")
@CrossOrigin(origins={"*","null"})
public class EnergyController {

	@Autowired
	private EnergyService energyService;
	
	//获得所有的能源信息
	@RequestMapping(value="/listEnergy",method=RequestMethod.GET)
	public Result listEnergy(@RequestParam(value="start",defaultValue="0") int start,@RequestParam(value="size",defaultValue="5") int size){
		PageHelper.startPage(start, size);
		List<Energy> list = energyService.listEnergy();
		PageInfo<Energy> page = new PageInfo<>(list);
		return Result.success(page);
	}
	
	//根据能源类型获得能源列表
	@PostMapping("/listEnergyByCid")
	public Result listEnergyByCid(@RequestParam(value="start",defaultValue="0") int start,@RequestParam(value="size",defaultValue="5")int size,@RequestParam int cid){
		PageHelper.startPage(start, size);
		List<Energy> list = energyService.listEnergyByCid(cid);
		PageInfo<Energy> page = new PageInfo<>(list);
		return Result.success(list);
	}
	
	//添加能源
	@PostMapping("/addEnergy")
	public Result addEnergy(@RequestBody Energy energy){
		energyService.addEnergy(energy);
		return Result.success();
	}
	
	//根据id获得能源信息
	@PostMapping("/getEnergyById")
	public Result getEnergyById(int id){
		Energy energy = energyService.getEnergyById(id);
		return Result.success(energy);
	}
	
	//修改能源信息
	@RequestMapping("/updateEnergy")
	public Result updateEnergy(@RequestBody Energy energy){
		energyService.updateEnergy(energy);
		return Result.success();
	}
	
	//删除能源
	@RequestMapping(value="/deleteEnergy",method=RequestMethod.GET)
	public Result deleteEnergy(@RequestParam int id){
		energyService.deleteEnergy(id);
		return Result.success();
	}
}
