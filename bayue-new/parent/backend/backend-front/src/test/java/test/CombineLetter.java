package test;

import java.util.ArrayList;
import java.util.List;

public class CombineLetter {

	private static List<LetterNodeTree> letterList = new ArrayList<LetterNodeTree>();
	static {
		for (char c = 'a'; c <= 'f'; c++) {
			LetterNodeTree letter = new LetterNodeTree();
			letter.setLetter(String.valueOf(c));
			letterList.add(letter);
		}
	}
	
	
	public static void initLetter(List<LetterNodeTree> root, int deep) {
		for (LetterNodeTree tree : root) {
			int temp = deep;
			List<LetterNodeTree> child = getChild(root, tree);
			if (!child.isEmpty()) {
				tree.setChild(child);
			}
			while (--temp > 1) {
				initLetter(child, temp);
			}

		}

	}

	private static List<LetterNodeTree> getChild(List<LetterNodeTree> root, LetterNodeTree tree) {
		List<LetterNodeTree> child = new ArrayList<LetterNodeTree>();
		for (LetterNodeTree tree1 : root) {
			String letter = tree1.getLetter();
			if (!tree.getLetter().equals(letter)) {
				LetterNodeTree ctree = new LetterNodeTree();
				ctree.setLetter(letter);
				child.add(ctree);
			}
		}
		return child;
	}
	
	public static String getCombine(List<LetterNodeTree> root){
		String res = "";
		for(LetterNodeTree tree : root){
			String letter = tree.getLetter();
			res += letter;
			while(tree.hasChild()){
				res += getCombine(tree.getChild());
			}
		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println(letterList.get(0));
		initLetter(letterList, 6);
		System.out.println();
	}

}

class LetterNodeTree {
	private String letter;
	private List<LetterNodeTree> child;

	public boolean hasChild() {
		return child.isEmpty();
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public List<LetterNodeTree> getChild() {
		return child;
	}

	public void setChild(List<LetterNodeTree> child) {
		this.child = child;
	}

}
