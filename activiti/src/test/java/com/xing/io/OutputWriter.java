package com.xing.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class OutputWriter {
	
	public static void main(String[] args) throws Exception {
		OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream(new File("")));
		InputStreamReader reader=new InputStreamReader(new FileInputStream(new File("")));
	}
}
