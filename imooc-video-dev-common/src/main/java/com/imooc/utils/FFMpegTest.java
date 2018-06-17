package com.imooc.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.format.InputAccessor;

public class FFMpegTest {
	private String ffmpegEXE;

	public FFMpegTest(String FFMpegTest) {
		super();
		this.ffmpegEXE = FFMpegTest;
	}

	public void convertor(String videoInputPath, String videoOutPutPath) throws Exception {
		// $ ffmpeg -i input.mp4 output.avi
		List<String> command = new ArrayList<>();
		command.add(ffmpegEXE);

		command.add("-i");
		command.add(videoInputPath);
		command.add(videoOutPutPath);
		for (String c : command) {
			System.out.println(c);
		}

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
		FFMpegTest ffMpegTest = new FFMpegTest("C:\\ffmpeg\\bin\\ffmpeg.exe");
		try {
			ffMpegTest.convertor("C:\\苏州.mp4", "C:\\苏州11.mp4");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
