                                单向链表的应用
1.链表是以节点的方式来存储（链式存储）。
每个结点包含data域，next域：指向下一个节点。
链表的各个节点不一定是连续存放。
链表分为带头结点的和没有头结点的链表，这个根据实际的需求来去确定。

应用实例：使用带头结点的单向链表实现水浒英雄排行榜管理。
1>完成对英雄人物的增删改查操作，
2>第一种方法在添加英雄时，直接添加到链表的尾部，
3>第二种方式在添加英雄时，根据排名将英雄插入到指定位置（如果有这个排名，则添加失败，并给出提示）。
1
2
3
4
练习：
1>求单链表中有效节点的个数
2>查找单链表中的倒数第k个节点【新浪】
3>从尾到头打印单链表【百度要求方式1：反向遍历，要求方式2：Stack栈】
1
2
3
4
package LinkedList;

import java.util.Stack;

/*
 * 添加方法一：1.先创建一个head头节点，作用就是表示单链表的头
 * 2.后面我们每添加一个节点，就直接加入到链表的最后
 * 添加方法二：
 * 1.先创建一个head头节点，作用就是表示单链表的头
 * 2.找到新添加节点的位置，是通过辅助变量temp
 * 3.使新的节点.next=temp.next
 * 4.将temp.next=新的节点
 * 删除：从单链表中删除一个节点的思路：
 * 1.head不能动，因此需要一个temp辅助节点找到待删除节点的前一个节点
 * 2.先找到需要删除的这个节点的前一个节点temp，
 * 3.temp.next=temp.next.next
 * 4.被删除的节点将不会有其他引用指向，会被垃圾回收机制回收
 * 遍历：通过一个辅助变量遍历，帮助遍历整个链表
 */
public class SingleLinkedListDemo {
	public static void main(String[] args) {
		// 先创建节点
		System.out.println("增加后的链表情况：");
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
		HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
		// 加入
		// 创建链表
		SingleLinkedList singleLinkedList = new SingleLinkedList();
		// 根据第一种方法加入
		// singleLinkedList.add(hero1);
		// singleLinkedList.add(hero2);
		// singleLinkedList.add(hero3);
		// singleLinkedList.add(hero4);
		// 根据第二种方法加入(加入按照编号的顺序)
		singleLinkedList.addByOrder(hero1);
		singleLinkedList.addByOrder(hero2);
		singleLinkedList.addByOrder(hero3);
		singleLinkedList.addByOrder(hero4);
		// 显示
		singleLinkedList.list();
		// 测试修改节点的代码
		HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟···");
		singleLinkedList.update(newHeroNode);
		// 显示一下修改后的链表的情况
		System.out.println("修改后的链表情况：");
		singleLinkedList.list();

		// 删除一个节点
		singleLinkedList.del(1);
		System.out.println("删除后的链表情况：");
		singleLinkedList.list();

		// 题目一：测试求单链表中有效节点的个数
		System.out.println("题目一：有效的结点个数为：" + getLength(singleLinkedList.getHead()));// 3

		// 题目二：测试是否得到倒数第k个元素
		HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 1);
		System.out.println("题目二：倒数第一个元素：" + res);

		// 题目三：逆序打印单链表
		System.out.println("题目三：逆序打印单链表");
		reversePrint(singleLinkedList.getHead());
	}

	// 题目一：
	// 方法：获取到单链表的节点的个数（如果是带头结点的链表，需求不统计头结点）
	/**
	 * head:链表的头结点 return:返回的就是有效节点的个数
	 */
	public static int getLength(HeroNode head) {
		if (head.next == null) {// 空链表
			return 0;
		}
		int length = 0;
		// 定义一个辅助变量,这里我们没有统计头结点
		HeroNode cur = head.next;
		while (cur != null) {
			length++;
			cur = cur.next;// 遍历
		}
		return length;
	}

	// 题目二：查找单链表中的倒数第k个节点【新浪】
	// 思路：
	// 1.编写一个方法，接收head节点，同时接收一个index
	// 2.index表示是倒数的index个节点
	// 3.先把链表从头到尾遍历，得到链表的总的长度getLength
	// 4.得到size后，从链表的第一个开始遍历（size-index）个
	// 如果找到，就返回该节点，否则返回null
	public static HeroNode findLastIndexNode(HeroNode head, int index) {
		// 判断如果链表为空，返回null
		if (head.next == null) {
			return null;// 没有找到
		}
		// 第一次遍历得到链表的长度
		int size = getLength(head);
		// 第二次遍历size-index位置，这就是倒数的第k个节点
		// 先做一个数据的校验，看数据是否合理
		if (index <= 0 || index > size) {
			return null;
		}
		// 定义一个辅助变量,for循环定位到倒数的index
		HeroNode cur = head.next;
		for (int i = 0; i < size - index; i++) {
			cur = cur.next;
		}
		return cur;
	}

	// 题目三：从尾到头打印单链表
	// 方式1：先将单链表进行反转操作，然后再遍历即可，这样做的问题是会破坏原来的单链表结构
	// 方式2：可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，实现逆序打印。
	// 这里采取方式2
	public static void reversePrint(HeroNode head) {
		if (head.next == null) {// 空链表不能打印
			return;
		}
		// 创建一个栈，将各个节点压入栈
		Stack<HeroNode> stack = new Stack<HeroNode>();
		HeroNode cur = head.next;
		// 将链表的所有节点压入栈
		while (cur != null) {
			stack.push(cur);
			cur = cur.next;// cur后移，压入下一个节点
		}
		// 将栈中的节点打印
		while (stack.size() > 0) {
			System.out.println(stack.pop());// stack的特点是先进后出
		}
	}
}

