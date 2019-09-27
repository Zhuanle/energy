package com.xiyou.energy.service;

import java.util.List;

import com.xiyou.energy.pojo.Energy;
import com.xiyou.energy.util.Result;

public interface EnergyService {

	List<Energy> listEnergy();

	List<Energy> listEnergyByCid(int cid);

	Result addEnergy(Energy energy);

	Energy getEnergyById(int id);

	void updateEnergy(Energy energy);

	void deleteEnergy(int id);

}
