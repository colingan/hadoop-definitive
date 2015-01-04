package com.github.colingan.hadoop.login.v1;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.github.colingan.hadoop.utils.IntPair;
import com.github.colingan.hadoop.utils.JobBuilder;

public class UserLoginTimesPerWeekDriver extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		Job job = JobBuilder.parseInputAndOutput(this, getConf(), args);
		if (job == null) {
			return -1;
		}

		job.setMapperClass(UserLoginTimesPerWeekMapper.class);
		job.setReducerClass(UserLoginTimesPerWeekReducer.class);
		job.setOutputKeyClass(IntPair.class);
		job.setOutputValueClass(IntWritable.class);

		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new UserLoginTimesPerWeekDriver(), args);
		System.exit(exitCode);
	}

}
