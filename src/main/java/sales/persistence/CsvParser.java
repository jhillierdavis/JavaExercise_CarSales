package sales.persistence;

import sales.model.SalesDatum;

import java.io.*;
import java.nio.file.Files;
import java.util.stream.Stream;

/**
 * Simple CSV (comma Separated Value) file parser for anticipated Car Sales input data format.
 */

public class CsvParser	{
	SalesDatum[] data;
	
	public CsvParser(final File file)	{
		if (null == file)	{
			throw new IllegalArgumentException("NULL file!");
		}

		try {
			this.data = this.parse(file);
		} catch (IOException e) {
			throw new RuntimeException("Failed to parse file: " + file.getAbsolutePath(), e);
		}
	}
	
	public SalesDatum[] toData()	{
		return this.data;
	}

	private SalesDatum[] parse(final File file) throws IOException {
		Stream<String> lines = Files.lines(file.toPath());
		Object[] objs = lines.toArray();

		SalesDatum[] data = new SalesDatum[objs.length -1];
		for (int i = 1; i < objs.length; i++) {
			String str = (String)objs[i];
			String[] values = str.split(",");
			if (values.length != 2)	{
				throw new IllegalArgumentException("Invalid file content at line " + i + "!");
			}
			data[i - 1] = new SalesDatum(values[0], values[1]);
		}
		return data;
	}
}