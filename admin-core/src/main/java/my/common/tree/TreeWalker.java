package my.common.tree;

import java.util.ArrayList;
import java.util.List;

import my.common.beans.ITreeNode;

public class TreeWalker<T> {
	private List<? extends ITreeNode<T>> nodes;
	
	public TreeWalker(List<? extends ITreeNode<T>> nodes) {
		this.nodes = nodes;
	}
	
	public void getTree(ITreeNode<T> parent, TreeWalkerCallback<T> callback) {

		T pId = parent.getId();
		int number = 1;
		List<ITreeNode<T>> children = getChildren(pId);
		parent.setChildren(children);
		
		if(children!=null && children.size()>0) {
			int total = children.size();
			for(int i=0;i<children.size();i++) {
				ITreeNode<T> child = children.get(i);
				
				T id = child.getId();
				
				if(callback!=null) {
					callback.onItem(child, parent);
				}
				
				getTree(child, callback);
				number++;
			}
		}
	}
	
	private List<ITreeNode<T>> getChildren(T pId) {
		List<ITreeNode<T>> children = new ArrayList<ITreeNode<T>>();
		for(int i=0;i<nodes.size();i++) {
			ITreeNode<T> node = nodes.get(i);
			if(pId.equals(node.getPId())) {
				children.add(node);
			}
		}
		return children;
	}
}
