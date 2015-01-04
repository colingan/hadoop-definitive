package com.github.colingan.hadoop.temp;

import java.io.IOException;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.github.colingan.hadoop.utils.JobBuilder;

public class FileSplitTest extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		Job job = JobBuilder.parseInputAndOutput(this, getConf(), args);
		if (job == null) {
			return -1;
		}

		job.setMapperClass(FileLongIndexWriterMapper.class);
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(NullWritable.class);

		job.setNumReduceTasks(0);

		return job.waitForCompletion(true) ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new FileSplitTest(), args);
		System.exit(exitCode);
	}

}

class FileLongIndexWriterMapper extends
		Mapper<LongWritable, Text, NullWritable, NullWritable> {

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		System.out.printf("(%d,%s)\n", key.get(), value.toString());
	}

}
