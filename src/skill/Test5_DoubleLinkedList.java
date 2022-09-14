package skill;

public class Test5_DoubleLinkedList<T> {
	public Node<T> head = null;
	public Node<T> tail = null;

	public class Node<T> {
		T data;
		Node<T> prev = null;
		Node<T> next = null;

		public Node(T data) {
			this.data = data;
		}
	}

	public void addNode(T data) {
		if (this.head == null) {
			this.head = new Node<T>(data);
			this.tail = this.head;
		} else {
			Node<T> node = this.head;
			while (node.next != null) {
				node = node.next;
			}
			node.next = new Node<T>(data);
			tail = node.next;
			node.next.prev = node;
		}
	}

	public void printAll() {
		if (this.head != null) {
			System.out.println(head.data);
			Node<T> node = head;
			while (node.next != null) {
				node = node.next;
				System.out.println(node.data);
			}
		}
	}

	public T searchFromHead(T isData) {
		if (head == null) {
			return null;
		} else {
			Node<T> node = head;
			while (node != null) {
				if (node.data == isData) {
					return node.data;
				} else {
					node = node.next;
				}
			}
			return null;
		}
	}

	public T searchFromTail(T isData) {
		if (tail == null) {
			return null;
		} else {
			Node<T> node = tail;
			while (node != null) {
				if (node.data == isData) {
					return node.data;
				} else {
					node = node.prev;
				}
			}
			return null;
		}
	}

	public boolean addNode(T data, T isData) {
		if (head == null) {
			addNode(data);
			System.out.println("isData 없음");
			return false;
		} else if (searchFromHead(isData) == null) {
			addNode(data);
			System.out.println("isData 없음");
			return false;
		} else {// 찾는 데이터가 있을때
			if (this.head.data == isData) {
				this.head.prev = new Node<T>(data);
				this.head.prev.next = this.head;
				this.head = this.head.prev;
				return true;
			}
			Node<T> node = this.head;
			while (node != null) {
				if (node.data == isData) {
					Node<T> prev = node.prev;
					node.prev = new Node<T>(data);
					node.prev.next = node;
					node.prev.prev = prev;
					node.prev.prev.next = node.prev;
					return true;
				}
				node = node.next;
			}
			return false;
		}
	}
}
