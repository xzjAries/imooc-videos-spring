package com.imooc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.pojo.Videos;
import com.imooc.pojo.vo.VideosVO;
import com.imooc.utils.MyMapper;

public interface VideosMapperCustom extends MyMapper<Videos> {
	
	public List<VideosVO> queryAllVideos(@Param("videoDesc") String videoDesc);
    
	/**
	 * 对视频喜欢的数量进行累加
	 * @param videoId
	 */
	public void addVideoLikeCount(@Param("videoId") String videoId);
	/**
	 * 对视频喜欢的数量进行累减
	 * @param videoId
	 */
	public void reduceVideoLikeCount(@Param("videoId") String videoId);

}