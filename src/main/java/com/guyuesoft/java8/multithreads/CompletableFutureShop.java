package com.guyuesoft.java8.multithreads;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CompletableFutureShop {

	Random random =new  Random();
	public double getPrice(String product) {
		return calculatePrice(product);
	}
	public void delay() {
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private double calculatePrice(String product) {
		delay();
		return random.nextDouble()* product.charAt(0)+product.charAt(1);
	}
	
	public Future<Double> getPriceAsync(String product){
		CompletableFuture<Double> futurePrice= new CompletableFuture();
		new Thread(()->{
			double price = calculatePrice(product);
			futurePrice.complete(price);
		}) .start();
		return futurePrice;
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletableFutureShop shop = new CompletableFutureShop();
		System.out.println("Start:"+ LocalDateTime.now());
		Future<Double> futureResult= shop.getPriceAsync("BBB");
		System.out.println("Finish getPriceAsync :"+ LocalDateTime.now());
		Double result= shop.getPrice("AAA");
		System.out.println("Finish getPrice :"+ LocalDateTime.now());
		System.out.println("Obtain result: "+ LocalDateTime.now()+":"+ result);
		System.out.println("Obtain futureResult: "+ LocalDateTime.now()+":"+futureResult.get());
	}
	
}
