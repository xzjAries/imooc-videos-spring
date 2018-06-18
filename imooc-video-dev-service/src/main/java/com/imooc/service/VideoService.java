package com.imooc.service;

import java.util.List;

import com.imooc.pojo.Videos;
import com.imooc.utils.PagedResult;


public interface VideoService {
 
	/**
	 * 保存视频
	 */
	public String saveVideo(Videos video);
	
	/**
	 * 修改视频的封面
	 */
	public void updateVideo(String videoId,String coverPath);
	
	/**
	 * 分页查询视频列表
	 */
	public PagedResult getAllVides(Videos video, Integer isSaveRecord,Integer page,Integer pageSize);
	
	/**
	 * 热搜词
	 */
    public List<String> getHotwords();
}
