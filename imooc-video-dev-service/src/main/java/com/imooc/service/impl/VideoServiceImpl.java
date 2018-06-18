package com.imooc.service.impl;

import java.util.List;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mapper.SearchRecordsMapper;
import com.imooc.mapper.VideosMapper;
import com.imooc.mapper.VideosMapperCustom;
import com.imooc.pojo.SearchRecords;
import com.imooc.pojo.Videos;
import com.imooc.pojo.vo.VideosVO;
import com.imooc.service.VideoService;
import com.imooc.utils.PagedResult;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideosMapper videosMapper;
    
    @Autowired
    private VideosMapperCustom videosMapperCustom;
    
    @Autowired
    private SearchRecordsMapper searchRecordsMapper;
    
    @Autowired
    private Sid sid;

    @Transactional(propagation=Propagation.REQUIRED)
	@Override
	public String saveVideo(Videos video) {
    	String id = sid.nextShort();
    	video.setId(id);
    	videosMapper.insertSelective(video);
        return id;
    }

    @Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void updateVideo(String videoId, String coverPath) {
		
    	Videos video = new Videos();
    	video.setId(videoId);
    	video.setCoverPath(coverPath);
    	videosMapper.updateByPrimaryKeySelective(video);
	}
    
    @Transactional(propagation=Propagation.REQUIRED)
	@Override
	public PagedResult getAllVides(Videos video, Integer isSaveRecord,Integer page, Integer pageSize) {
		String desc = video.getVideoDesc();
		//保存热搜词语
		if(isSaveRecord !=null && isSaveRecord==1) {
			SearchRecords record = new SearchRecords();
			String recordId = sid.nextShort();
			record.setId(recordId);
			record.setContent(desc);
			searchRecordsMapper.insert(record);
		}
		
		PageHelper.startPage(page,pageSize);
        List<VideosVO> list=videosMapperCustom.queryAllVideos(desc);
        
        PageInfo<VideosVO> pageList =new PageInfo<>(list);
        
        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(list);
        pagedResult.setRecords(pageList.getTotal());
        
		return pagedResult;
	}

    @Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public List<String> getHotwords() {
		return searchRecordsMapper.getHotwords();
	}

}
