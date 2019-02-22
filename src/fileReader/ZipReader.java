package fileReader;

import java.io.File;
import java.util.*;
import java.util.zip.*;


public class ZipReader {
	public static void main(String[] argv) {
		try {
			ZipFile zipFile = new ZipFile("c:/dev/STRIDE_SLF - DG.twbx");
			System.out.println("Success");
			zipFile.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
