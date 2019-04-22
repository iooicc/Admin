package my.admin.model;

import java.io.StringWriter;
import java.util.Date;
import java.util.List;

import org.beetl.sql.core.annotatoin.AssignID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import my.admin.core.model.BaseModel;
import my.common.beans.ITreeNode;

/* 
* 
* gen by beetlsql 2018-08-21
*/
public class SysMenu extends BaseModel implements ITreeNode<String> {

	// 菜单编码
	@AssignID
	private String menuCode;
	// 创建者
	private String createBy;
	// 是否显示（1显示 0隐藏）
	private String isShow;
	// 颜色
	private String menuColor;
	// 链接
	private String menuHref;
	// 图标
	private String menuIcon;
	// 菜单名称
	private String menuName;
	// 目标
	private String menuTarget;
	// 菜单类型（1菜单 2权限 3开发）
	private String menuType;
	// 归属模块（多个用逗号隔开）
	private String moduleCodes;
	// 父级编号
	private String parentCode;
	// 所有父级编号
	private String parentCodes;
	// 权限标识
	private String permission;
	// 备注信息
	private String remarks;
	// 状态（0正常 1删除 2停用）
	private String status;
	// 归属系统（default:主导航菜单、mobileApp:APP菜单）
	private String sysCode;
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
	// 更新者
	private String updateBy;
	// 菜单权重
	private Integer weight;
	// 创建时间
	private Date createDate;
	// 更新时间
	private Date updateDate;
	
	private SysMenu parent;
	
	private Integer isBuilt;
	
	private List<SysMenu> children;

	public SysMenu() {
	}

	/**
	 * 菜单编码
	 * 
	 * @return
	 */
	public String getMenuCode() {
		return menuCode;
	}

	/**
	 * 菜单编码
	 * 
	 * @param menuCode
	 */
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	/**
	 * 创建者
	 * 
	 * @return
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * 创建者
	 * 
	 * @param createBy
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * 是否显示（1显示 0隐藏）
	 * 
	 * @return
	 */
	public String getIsShow() {
		return isShow;
	}

	/**
	 * 是否显示（1显示 0隐藏）
	 * 
	 * @param isShow
	 */
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	/**
	 * 颜色
	 * 
	 * @return
	 */
	public String getMenuColor() {
		return menuColor;
	}

	/**
	 * 颜色
	 * 
	 * @param menuColor
	 */
	public void setMenuColor(String menuColor) {
		this.menuColor = menuColor;
	}

	/**
	 * 链接
	 * 
	 * @return
	 */
	public String getMenuHref() {
		return menuHref;
	}

	/**
	 * 链接
	 * 
	 * @param menuHref
	 */
	public void setMenuHref(String menuHref) {
		this.menuHref = menuHref;
	}

	/**
	 * 图标
	 * 
	 * @return
	 */
	public String getMenuIcon() {
		return menuIcon;
	}

	/**
	 * 图标
	 * 
	 * @param menuIcon
	 */
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	/**
	 * 菜单名称
	 * 
	 * @return
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * 菜单名称
	 * 
	 * @param menuName
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * 目标
	 * 
	 * @return
	 */
	public String getMenuTarget() {
		return menuTarget;
	}

	/**
	 * 目标
	 * 
	 * @param menuTarget
	 */
	public void setMenuTarget(String menuTarget) {
		this.menuTarget = menuTarget;
	}

	/**
	 * 菜单类型（1菜单 2权限 3开发）
	 * 
	 * @return
	 */
	public String getMenuType() {
		return menuType;
	}

	/**
	 * 菜单类型（1菜单 2权限 3开发）
	 * 
	 * @param menuType
	 */
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	/**
	 * 归属模块（多个用逗号隔开）
	 * 
	 * @return
	 */
	public String getModuleCodes() {
		return moduleCodes;
	}

	/**
	 * 归属模块（多个用逗号隔开）
	 * 
	 * @param moduleCodes
	 */
	public void setModuleCodes(String moduleCodes) {
		this.moduleCodes = moduleCodes;
	}

	/**
	 * 父级编号
	 * 
	 * @return
	 */
	public String getParentCode() {
		return parentCode;
	}

	/**
	 * 父级编号
	 * 
	 * @param parentCode
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	/**
	 * 所有父级编号
	 * 
	 * @return
	 */
	public String getParentCodes() {
		return parentCodes;
	}

	/**
	 * 所有父级编号
	 * 
	 * @param parentCodes
	 */
	public void setParentCodes(String parentCodes) {
		this.parentCodes = parentCodes;
	}

