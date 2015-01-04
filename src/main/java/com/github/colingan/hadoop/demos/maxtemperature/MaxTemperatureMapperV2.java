package com.github.colingan.hadoop.demos.maxtemperature;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxTemperatureMapperV2 extends
		Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	protected void map(LongWritable key, Text value,
			org.apache.hadoop.mapreduce.Mapper.Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String year = line.substring(15, 19);
		String temp = line.substring(87, 92);
		if (!missing(temp)) {
			int airTemperature = Integer.parseInt(temp);
			context.write(new Text(year), new IntWritable(airTemperature));
		}
	}

	private boolean missing(String raw) {
		return "+9999".equals(raw);
	}

}
