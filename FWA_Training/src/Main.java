import java.util.ArrayList;
import java.util.List;

import model.Animal;
import model.Cat;

public class Main {
	List<Integer> list = new ArrayList<Integer>();
	
	public static void main(String[] args) {
//		Animal a = new Cat();
		
		String str1 = "Hello";
		
		String str2 = "Hello";
		
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str1 == str2);
		
		str2 = str2 + "c";
		System.out.println(str2);
		System.out.println(str1);
		
		System.out.println(str2 == str1);
	}
}
