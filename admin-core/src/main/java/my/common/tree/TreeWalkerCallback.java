package my.common.tree;

import my.common.beans.ITreeNode;

public abstract class TreeWalkerCallback<T> {
	public abstract void onItem(ITreeNode node, ITreeNode parent);
}
