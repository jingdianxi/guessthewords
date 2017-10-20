package com.jingdianxi.guessthewords;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GuessTheWords {

	// 定义字条串数组保存词库
	public static String[] str = { "apple", "bravo", "charlie", "delta", "echo", "fox", "apple", "banana", "cat", "dog", "egg", "element" };

	// 随机选取单词
	public static Random random = new Random();
	public static String word = str[random.nextInt(str.length)];

	// 初始化Scanner类对象
	public static Scanner scan = new Scanner(System.in);

	// 初始化猜词次数
	public static int chance = 5;

	// 初始化猜词结果
	public static String[] chars = new String[word.length()];

	public static void init() {
		// 初始化猜词结果为单词长度个横线
		for (int i = 0; i < chars.length; i++) {
			chars[i] = "-";
		}
		// 输出结果，游戏开始
		output();
	}

	// 输出猜词结果
	public static void output() {
		// 遍历猜词结果数组并输出剩余次数
		for (int i = 0; i < chars.length; i++) {
			System.out.print(chars[i]);
		}
		System.out.println();
		System.out.println("还有" + chance + "次机会");
		input();
	}

	// 获取用户猜词输入内容
	public static void input() {
		String key = scan.nextLine();
		// 判断用户输入是否为字母
		if (key.length() != 1 || key.charAt(0) < 65 || (key.charAt(0) > 90 && key.charAt(0) < 97) || key.charAt(0) > 122) {
			// 输入非字母，提示信息并重新捕获用户动作
			System.out.println("请输入单个字母");
			input();
		} else {
			// 输入字母，判断用户猜词是否正确
			check(key);
		}
	}

	// 判断用户猜词是否正确
	public static void check(String key) {
		if (word.indexOf(key.toLowerCase()) == -1) {
			// 猜词错误，次数减一
			chance--;
			// 判断剩余次数
			if (chance > 0) {
				// 剩余次数不为0，继续猜词
				output();
			} else {
				// 剩余次数为0，游戏结束
				over();
			}
		} else {
			// 猜词正确，相应位置字母转为可见
			show(key);
		}
	}

	// 显示用户猜到字母
	public static void show(String key) {
		// 遍历目标单词
		for (int i = 0; i < word.length(); i++) {
			// 将用户猜出字母转为可见
			if (word.charAt(i) == key.charAt(0)) {
				chars[i] = key;
			}
		}
		// 判断单词是否完全猜出
		judge();
	}

	// 判断单词是否完全猜出
	public static void judge() {
		// 判断用户是否完全猜出单词，即判断用户猜词数组结果是否存在"-"
		if (Arrays.asList(chars).contains("-")) {
			// 若未完全猜出，则输出当前结果并继续猜词
			output();
		} else {
			// 若完全猜出，则猜词成功
			goal();
		}
	}

	// 猜词成功
	public static void goal() {
		System.out.println(word);
		System.out.println("猜词成功");
	}

	// 猜词失败
	public static void over() {
		System.out.println("猜词失败，答案为" + word);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(word);
		init();
	}

}
