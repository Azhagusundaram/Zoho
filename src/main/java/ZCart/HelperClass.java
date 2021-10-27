package ZCart;

import java.util.ArrayList;
import java.util.Collection;

public class HelperClass{
	private String name;
	private Collection list=new ArrayList();
	public HelperClass(String name,Collection<Category> categories) {
		this.name=name;
		this.list=categories;
	}
	public String getName() {
		return name;
	}
	public Collection getList() {
		return list;
	}
}