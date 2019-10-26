//前驱 prev	previous
//后继 next
class Node {
	int val; // data | element
	Node next; // 如果 next == null 表示是最后一个结点

	Node(int val) {
		this.val = val;
		this.next = null;
	}

	public String toString() {
		return String.format("Node(%d)", val);
	}
}

public class MyLinkedList {
	public static void main(String[] args) {
		Node head = null;
		// head 的意思是链表的第一个结点
		// 通过第一个结点，就可以找到完整的链表的所有结点
		// 所以，链表的第一个结点往往代表整个链表

		// 空的链表，就是一个结点都没有的链表
		// 也就没有第一个结点
		// head == null 表示第一个结点不存在
		// 也就是整个链表为空

		// 头插
		/*
		 * int val = 0; // 1. 结点 Node node = new Node(val); // 2. 让原来的 head 成为 node
		 * 的下一个结点 node.next = head; // 3. 更新第一个结点的引用 head = node;
		 * 
		 * pushFront(head, 0);
		 */

		head = pushFront(head, 0);
		head = pushFront(head, 1);
		head = pushFront(head, 2);

		// 打印
		print(head); // 2 1 0

		// 尾插
		head = popFront(head);
		print(head); // 1 0

		head = pushBack(head, 10);
		head = pushBack(head, 20);
		head = pushBack(head, 30);
		print(head); // 1 0 10 20 30
		head = popBack(head);
		head = popBack(head);
		head = popBack(head);
		head = popBack(head);
		head = popBack(head);

		head = popBack(head); // 报错
		print(head); // 空

		head = pushBack(head, 100);
		print(head); // 100
	}

	// 打印
	private static void print(Node head) {
		System.out.println("打印链表：");
		for (Node cur = head; cur != null; cur = cur.next) {
			System.out.print(cur + " --> ");
		}
		System.out.println("null");
	}

	// 头插
	// head: 原来的第一个结点
	// val：要插入的值
	// 返回：新的第一个结点
	private static Node pushFront(Node head, int val) {
		// 1. 结点
		Node node = new Node(val);
		// 2. 让原来的 head 成为 node 的下一个结点
		node.next = head;
		// 3. 更新第一个结点的引用
		return node;
	}

	private static Node pushBack(Node head, int val) {
		Node node = new Node(val);
		if (head == null) {
			return node;
		} else {
			Node last = head;
			while (last.next != null) {
				last = last.next;
			}
			last.next = node;
			return head;
		}
	}

	private static Node popFront(Node head) {
		if (head == null) {
			System.err.println("空链表无法删除");
			return null;
		}

		// 原来第一个结点，会因为没有引用指向而被回收
		return head.next;
	}

	private static Node popBack(Node head) {
		if (head == null) {
			System.err.println("空链表无法删除");
			return null;
		}

		if (head.next == null) {
			return null;
		} else {
			Node lastSecond = head;
			while (lastSecond.next.next != null) {
				lastSecond = lastSecond.next;
			}

			lastSecond.next = null;
			return head;
		}
	}
}
