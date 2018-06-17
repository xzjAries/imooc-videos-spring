package com.imooc.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.format.InputAccessor;

public class MergeVideoMp3 {
	private String ffmpegEXE;

	public MergeVideoMp3(String FFMpegTest) {
		super();
		this.ffmpegEXE = FFMpegTest;
	}

	public void convertor(String videoInputPath, String mp3InuputPath,
			double seconds, String videoOutPutPath) throws Exception {
		//ffmpeg.exe -i 1007.mp4 -i ceshimp3.mp3 -t 7 -y 1009.mp4
		//ffmpeg.exe -i bgm.mp3 -i 1007.mp4 -t 7 -y 1009.mp4
		//ffmpeg -i a.mp3 -i a.mp4 -t 7 -vcodec copy -acodec copy test.mp4
		
		List<String> command = new ArrayList<>();
		command.add(ffmpegEXE);
		
		command.add("-i");
		command.add(mp3InuputPath);
		
		command.add("-i");
		command.add(videoInputPath);
		
		command.add("-t");
		command.add(String.valueOf(seconds));
		
//		command.add("-vcodec");
//		command.add("copy");
//		command.add("-acodec");
//		command.add("copy");
		
		command.add("-y");
		command.add(videoOutPutPath);
		
//		for (String c : command) {
//			System.out.println(c);
//		}

		ProcessBuilder builder = new ProcessBuilder(command);
		Process process = builder.start();
		InputStream errorStream = process.getErrorStream();
		InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
		BufferedReader br = new BufferedReader(inputStreamReader);
		String line = "";
		while ((line = br.readLine()) != null) {
		}
		if(br != null) {
			br.close();
		}
		if(inputStreamReader != null) {
			inputStreamReader.close();
		}
		if(errorStream != null) {
			errorStream.close();
		}
	}

	public static void main(String[] args) {
		MergeVideoMp3 ffMpegTest = new MergeVideoMp3("C:\\ffmpeg\\bin\\ffmpeg.exe");
		try {
			ffMpegTest.convertor("C:\\苏州.mp4", "C:\\bgm.mp3",7.1,"C:\\苏州11.mp4");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
