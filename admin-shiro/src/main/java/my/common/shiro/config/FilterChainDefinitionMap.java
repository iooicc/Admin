package my.common.shiro.config;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.factory.FactoryBean;

public class FilterChainDefinitionMap implements FactoryBean<Section> {
	private Section section;
	private String defaultFilterChainDefinitions;
	private String filterChainDefinitions;

	public Section getObject() {
		if (this.section == null) {
			Ini a = new Ini();
			a.load(this.defaultFilterChainDefinitions);
			this.section = a.getSection("");
			
			a.load(this.filterChainDefinitions);
			this.section.putAll(a.getSection(""));
		}
		return this.section;
	}

	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	public boolean isSingleton() {
		return false;
	}

	public void setDefaultFilterChainDefinitions(String defaultFilterChainDefinitions) {
		this.defaultFilterChainDefinitions = defaultFilterChainDefinitions;
	}

	public Class<?> getObjectType() {
		return this.getClass();
	}
}
