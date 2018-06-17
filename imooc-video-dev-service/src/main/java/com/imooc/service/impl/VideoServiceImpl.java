package com.imooc.service.impl;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.mapper.VideosMapper;
import com.imooc.pojo.Videos;
import com.imooc.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideosMapper videosMapper;
    @Autowired
    private Sid sid;

    @Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void saveVideo(Videos video) {
    	String id = sid.nextShort();
    	video.setId(id);
    	videosMapper.insertSelective(video);
	}

}
