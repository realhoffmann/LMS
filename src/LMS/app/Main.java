package LMS.app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import LMS.entities.*;
import LMS.provided.*;
import LMS.util.*;

/**
 * Logistics management system demo application
 */
public class Main {
	private static List<Delivery> deliveries;
	static {
		// ------- items
		LinkedList<Item> items = new LinkedList<>();
		items.add(new Item("Dell H2412", 16, 4.5F, 45099));
		items.add(new Item("Lenovo ThinkPad", 11, 2.5F, 180000));
		items.add(new Item("Iphone 6", 8, 0.34f, 70000));
		items.add(new Item("S9", 32, 0.26F, 90000));

		// ------- drivers
		List<Driver> drivers = new LinkedList<>();
		drivers.add(new Driver("Franz", "555-123"));
		drivers.add(new Driver("Hans", "555-456"));

		// ------- vehicles
		List<Vehicle> vehicles = new LinkedList<>();
		vehicles.add(new Vehicle("W123", 100000, drivers.get(0)));
		vehicles.add(new Vehicle("A456", 1200, drivers.get(0)));
		vehicles.add(new Vehicle("GF-007", 450, drivers.get(1)));
		vehicles.add(new Vehicle("B-10 54", 8500, drivers.get(1)));

		// ------- deliveries
		deliveries = new LinkedList<>();

		// ---
		Delivery d = new InterationalDelivery(120120210L, "Europe Storage", "Austria Storage");
		d.addGoods(items);
		d.assignCarrier(vehicles.get(0));
		d.collect(new DateTime(2018, 7, 1, 15, 15));
		deliveries.add(d);

		// ---
		d = new RegionalDelivery(120133210L, "Austria Storage", "Vienna Storage");
		deliveries.add(d);

	}


	public static void main(String[] args) {

		print(deliveries);
		
	}
	public static <T> java.util.List<T> filter(java.util.List<T> list, Matcher<? super T> m){
		List<T> filtered = new LinkedList<>();
		for(T t : list){
			if(m.matches(t)){
				filtered.add(t);
			}
		}
		return filtered;
	}
	public static int export(java.util.Collection<Delivery> deliveries, String filename){
		int n = 0;
		try (BufferedWriter w = new BufferedWriter(new FileWriter(filename))){
			for(Delivery d : deliveries){
				w.write(d.toString());
				w.newLine();
				n++;
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		return n;
	}

	

	public static void print(Collection<Delivery> deliveries) {
		System.out.println("\n--- Deliveries");
		for (Delivery d : deliveries)
			System.out.println(d);

	}

}
