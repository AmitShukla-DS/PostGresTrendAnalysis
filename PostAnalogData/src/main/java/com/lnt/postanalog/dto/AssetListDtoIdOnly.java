package com.lnt.postanalog.dto;

import java.util.List;

public class AssetListDtoIdOnly {

		private String id;

		private String name;

		private String displayField;

		private String uom;

		private Integer assetTypeSeq;

		private String assetTypeName;

		private List<AssetListDtoIdOnly> children;
		
		private String type;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDisplayField() {
			return displayField;
		}

		public void setDisplayField(String displayField) {
			this.displayField = displayField;
		}

		public Integer getAssetTypeSeq() {
			return assetTypeSeq;
		}

		public void setAssetTypeSeq(Integer assetTypeSeq) {
			this.assetTypeSeq = assetTypeSeq;
		}

		public String getAssetTypeName() {
			return assetTypeName;
		}

		public void setAssetTypeName(String assetTypeName) {
			this.assetTypeName = assetTypeName;
		}

		public List<AssetListDtoIdOnly> getChildren() {
			return children;
		}

		public void setChildren(List<AssetListDtoIdOnly> children) {
			this.children = children;
		}
		
		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getUom() {
			return uom;
		}

		public void setUom(String uom) {
			this.uom = uom;
		}
	}


