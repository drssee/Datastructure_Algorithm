package skill;

import java.util.ArrayList;
import java.util.List;

class MyQueue<T>{
	public Integer count=0;
	List<T> queByList = new ArrayList<>();
	public boolean enqueue(T item) {
		boolean result = queByList.add(item);
		setCount(result);
		return result;
	}
	public void setCount(Boolean tf) {
		if(tf) {
			count++;
		}
	}
	public T dequeue() {
		if(queByList.isEmpty()) {
			return null;
		}
		T result = queByList.get(0);
		queByList.remove(0);
		return result;
	}
}

public class Test2_Queue {
	
	public static void main(String[] args) {
		MyQueue<Integer> mq = new MyQueue<>();
		mq.enqueue(5);
		mq.enqueue(4);
		mq.enqueue(3);
		mq.enqueue(2);
		mq.enqueue(1);
		System.out.println("inQue:"+mq.queByList);
		for(Integer i = 0;i<mq.count;i++) {
			System.out.println(mq.dequeue());
			System.out.println("inQue:"+mq.queByList);
		}
	}
}
