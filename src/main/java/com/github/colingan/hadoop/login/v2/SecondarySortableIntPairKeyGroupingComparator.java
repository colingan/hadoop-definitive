package com.github.colingan.hadoop.login.v2;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import com.github.colingan.hadoop.utils.IntPair;

public class SecondarySortableIntPairKeyGroupingComparator extends
		WritableComparator {

	public SecondarySortableIntPairKeyGroupingComparator() {
		super(IntPair.class, true);
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		IntPair k1 = (IntPair) a;
		IntPair k2 = (IntPair) b;

		return k1.getSecond() - k2.getSecond();
	}
}
