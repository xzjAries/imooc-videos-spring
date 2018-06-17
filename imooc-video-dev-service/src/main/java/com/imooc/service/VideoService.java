package com.imooc.service;

import com.imooc.pojo.Videos;


public interface VideoService {
 
	/**
	 * 保存视频
	 */
	public String saveVideo(Videos video);
	
	/**
	 * 修改视频的封面
	 */
	public void updateVideo(String videoId,String coverPath);
	
}
