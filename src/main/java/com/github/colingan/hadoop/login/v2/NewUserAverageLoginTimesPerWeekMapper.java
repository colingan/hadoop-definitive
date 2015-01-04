package com.github.colingan.hadoop.login.v2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.github.colingan.hadoop.login.LoginRecordParser;
import com.github.colingan.hadoop.utils.IntPair;

public class NewUserAverageLoginTimesPerWeekMapper extends
		Mapper<LongWritable, Text, IntWritable, IntPair> {

	@Override
	protected void map(LongWritable key, Text value,
			org.apache.hadoop.mapreduce.Mapper.Context context)
			throws IOException, InterruptedException {
		LoginRecordParser parser = new LoginRecordParser();
		try {
			parser.parse(value);

			context.write(new IntWritable(parser.getUserid()), new IntPair(
					parser.getWeek(), 1));
		} catch (Exception e) {
			// drop invalid record
		}
	}

}
