package com.imooc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imooc.utils.IMoocJSONResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "视频上传业务接口", tags = "视频上传接口controller")
@RequestMapping("/video")
public class VideoController {
	@ApiOperation(value = "上传视频", notes = "上传视频的接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "bgmId", value = "背景音乐id", required = false, dataType = "String", paramType = "form"),
			@ApiImplicitParam(name = "videoSeconds", value = "视频的长度", required = true, dataType = "double", paramType = "form"),
			@ApiImplicitParam(name = "videowidth", value = "视频的宽度", required = true, dataType = "int", paramType = "form"),
			@ApiImplicitParam(name = "videoHight", value = "视频的高度", required = true, dataType = "int", paramType = "form"),
			@ApiImplicitParam(name = "desc", value = "视频的描述", required = false, dataType = "String", paramType = "form"),

	})

	@PostMapping(value = "/upload",headers="content-type=multipart/form-data")
	public IMoocJSONResult upload(String userId, String bgmId, double videoSeconds, int videoWidth, int videoHeight,
			String desc,
			@ApiParam(value="短视频",required=true)
			MultipartFile file) throws Exception {
		if (StringUtils.isBlank(userId)) {
			return IMoocJSONResult.errorMap("用户id不能为空");
		}

		// 文件保存的命名空间
		String fileSpace = "C:/imooc_videos_dev";
		// 保存到数据库中的相对路径
		String uploadPathDB = "/" + userId + "/video";
		FileOutputStream fileOutputStream = null;
		InputStream inputStream = null;
		try {
			if (file != null ) {
				String filename = file.getOriginalFilename();
				if (StringUtils.isNoneBlank(filename)) {
					// 文件上传的最终路径
					String finalVideoPath = fileSpace + uploadPathDB + "/" + filename;
					// 设置数据库的保存路径
					uploadPathDB = ("/imooc_videos_dev" + uploadPathDB + "/" + filename);

					File outFile = new File(finalVideoPath);
					if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
						// 创建父文件夹
						outFile.getParentFile().mkdirs();
					}

					fileOutputStream = new FileOutputStream(outFile);
					inputStream = file.getInputStream();
					IOUtils.copy(inputStream, fileOutputStream);
				} else {
					return IMoocJSONResult.errorMap("上传出错...");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return IMoocJSONResult.errorMap("上传出错...");
		} finally {
			if (fileOutputStream != null) {
				fileOutputStream.flush();
				fileOutputStream.close();
			}
		}

		return IMoocJSONResult.ok();

	}

}
