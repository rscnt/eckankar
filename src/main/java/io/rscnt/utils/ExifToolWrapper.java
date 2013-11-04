package io.rscnt.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExifToolWrapper {

	private String exifTool = "/usr/bin/exiftool";

	public ExifToolWrapper() {
	}

	public ExifToolWrapper(String exifToolDir) {
		this.exifTool = exifToolDir;
	}

	public ExifSongInfo getSongInfo(String songDir)
			throws InterruptedException, IOException {
		ProcessBuilder processBuilder = null;

		try {
			processBuilder = new ProcessBuilder(exifTool, songDir);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Process process = processBuilder.start();

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(
				process.getInputStream()));

		BufferedReader stdError = new BufferedReader(new InputStreamReader(
				process.getErrorStream()));

		String line = "";
		ExifSongInfo exifInfo = new ExifSongInfo();

		while ((line = stdInput.readLine()) != null) {

			int pos = line.indexOf(":");
			String tagName = line.substring(0, pos).trim();
			String tagValue = line.substring(pos + 1).trim();
			if (pos != -1) {
				exifInfo.setTag(tagName, tagValue);
			}
			;
		}

		while ((line = stdError.readLine()) != null) {
		}

		process.waitFor();

		// ---- UNSAFE GRAB PICTURE - NO ONE IS GIVING ME A SHIT FOR THIS

		String baseDir = System.getenv("RSCNT_DATA_MEDIA") != null ? System
				.getenv("RSCNT_DATA_MEDIA") + "covers/"
				: "/home/_r/media/covers/";

		try {
			String coverDir = baseDir
					+ Utils.stringClearSpaces(exifInfo.getAlbum()) + ".jpg";
			ProcessBuilder imageProcessBuilder = null;

			try {
				imageProcessBuilder = new ProcessBuilder("bash", "-c", exifTool
						+ " -Picture -b " + songDir + " > " + coverDir);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Process exifProc = imageProcessBuilder.start();

			// any error message?
			StreamGobbler errorGobbler = new StreamGobbler(
					exifProc.getErrorStream(), "ERR");

			// any output?
			StreamGobbler outputGobbler = new StreamGobbler(
					exifProc.getInputStream(), "OUT");

			// kick them off
			errorGobbler.start();
			outputGobbler.start();
			exifProc.waitFor();
			System.out.println("Exit Value: " + exifProc.exitValue());
			exifInfo.setTag("Picture",
					Utils.stringClearSpaces(exifInfo.getAlbum()) + ".jpg");

			// --- END OF STUPID UNSAFE GRAB PICTURE - 'CAUSE NO ONE IS GIVING
			// ME A SHIT FOR THIS
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return exifInfo;
	}
}