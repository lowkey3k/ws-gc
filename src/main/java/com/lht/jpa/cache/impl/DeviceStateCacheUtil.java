package com.lht.jpa.cache.impl;


import com.kedacom.uav.admin.cache.GuavaAbstractLoadingCache;
import com.kedacom.uav.admin.entity.DeviceState;
import com.kedacom.uav.admin.service.DeviceStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 本地缓存：areaId -> Area
 * @author LiHaitao
 *
 */
@Component
public class DeviceStateCacheUtil extends GuavaAbstractLoadingCache<String, DeviceState>  {
	@Autowired
	private DeviceStateService deviceStateService;
	
	private DeviceStateCacheUtil(){
		setMaximumSize(3000); //最大缓存条数
	}

	/**
	 * 从数据库中获取数据
	 */
	@Override
	protected DeviceState fetchData(String key) {
		logger.debug("测试：正在从es中获取DeviceState", key);
		return deviceStateService.get(Long.parseLong(key));
	}


}