	/**
	 * 权限标识
	 * 
	 * @return
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * 权限标识
	 * 
	 * @param permission
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

	/**
	 * 备注信息
	 * 
	 * @return
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 备注信息
	 * 
	 * @param remarks
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * 状态（0正常 1删除 2停用）
	 * 
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 状态（0正常 1删除 2停用）
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 归属系统（default:主导航菜单、mobileApp:APP菜单）
	 * 
	 * @return
	 */
	public String getSysCode() {
		return sysCode;
	}

	/**
	 * 归属系统（default:主导航菜单、mobileApp:APP菜单）
	 * 
	 * @param sysCode
	 */
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	/**
	 * 是否最末级
	 * 
	 * @return
	 */
	public String getTreeLeaf() {
		return treeLeaf;
	}

	/**
	 * 是否最末级
	 * 
	 * @param treeLeaf
	 */
	public void setTreeLeaf(String treeLeaf) {
		this.treeLeaf = treeLeaf;
	}

	/**
	 * 层次级别
	 * 
	 * @return
	 */
	public Integer getTreeLevel() {
		return treeLevel;
	}

	/**
	 * 层次级别
	 * 
	 * @param treeLevel
	 */
	public void setTreeLevel(Integer treeLevel) {
		this.treeLevel = treeLevel;
	}

	/**
	 * 全节点名
	 * 
	 * @return
	 */
	public String getTreeNames() {
		return treeNames;
	}

	/**
	 * 全节点名
	 * 
	 * @param treeNames
	 */
	public void setTreeNames(String treeNames) {
		this.treeNames = treeNames;
	}

	/**
	 * 本级排序号（升序）
	 * 
	 * @return
	 */
	public Long getTreeSort() {
		return treeSort;
	}

	/**
	 * 本级排序号（升序）
	 * 
	 * @param treeSort
	 */
	public void setTreeSort(Long treeSort) {
		this.treeSort = treeSort;
	}

	/**
	 * 所有级别排序号
	 * 
	 * @return
	 */
	public String getTreeSorts() {
		return treeSorts;
	}

	/**
	 * 所有级别排序号
	 * 
	 * @param treeSorts
	 */
	public void setTreeSorts(String treeSorts) {
		this.treeSorts = treeSorts;
	}

	/**
	 * 更新者
	 * 
	 * @return
	 */
	public String getUpdateBy() {
		return updateBy;
	}

	/**
	 * 更新者
	 * 
	 * @param updateBy
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * 菜单权重
	 * 
	 * @return
	 */
	public Integer getWeight() {
		return weight;
	}

	/**
	 * 菜单权重
	 * 
	 * @param weight
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	/**
	 * 创建时间
	 * 
	 * @return
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 创建时间
	 * 
	 * @param createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 更新时间
	 * 
	 * @return
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * 更新时间
	 * 
	 * @param updateDate
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public SysMenu getParent() {
		return parent;
	}

	public void setParent(SysMenu parent) {
		this.parent = parent;
	}

	public Integer getIsBuilt() {
		return isBuilt;
	}

	public void setIsBuilt(Integer isBuilt) {
		this.isBuilt = isBuilt;
	}
	
	public String getId() {
		return getMenuCode();
	}
	
	public String getPId() {
		return getParentCode();
	}
	
	public String getName() {
		return getMenuName();
	}

	public List<SysMenu> getChildren() {
		return children;
	}

	public void setChildren(List children) {
		this.children = children;
	}
	
	@JsonIgnore
	public String getHtml() {
		StringWriter writer = new StringWriter(128);
		writer.append("<li class=\"treeview\">");
		writer.append("<a title=\"").append(menuName).append("\"")
			.append(" href=\"javascript:\" data-href=\"").append(menuHref).append("\"")
			.append(" class=\"addTabPage\">")
				.append("<i class=\"fa fa-fw ").append(menuIcon).append("\"></i>")
				.append(" <span>").append(menuName).append("</span>");
		if(children!=null&&children.size()!=0) {
			writer.append("<span class=\"pull-right-container\"><i class=\"fa fa-angle-left pull-right\"></i></span>");
		}
		writer.append("</a>");
		if(children!=null&&children.size()!=0) {
			writer.append("<ul class=\"treeview-menu\">");
			for(int i=0;i<children.size();i++) {
				SysMenu child = children.get(i);
				writer.append(child.getHtml());
			}
			writer.append("</ul>");
		}
		
		writer.append("</li>");
		return writer.toString();
	}
}
