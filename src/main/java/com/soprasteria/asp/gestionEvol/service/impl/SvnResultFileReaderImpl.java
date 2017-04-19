package com.soprasteria.asp.gestionEvol.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soprasteria.asp.gestionEvol.service.SvnResultFileReader;

@Component
public class SvnResultFileReaderImpl implements SvnResultFileReader {

	private static Logger LOGGER = LoggerFactory.getLogger(SvnResultFileReaderImpl.class);

	private BufferedReader openFileJson(String name) throws Exception {
		String path = new File(System.getProperty("user.dir")).getParent();
		File pathFile = new File(path, name);
		BufferedReader br = new BufferedReader(new FileReader(pathFile.getPath()));
		return br;
	}

	// @Override
	public <T> ArrayList<T> getData(String name, TypeReference<ArrayList<T>> type)
													
	{
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<T> listData = null;
		try {
			BufferedReader br = openFileJson(name);
			listData = mapper.readValue(br,  type);
			br.close();
			return listData;
		} catch (FileNotFoundException e) {
			LOGGER.error(e.toString());
		} catch (Exception e) {
			LOGGER.error(e.toString());
		}
		return listData;
	}

	@Override
	public ArrayList<String> getAllFileRepo() {
		String path = new File(System.getProperty("user.dir")).getParent();
		File pathFile = new File(path, "Repo");
		File[] listFileTmp = pathFile.listFiles();
		ArrayList<File> listFile = new ArrayList(Arrays.asList(listFileTmp));
		ArrayList<String> names = (ArrayList<String>) listFile.stream().map(File::getName).collect(Collectors.toList());

		return names;
	}
}
