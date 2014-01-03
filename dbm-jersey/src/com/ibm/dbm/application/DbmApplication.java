package com.ibm.dbm.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.ibm.dbm.resources.DbmBeanResource;
import com.ibm.dbm.resources.ImageResource;
import com.ibm.dbm.resources.ServerResource;
import com.ibm.dbm.resources.WorkloadResource;

public class DbmApplication extends Application{

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<Class<?>>();
		resources.add(DbmBeanResource.class);
		resources.add(WorkloadResource.class);
		resources.add(ServerResource.class);
		resources.add(ImageResource.class);
		
		return resources;
	}
}
