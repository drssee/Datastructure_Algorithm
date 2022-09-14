package skill;

class SingleLinkedList<T>{
	public Node<T> head = null;
	
	public class Node<T>{
		T data;
		Node<T> next = null;
		public Node(T data) {
			this.data=data;
		}
	}
	
	public void addNode(T data) {
		if(head==null) {
			head=new Node<T>(data);
		}
		else {
			Node<T> node = this.head;
			while(node.next!=null) {//next가 null이면 이게 제일 끝
				node=node.next;
			}
			node.next=new Node<T>(data);
		}
	}
	public void printAll() {
		if(head!=null) {
			Node<T> node=this.head;
			System.out.println(node.data);
			while(node.next!=null) {
				node=node.next;
				System.out.println(node.data);
			}
		}
	}
	
	public void addNodeInside(T data,T isData) {
		Node<T> searchedNode = this.search(isData);
		if(searchedNode==null) {
			this.addNode(data);
		}
		else {
			Node<T> nextNode = searchedNode.next;
			
			Node<T> insertedNode = new Node<T>(data);
			insertedNode.next=nextNode;
			
			searchedNode.next=insertedNode;
		}
	}
	public Node<T> search(T data){
		if(this.head==null) {
			return null;
		}
		else {
			Node<T> node=this.head;
			while(node!=null) {
				if(node.data==data) {
					return node;
				}
				else {
					node=node.next;
				}
			}
			return null;
		}
	}
	
	public boolean delNode(T isData) {
		if(this.head==null) {
			return false;
		}
		else {
			Node<T> node = this.head;
			if(node.data==isData) {
				this.head=head.next;
				return true;
			}
			else {
				while(node.next!=null) {
					if(node.next.data==isData) {
						node.next = node.next.next;
						return true;
					}
					node=node.next;
				}//while
				return false;
			}
		}
	}

}

public class Test4_SingleLinkedList {

	public static void main(String[] args) {
		SingleLinkedList<Integer> sl = new SingleLinkedList<>();
		sl.addNode(1);
		sl.addNode(2);
		sl.addNode(3);
		sl.printAll();
	}

}
