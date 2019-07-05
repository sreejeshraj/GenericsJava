package com.sreejesh;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class MainClassToTestGenerics {

	
	public static void main(String[] args) {

		System.out.println("***** main() START *****");

		List<String> allBooksList = populateAllBooksList();
		System.out.println("***** allBooksList *****");
		printCollection(allBooksList);

		List<String> scienceBooksList = getAllScienceBooks1(allBooksList);
		System.out.println("***** scienceBooksList1 *****");
		printCollection(scienceBooksList);

		scienceBooksList = getAllScienceBooks2(allBooksList);
		System.out.println("***** scienceBooksList2 *****");
		printCollection(scienceBooksList);
		
		scienceBooksList = getAllScienceBooks3(allBooksList);
		System.out.println("***** scienceBooksList3 *****");
		printCollection(scienceBooksList);
		
		scienceBooksList = getAllScienceBooks4(allBooksList);
		System.out.println("***** scienceBooksList4 *****");
		printCollection(scienceBooksList);
		
		scienceBooksList = getAllScienceBooks5(allBooksList);
		System.out.println("***** scienceBooksList5 *****");
		printCollection(scienceBooksList);

		System.out.println("***** main() END *****");

	}

	private static List<String> populateAllBooksList() {
		List<String> allBooksList = new ArrayList<>();
		allBooksList.add("Science1");
		allBooksList.add("Science2");
		allBooksList.add("History1");
		allBooksList.add("Fiction1");
		allBooksList.add("Science3");
		allBooksList.add("Travel1");
		allBooksList.add("Law1");
		allBooksList.add("Science4");

		return allBooksList;
	}

	private static void printCollection(Collection<?> collection) {
		System.out.println(Arrays.toString(collection.toArray()));
	}

	private static List<String> getAllScienceBooks1(List<String> allBooksList) {
		List<String> scienceBooksList = new ArrayList<>();
		for (String bookName : allBooksList) {
			boolean isScienceBook = bookName.toLowerCase().contains("science");
			if (isScienceBook) {
				scienceBooksList.add(bookName);
			}
		}
		return scienceBooksList;
	}

	private static List<String> getAllScienceBooks2(List<String> allBooksList) {

		List<String> scienceBooksList = allBooksList.stream().filter(new Predicate<String>() {

			@Override
			public boolean test(String bookName) {
				boolean isScienceBook = bookName.toLowerCase().contains("science");
				return isScienceBook;
			}

		}).collect(Collectors.toList());

		return scienceBooksList;
	}

	private static List<String> getAllScienceBooks3(List<String> allBooksList) {

		List<String> scienceBooksList = allBooksList.stream().filter(new ScienceBookPredicate()).collect(Collectors.toList());

		return scienceBooksList;
	}
	
	private static final class ScienceBookPredicate implements Predicate<String> {
		@Override
		public boolean test(String bookName) {
			boolean isScienceBook = bookName.toLowerCase().contains("science");
			return isScienceBook;
		}
	}
	
	private static List<String> getAllScienceBooks4(List<String> allBooksList) {

		List<String> scienceBooksList = allBooksList.stream().filter(bookName -> bookName.toLowerCase().contains("science")).collect(Collectors.toList());
		return scienceBooksList;
	}
	
	private static List<String> getAllScienceBooks5(List<String> allBooksList) {

		List<String> scienceBooksList = allBooksList.stream().filter(findScienceBooksByLambda()).collect(Collectors.toList());
		return scienceBooksList;
	}

	// Check the return type of this method. It returns a Predicate instead of a boolean
	private static Predicate<? super String> findScienceBooksByLambda() {
		return bookName -> bookName.toLowerCase().contains("science");
	}
	
	/*private static List<String> getAllScienceBooks6(List<String> allBooksList) {

		//Won't compile as "this" cannot be sed in static context
		//List<String> scienceBooksList = allBooksList.stream().filter(this::findScienceBooksByLambda()).collect(Collectors.toList());
		List<String> scienceBooksList = allBooksList.stream().filter(MainClassToTestGenerics::findScienceBooksByLambda()).collect(Collectors.toList());
		return scienceBooksList;
	}*/

}
