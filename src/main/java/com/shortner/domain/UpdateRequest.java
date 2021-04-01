package com.shortner.domain;

public class UpdateRequest {
private Long id;
private String oldName;
private String newName;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getOldName() {
	return oldName;
}
public void setOldName(String oldName) {
	this.oldName = oldName;
}
public String getNewName() {
	return newName;
}
public void setNewName(String newName) {
	this.newName = newName;
}
@Override
public String toString() {
	return "updateRequest [id=" + id + ", oldName=" + oldName + ", newName=" + newName + "]";
}

}
