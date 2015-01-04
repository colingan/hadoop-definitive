package com.github.colingan.hadoop.login.v2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

import com.github.colingan.hadoop.utils.IntPair;

public class SecondarySortableIntPairKeyPartitioner extends
		Partitioner<IntPair, IntWritable> {

	private static final HashPartitioner<IntWritable, IntWritable> HASH_PARTITIONER = new HashPartitioner<IntWritable, IntWritable>();

	@Override
	public int getPartition(IntPair key, IntWritable value, int numPartitions) {
		return HASH_PARTITIONER.getPartition(new IntWritable(key.getFirst()),
				value, numPartitions);
	}

}
