package com.github.colingan.hadoop.demos.maxtemperature;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

public class MaxTemperatureDriverTest {

	@Test
	public void localTest() throws Exception {
		Configuration conf = new Configuration();
		conf.set("df.default.name", "file:///");
		// conf.set("mapred.job.tracer", "local");
		conf.set("mapreduce.framework.name", "local");

		Path input = new Path("/Volumes/FUN/hadoop/ncdc/micro");
		Path output = new Path("/Volumes/FUN/hadoop/ncdc/output");

		FileSystem fs = FileSystem.getLocal(conf);
		fs.delete(output, true); // delete old output

		MaxTemperatureDriver driver = new MaxTemperatureDriver();
		driver.setConf(conf);

		int exitCode = driver.run(new String[] { input.toString(),
				output.toString() });
		assertThat(exitCode, is(0));

	}
}
