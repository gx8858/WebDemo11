package cn.itcast.servlet;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "d:/aa/boy3.jpg"; 
		int num = path.lastIndexOf("/");
		String subString = path.substring(num + 1);
		System.out.println(num);
		System.out.println(subString);
	}

}
