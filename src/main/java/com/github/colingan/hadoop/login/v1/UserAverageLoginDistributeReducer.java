package com.github.colingan.hadoop.login.v1;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class UserAverageLoginDistributeReducer extends
		Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {

	@Override
	protected void reduce(IntWritable key, Iterable<IntWritable> values,
			Context context) throws IOException, InterruptedException {
		int sum = 0;
		for (IntWritable value : values) {
			sum += 1;
		}

		context.write(key, new IntWritable(sum));
	}
}
