package com.xiyou.energy.service;

import java.util.List;

import com.xiyou.energy.pojo.Jiancha;

public interface CheckService {

	List<Jiancha> listCheck();

	void addCheck(Jiancha jiancha);

}
