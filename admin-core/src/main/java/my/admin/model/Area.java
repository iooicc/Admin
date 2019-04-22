package my.admin.model;

import java.util.Date;
import java.util.List;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import my.admin.core.model.BaseModel;
import my.common.beans.ITreeNode;



/**
 * 行政区划Entity
 */
@Table(name="sys_area")
public class Area extends BaseModel implements ITreeNode<String> {

	private static final long serialVersionUID = 1L;
	@AssignID
	private String areaCode;		// 区域代码
	private String areaName;		// 区域名称
	private String areaType; 		// 区域类型（1：国家；2：省份、直辖市；3：地市；4：区县）	

	// 是否最末级
	private String treeLeaf;
	// 层次级别
	private Integer treeLevel;
	// 全节点名
	private String treeNames;
	// 本级排序号（升序）
	private Long treeSort;
	// 所有级别排序号
	private String treeSorts;
	
	// 状态（0正常 1删除 2停用）
	private String status;
	
	protected String parentCode;
	
	// 所有父级编号
	private String parentCodes;
	
	private List<Area> children;
	
	private Area parent;
	
	// 创建时间
	private Date createDate;
	// 更新时间
	private Date updateDate;
	
	// 备注信息
	private String remarks;
	
	// 更新者
	private String updateBy;
	
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@NotBlank(message="名称不能为空")
	@Length(min=0, max=100, message="名称长度不能超过 100 个字符")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	@NotBlank(message="类型不能为空")
	@Length(min=0, max=1, message="类型长度不能超过 1 个字符")
	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	
	@Override
	public String toString() {
		return areaCode;
	}
	
	@Override
	public String getId() {
		return getAreaCode();
	}
	
	@Override
	public String getPId() {
		return getParentCode();
	}

	@Override
	public String getName() {
		return getAreaName();
	}

	@Override
	public List<Area> getChildren() {
		return children;
	}

	@Override
	public void setChildren(List children) {
		this.children = children;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Area getParent() {
		return parent;
	}

	public void setParent(Area parent) {
		this.parent = parent;
	}

	public String getTreeLeaf() {
		return treeLeaf;
	}

	public void setTreeLeaf(String treeLeaf) {
		this.treeLeaf = treeLeaf;
	}

	public Integer getTreeLevel() {
		return treeLevel;
	}

	public void setTreeLevel(Integer treeLevel) {
		this.treeLevel = treeLevel;
	}

	public String getTreeNames() {
		return treeNames;
	}

	public void setTreeNames(String treeNames) {
		this.treeNames = treeNames;
	}

	public Long getTreeSort() {
		return treeSort;
	}

	public void setTreeSort(Long treeSort) {
		this.treeSort = treeSort;
	}

	public String getTreeSorts() {
		return treeSorts;
	}

	public void setTreeSorts(String treeSorts) {
		this.treeSorts = treeSorts;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getParentCodes() {
		return parentCodes;
	}

	public void setParentCodes(String parentCodes) {
		this.parentCodes = parentCodes;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}


	
	
	
}