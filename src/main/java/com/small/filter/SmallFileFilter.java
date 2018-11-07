package com.small.filter;

import java.io.File;
import java.io.FileFilter;

public class SmallFileFilter implements FileFilter {

	@Override
	public boolean accept(File pathname) {
		return pathname.getName().toLowerCase().contains(".xml");
	}

}
