package ui;

import java.util.Date;

public class Test {
	public static void main(String[] args) {
		Date date1 = new Date(2022, 11, 2);
		Date date2 = new Date(2022, 10, 31);
		System.out.println(date1.after(date2));
	}
}
