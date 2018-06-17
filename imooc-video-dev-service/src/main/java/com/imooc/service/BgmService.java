package com.imooc.service;

import java.util.List;

import com.imooc.pojo.Bgm;


public interface BgmService {
	/**
	 * 查询背景音乐列表
	 * 
	 * @param username
	 * @return
	 */
	public List<Bgm> queryBgmList();
 
	/**
	 * 根据id查询bgm信息
	 * @param bgmId
	 * @return
	 */
	public Bgm qureyBgmById(String bgmId);
}
