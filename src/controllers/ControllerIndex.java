package controllers;

import java.util.ArrayList;

public class ControllerIndex {

	public static ArrayList<String> getCategories() {
		ArrayList<String> categories=new ArrayList<String>();
		categories.add("Men");
		categories.add("Women");
		categories.add("esto es");
		categories.add("dinamico");
		return categories;
	}

}