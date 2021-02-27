package com.proway.godev.utils;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(
		 name = "findByAssignedIds", 
		 classes = {@ConstructorResult( 
		          targetClass = com.proway.godev.utils.ReturnAssignedIdsUtil.class,
		          columns = {
		               @ColumnResult( name = "eventRoomId", type = Long.class),  
		               @ColumnResult( name = "coffeeSpaceId", type = Long.class)})})
@NamedNativeQuery(name = "AssignedIds",
query = "select eventroom_id as eventRoomId, coffeespace_id as coffeeSpaceId from tb_participant part where part.first_name = :firstName and part.last_name = :lastName",
resultSetMapping = "findByAssignedIds")
public class ReturnAssignedIdsUtil {
	
	private long eventRoomId;
	private long coffeeSpaceId;
	
	public ReturnAssignedIdsUtil() {}
	public ReturnAssignedIdsUtil(long eventRoomId, long coffeeSpaceId) {
		super();
		this.eventRoomId = eventRoomId;
		this.coffeeSpaceId = coffeeSpaceId;
	}
	
	public long getEventRoomId() {
		return eventRoomId;
	}
	public void setEventRoomId(long eventRoomId) {
		this.eventRoomId = eventRoomId;
	}
	public long getCoffeeSpaceId() {
		return coffeeSpaceId;
	}
	public void setCoffeeSpaceId(long coffeeSpaceId) {
		this.coffeeSpaceId = coffeeSpaceId;
	}
}
