package skill;

import java.util.ArrayList;
import java.util.List;

class MyStack<T> {
	private List<T> al = new ArrayList<>();
	public T push(T item) {
		if(al.add(item)) {
			return item;
		}
		return null; 
	}
	public T pop() {
		if(al.isEmpty()) {
			return null;
		}
		return al.remove(al.size()-1);
	}
	public int getSize() {
		return al.size();
	}
}

public class Test3_Stack {

	public static void main(String[] args) {
		MyStack<Integer> ms = new MyStack<>();
		ms.push(1);
		ms.push(2);
		ms.push(3);
		ms.push(4);
		ms.push(5);
		int cSize=ms.getSize();
		for(int i=0;i<cSize;i++) {
			System.out.println(ms.pop());		
		}
	}

}
