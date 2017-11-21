package com.upanda.updp.server.system.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.domain.Persistable;

import com.alibaba.fastjson.JSON;

/**
 * 实体Base类
 * 
 * @author WuZhiFeng
 * @date 2016年7月29日
 */
@MappedSuperclass
public abstract class BaseEntity implements Persistable<Long>,Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "int COMMENT '主键，自动生成'", nullable = false, unique = true)
	private Long id;

	/**
	 * 创建时间
	 */
	@Column(columnDefinition = "datetime COMMENT '创建时间'",nullable = false)
	private Date createTime;

	/**
	 * 修改时间
	 */
	@Column(columnDefinition = "datetime COMMENT '最后修改时间'")
	private Date modifyTime;

	/**
	 * 时间戳
	 */
	@Column(columnDefinition = "datetime COMMENT '时间戳'",nullable = false)
	private Date ts;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public boolean isNew() {
		return this.id == null;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 新增前触发
	 */
	@PrePersist
	public void prePersist() {
		Date nowDate = new Date();
		this.createTime = nowDate;// 设置创建时间为当前时间
		this.ts = nowDate;// 设置时间戳为当前时间
	}

	/**
	 * 更新前触发
	 */
	@PreUpdate
	public void preUpdate() {
		Date nowDate = new Date();
		this.modifyTime = nowDate;// 设置最后修改时间为当前时间
		this.ts = nowDate;// 设置时间戳为当前时间
	}

	public String toJSONString() {
		return JSON.toJSONString(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!getClass().equals(obj.getClass())) {
			return false;
		}
		BaseEntity rhs = (BaseEntity) obj;
		return this.id == null ? false : this.id.equals(rhs.id);
	}
}