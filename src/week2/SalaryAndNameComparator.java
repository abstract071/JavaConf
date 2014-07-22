package week2;

import java.util.Comparator;

public class SalaryAndNameComparator implements Comparator<Worker> {
	 public int compare (Worker e1, Worker e2) {
       return e1.compareTo(e2);
   }
}