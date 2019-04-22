package my.common.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface ITreeNode<T> {
	<T> T getId();
	@JsonProperty("pId")
	<T> T getPId();
	String getName();
	List getChildren();
	void setChildren(List children);
}
