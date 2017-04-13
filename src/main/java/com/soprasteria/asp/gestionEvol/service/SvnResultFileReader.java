package com.soprasteria.asp.gestionEvol.service;

import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;

public interface SvnResultFileReader {
	public <T> ArrayList<T> getData(String name, TypeReference<ArrayList<T>> type);
}
