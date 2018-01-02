package com.guyuesoft.structure.hash;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import sun.dc.DuctusRenderingEngine;

/**
 * 测试结果(大概有一倍的性能差距比起官方的)： <br/>
 * Time cost for Official Map :<br/>
 * Time Cost for insert : PT15.0261963S<br/>
 * Time Cost for search exsist record : (10000000)PT1.783523S<br/>
 * Time Cost for not exsist record : (0)PT1.9395122S<br/>
 * Time Cost for all : PT16.9747071S<br/>
 * 
 * Time cost for Custom Map :<br/>
 * Time Cost for insert : PT29.8488473S<br/>
 * Time Cost for search exsist record : (10000000)PT3.3517392S<br/>
 * Time Cost for not exsist record : (0)PT3.5354669S<br/>
 * Time Cost for all : PT33.3853169S<br/>
 * 
 * @author xiaodonz
 *
 */
public class HashTableTest {

	public static void main(String[] args) {
		List<Employee> testList = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 10000000; i++) {
			Employee employee = new Employee();
			employee.setName("e" + random.nextDouble());
			testList.add(employee);
		}
		System.out.println("Time cost for Official Map :");
		testOfficialMap(testList);
		System.out.println("Time cost for Custom Map :");
		testCustomMap(testList);
	}

	public static void testOfficialMap(List<Employee> testList) {
		Employee temp = new Employee();
		temp.setName("Can't find");
		Instant totalBefore = Instant.now();
		Instant before = Instant.now();
		Set<Employee> officialMap = new HashSet<>();
		for (Employee employee : testList) {
			officialMap.add(employee);
		}
		Instant after = Instant.now();
		System.out.println("Time Cost for insert : " + Duration.between(before, after));
		before = Instant.now();
		int total = 0;
		for (Employee employee : testList) {
			if (officialMap.contains(employee)) {
				total++;
			}
		}

		after = Instant.now();
		System.out.println("Time Cost for search exsist record : (" + total + ")" + Duration.between(before, after));
		total = 0;
		for (Employee employee : testList) {
			if (officialMap.contains(temp)) {
				total++;
			}
		}
		after = Instant.now();
		System.out.println("Time Cost for not exsist record : (" + total + ")" + Duration.between(before, after));
		Instant totalAfter = Instant.now();
		System.out.println("Time Cost for all : " + Duration.between(totalBefore, totalAfter));

	}

	public static void testCustomMap(List<Employee> testList) {
		Employee temp = new Employee();
		temp.setName("Can't find");
		Instant totalBefore = Instant.now();
		Instant before = Instant.now();
		SeparateChainingHashTable<Employee> customMap = new SeparateChainingHashTable<>();
		for (Employee employee : testList) {
			customMap.insert(employee);
		}
		Instant after = Instant.now();
		System.out.println("Time Cost for insert : " + Duration.between(before, after));
		before = Instant.now();
		int total = 0;
		for (Employee employee : testList) {
			if (customMap.contains(employee)) {
				total++;
			}
		}
		after = Instant.now();
		System.out.println("Time Cost for search exsist record : (" + total + ")" + Duration.between(before, after));
		total = 0;
		for (Employee employee : testList) {
			if (customMap.contains(temp)) {
				total++;
			}
		}
		after = Instant.now();
		System.out.println("Time Cost for not exsist record : (" + total + ")" + Duration.between(before, after));
		Instant totalAfter = Instant.now();
		System.out.println("Time Cost for all : " + Duration.between(totalBefore, totalAfter));

	}
}