//定义一个SingleLinkedList来管理英雄
class SingleLinkedList {
	// 初始化一个头节点，头结点不要动,不存放具体的数字
	private HeroNode head = new HeroNode(0, "", "");

	// 题目一所添加的方法,返回头节点
	public HeroNode getHead() {
		return head;
	}

//第一种添加英雄的方式
	// 添加节点到单向链表
	public void add(HeroNode heroNode) {
		// 当不考虑编号的顺序时，找到当前链表的最后节点，将最后这个节点的next指向新的节点
		// 因为head节点不能动，因此我们需要一个辅助遍历temp
		HeroNode temp = head;
		// 遍历链表，找到到最后
		while (true) {
			// 找到链表的最后，就将temp后移
			if (temp.next == null) {
				break;
			}
			// 如果没有找到，就将temp后移
			temp = temp.next;
		}
		// 当退出while循环时，temp就指向了链表的最后
		temp.next = heroNode;
	}

//第二种添加英雄的方式
	public void addByOrder(HeroNode heroNode) {
		// 因为头结点不能动，因此我们仍然通过一个辅助变量来帮助找到添加的位置
		// 因为单链表，因此我们找的temp位于添加位置的前一个节点，否则插入不了
		HeroNode temp = head;
		boolean flag = false;// 标识添加的变量是否存在，默认为false，如果已经添加过，则报错
		while (true) {
			if (temp.next == null) {
				// 说明temp已经在链表的最后
				break;
			}
			if (temp.next.no > heroNode.no) {
				// 位置已经找到，就在temp后面插入
				break;
			} else if (temp.next.no == heroNode.no) {
				// 说明希望添加的heroNode的编号已经存在
				flag = true;// 说明编号已经存在
				break;
			}
			temp = temp.next;// 后移，就相当于遍历当前的链表
		}
		// 判断flag的值
		if (flag == true) {
			// 不能添加，说明编号存在
			System.out.printf("准备插入的英雄的编号%d已经存在，不能加入", heroNode.no);
		} else {
			// 插入到链表中，temp的后面
			heroNode.next = temp.next;
			temp.next = heroNode;
		}
	}

	// 修改节点的信息，根据编号来修改，即no编号不能修改
	// 说明：1.根据newHeroNode的no来修改即可
	public void update(HeroNode newHeroNode) {
		// 判断是否为空
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		// 找到需要修改的节点，根据no编号
		// 定义一个辅助变量
		HeroNode temp = head.next;
		boolean flag = false;// 表示是否找到该节点
		while (true) {
			// 如果查找过程中temp为空，说明到了已经遍历完链表
			if (temp == null) {
				break;
			}
			if (temp.no == newHeroNode.no) {
				// 找到
				flag = true;
				break;
			}
			temp = temp.next;
		}
		// 根据flag判断是否找到要修改的节点
		if (flag) {
			temp.name = newHeroNode.name;
			temp.nickname = newHeroNode.nickname;
		} else {
			// 没有找到
			System.out.printf("没有找到编号为%d的节点，不能修改\n", newHeroNode);
		}
	}

	// 删除节点
	public void del(int no) {
		HeroNode temp = head;
		boolean flag = false;// 标志是否找到待删除节点的
		while (true) {
			if (temp.next == null) {// 已经到链表的最后
				break;
			}
			if (temp.next.no == no) {
				// 找到待删除节点的前一个节点temp
				flag = true;
				break;
			}
			temp = temp.next;
		}
		// 判断flag
		if (flag) {// 找到，可以删除
			temp.next = temp.next.next;
		} else {
			System.out.printf("要删除的%d节点不存在\n", no);
		}
	}

	// 通过遍历，显示链表因此我们需要一个辅助变量来遍历
	public void list() {
		// 判断链表是否为空
		if (head.next == null) {
			System.out.println("链表为空");
			return;
		}
		// 因为头结点不能动
		HeroNode temp = head.next;
		while (true) {
			// 判断是否到链表最后
			if (temp == null) {
				break;
			}
			// 输出节点的信息
			System.out.println(temp);
			// 将next一定要后移
			temp = temp.next;
		}
	}
}

//定义一个HeroNode，每个HeroNode对象就是一个节点
class HeroNode {
	public int no;
	public String name;
	public String nickname;
	public HeroNode next;// 指向下一个节点
	// 构造器

	public HeroNode(int no, String name, String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}

	// 为了显示方便，重写toString
	public String toString() {
		return "HeroNode[no=" + no + ",name=" + name + ",nickname=" + nickname + "]";
	}
}
