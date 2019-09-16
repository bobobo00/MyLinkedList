package cn.test.mycollection;
/**
 * 自定义实现LinkedList
 * @author dell
 *
 */

public class MyLinkedList<E> {
	public static class Node<E>{
		Node previous;
		E element;
		Node next;
		public Node(E element) {
			this.element=element;
		}
		public Node() {
			super();
		}
		/*public String toString() {
			return String.format("[%c]->", element);
		}*/
		public String toString() {
			StringBuilder sb=new StringBuilder();
			Node temp=first;
			if(temp==null) {
				System.err.println("链表为空");
				sb.append(']');
				return sb.toString();
			}
			while(temp!=null) {
				sb.append("["+temp.element+"]->");
				temp=temp.next;
			}
			sb.append("null");
			return sb.toString();
		}
	}
	
	private static Node first;
	private static Node last;
	private int size;
	public void checkRange(int index) {
		if(index<0||index>size-1) {
			throw new RuntimeException("索引数字不合法");
		}
			
	}
	public Node getIndex(int index) {
		this.checkRange(index);
		if(index>size/2) {
			Node temp=last;
			for(int i=size-1;i>index;i--) {
				temp=temp.previous;
			}
			return temp;
		}else {
			Node temp=first;
			for(int i=0;i<index;i++) {
				temp=temp.next;
			}
			return temp;
		}
	}
	public void add(E obj) {
		Node node=new Node(obj);
		if(first==null) {
			first=node;
		}else {
			last.next=node;
			node.previous=last;
		}
		last=node;
		size++;
	}
	public E get(int index) {
		if(first==null) {
			System.err.println("链表为空");
			return null;
		}
		Node result=this.getIndex(index);
		return (E)result.element;
	}
	
	public E remove(int index) {
		if(first==null) {
			System.err.println("链表为空");
			return null;
		}
		if(index==0) {
			Node result=first;
			first=first.next;
			size--;
			return (E)result.element;
		}
		if(index==size-1) {
			Node result=last;
			last=last.previous;
			last.next=null;
			size--;
			return (E)result.element;
		}
		Node temp=this.getIndex(index-1);
		Node result=temp.next;
		temp.next=result.next;
		result.next.previous=temp;
		size--;
		return (E)result.element;
	}
	public void insert(E element,int index) {
		Node n=new Node(element);
		size++;
		if(index==0) {
			first.previous=n;
			n.next=first;
			first=n;
			return;
		}
		if(index==size) {
			last.next=n;
			n.previous=last;
			last=n;
			return;
		}
		Node temp=this.getIndex(index-1);
		n.next=temp.next;
		n.previous=temp;
		n.next.previous=n;
		temp.next=n;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyLinkedList<Character> ml=new MyLinkedList();
		System.out.println(first);
		ml.add('a');
		ml.add('b');
		ml.add('c');
		ml.add('d');
		ml.add('e');
		System.out.println(first);
		/*System.out.println(ml.get(3));
		System.out.println(ml.remove(0));
		System.out.println(first);
		System.out.println(ml.remove(3));
		System.out.println(first);*/
		ml.insert('&', 0);
		System.out.println(first);
		ml.insert('*', 6);
		ml.insert('(', 3);
		System.out.println(first);
		

	}

}
