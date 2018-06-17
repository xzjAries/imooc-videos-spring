package com.imooc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imooc.pojo.User;
import com.imooc.pojo.vo.UserVO;
import com.imooc.service.UserService;
import com.imooc.utils.IMoocJSONResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "用户相关业务接口", tags = "用户相关业务接口controller")
@RequestMapping("/user")
public class UserController extends BasicController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "用户上传头像", notes = "用户上传头像接口")
	@ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String", paramType = "query")
	@PostMapping("/uploadFace")
	public IMoocJSONResult uploadFace(String userId, @RequestParam("file") MultipartFile[] files) throws Exception {
        if(StringUtils.isBlank(userId)) {
        	return IMoocJSONResult.errorMap("用户id不能为空");
        }
		
		// 文件保存的命名空间
		String fileSpace = "C:/imooc_videos_dev";
		// 保存到数据库中的相对路径
		String uploadPathDB = "/" + userId + "/face";
		FileOutputStream fileOutputStream = null;
		InputStream inputStream = null;
		try {
			if (files != null && files.length > 0) {
				String filename = files[0].getOriginalFilename();
				if (StringUtils.isNoneBlank(filename)) {
			       //文件上传的最终路径
					String finalFacePath = fileSpace+uploadPathDB+"/"+filename;
					//设置数据库的保存路径
					uploadPathDB=("/imooc_videos_dev"+uploadPathDB+"/"+filename);
					
					File outFile = new File(finalFacePath);
					if(outFile.getParentFile() !=null || !outFile.getParentFile().isDirectory()) {
						//创建父文件夹
						outFile.getParentFile().mkdirs();
					}
					
					fileOutputStream = new FileOutputStream(outFile);
					inputStream = files[0].getInputStream();
					IOUtils.copy(inputStream, fileOutputStream);
				}else {
					return IMoocJSONResult.errorMap("上传出错...");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return IMoocJSONResult.errorMap("上传出错...");
		}finally {
			if(fileOutputStream!=null) {
				fileOutputStream.flush();
				fileOutputStream.close();
			}
		}

		User user = new User();
		user.setId(userId);
		user.setFaceImage(uploadPathDB);
		userService.updateUserInfo(user);
		return IMoocJSONResult.ok(uploadPathDB);

	}
	
	@ApiOperation(value = "查询用户信息", notes = "查询用户信息接口")
	@ApiImplicitParam(name = "userId", value = "用户id",
	                   required = true, dataType = "String", paramType = "query")
	@PostMapping("/query")
	public IMoocJSONResult query(String userId) throws Exception {
        if(StringUtils.isBlank(userId)) {
        	return IMoocJSONResult.errorMap("用户id不能为空");
        }
		User userInfo = userService.queryUserInfo(userId);
		UserVO userVO= new UserVO();
		BeanUtils.copyProperties(userInfo, userVO);
		
		return IMoocJSONResult.ok(userVO);

	}

}
