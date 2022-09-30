package com.skilldistillery.treattracker.entities;

import java.util.Comparator;

public class SortByRate implements Comparator<Store> {

	@Override
	public int compare(Store store1, Store store2) {
		double avg1 = 0, avg2 = 0;
		double sum1 = 0, sum2 = 0;
		int count1 = 0, count2 = 0;
		for (StoreComment com : store1.getComments()) {
			sum1 = com.getRating();
			count1 ++;
		}
		avg1 = sum1/count1;
		for (StoreComment com : store2.getComments()) {
			sum2 = com.getRating();
			count2 ++;
		}
		avg2 = sum2/count2;
		
		return (int) (avg1 - avg2);
	}

}
