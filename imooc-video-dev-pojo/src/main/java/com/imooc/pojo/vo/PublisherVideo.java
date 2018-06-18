package com.imooc.pojo.vo;

public class PublisherVideo {
	public UserVO publisher;
	public boolean userLikeVideo;
	public UserVO getPublisher() {
		return publisher;
	}
	public void setPublisher(UserVO publisher) {
		this.publisher = publisher;
	}
	public boolean isUserLikeVideo() {
		return userLikeVideo;
	}
	public void setUserLikeVideo(boolean userLikeVideo) {
		this.userLikeVideo = userLikeVideo;
	}
	
	
	
}