package com.github.colingan.hadoop.login.v1;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.github.colingan.hadoop.login.Constants;

public class UserAverageLoginTimesPerWeekMapper extends
		Mapper<LongWritable, Text, IntWritable, IntWritable> {

	@Override
	protected void map(LongWritable key, Text value,
			org.apache.hadoop.mapreduce.Mapper.Context context)
			throws IOException, InterruptedException {
		// parser line
		String[] tokens = value.toString().split(Constants.SPLITTER);
		if (tokens != null && tokens.length == 3) {
			context.write(new IntWritable(Integer.parseInt(tokens[0])),
					new IntWritable(Integer.parseInt(tokens[2])));
		}
	}

}
