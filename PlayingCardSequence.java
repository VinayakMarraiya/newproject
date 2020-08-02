package com.vinayak.playcard;

public class PlayingCardSequence {

	public static void main(String[] args) {
		String[] suit = {"S","H","D","C"};
		String[] rank = {"#2","#3","#4","#5","#6","#7","#8","#9","#10","#J","#Q","#K","#A"};	
		String[] deck = new String[52];
		for(int i = 0; i < deck.length ; i++) {
			deck[i] =  suit[i / 13] + rank[i % 13];
			System.out.println(deck[i]);
		}
	}

}
