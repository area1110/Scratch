package model;

public abstract class Animal {
	
	private String name;
	private int size;
	private String color;
	
	public Animal() {
		
	}
	
	public Animal(String name, int size, String color) {
		this.name = name;
		this.size = size;
		this.color = color;
	}
	
	public void Eat() {
		
	}
	
}


