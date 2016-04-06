package com.stone.common;

import java.util.List;

public class Tree {

	private List<Tree> children = null;
	private String text = null;
	private boolean leaf = false;
	private boolean expanded = true;
//	private boolean checked = false;
	private Object model;

	public static Tree folder(String text, Object model){
		Tree tree = new Tree();
		tree.setExpanded(true);
		tree.setLeaf(false);
//		tree.setChecked(false);
		tree.setText(text);
		tree.setModel(model);
		return tree;
	}

	public static Tree leaf(String text, Object model){
		Tree tree = new Tree();
		tree.setExpanded(true);
		tree.setLeaf(true);
//		tree.setChecked(false);
		tree.setText(text);
		tree.setModel(model);
		return tree;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public Object getModel() {
		return model;
	}

	public void setModel(Object model) {
		this.model = model;
	}

//	public boolean isChecked() {
//		return checked;
//	}
//
//	public void setChecked(boolean checked) {
//		this.checked = checked;
//	}


}
