package com.ibm.dbm.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ibm.dbm.bean.Deployment;
import com.ibm.dbm.bean.Migration;
import com.ibm.dbm.bean.Recycle;
import com.ibm.dbm.bean.Response;
import com.ibm.dbm.bean.Workload;
import com.ibm.dbm.util.WorkloadHelper;

@Path("workloads")
public class WorkloadResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Workload> getWorkloads() {
		List<Workload> workloads = new ArrayList<Workload>();
		
		Workload wd = new Workload();
		wd.setId(1);
		wd.setName("This API isn't supported now!");
	    workloads.add(wd);
		
		return workloads;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addWorkload(Deployment deployment) {
		WorkloadHelper helper = new WorkloadHelper();
		Response response = null;
		
		if (deployment.getType() == Deployment.Type.Logical_Partition) {
			response = helper.deployLogicalPartition(deployment);
		} else if (deployment.getType() == Deployment.Type.Physical_Partition) {
			response = helper.deployPhysicalPartition(deployment);
		} else {
			response = new Response(Response.Status.Failed, "Error input, you shoud indicate your deployment type: 'type'='Logical_Partition' or 'Physical_Partition'.", null);
		}
		
		return response;
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Workload getWorkload(@PathParam("id") int id) {
		System.out.println("GET: " + id);
		
		Workload wd = new Workload();
		wd.setId(1);
		wd.setName("This API isn't supported now!");
		
		return wd;
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateWorkload(@PathParam("id") int id, Workload workload) {
		WorkloadHelper helper = new WorkloadHelper();
		return helper.update(workload);
	}
	
	@DELETE
	@Path("{id}")
	public String deleteWorkload(@PathParam("id") int id) {
		System.out.println("DELETE: " + id);
		
		return null;
	}
	
	@PUT
	@Path("{id}/migration")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response migrate(@PathParam("id") int id, Migration migration) {
		WorkloadHelper helper = new WorkloadHelper();
		Response response = null;
		
		if (migration.getType() == Migration.Type.V2P) {
			response = helper.V2P(migration);
		} else if (migration.getType() == Migration.Type.P2V) {
			response = new Response(Response.Status.Failed, "Error input, only: 'type'='V2P' is support now.", null);
		} else if (migration.getType() == Migration.Type.V2V) {
			response = new Response(Response.Status.Failed, "Error input, only: 'type'='V2P' is support now.", null);
		} else if (migration.getType() == Migration.Type.P2P) {
			response = new Response(Response.Status.Failed, "Error input, only: 'type'='V2P' is support now.", null);
		} else {
			response = new Response(Response.Status.Failed, "Error input, you shoud indicate your migration type: 'type'='V2P' or 'P2V' or 'V2V' or 'P2P'.", null);
		}
		
		return response;
	}
	
	@PUT
	@Path("{id}/recycle")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response recycle(@PathParam("id") int id, Recycle recycle) {
		WorkloadHelper helper = new WorkloadHelper();
		
		return helper.recycle(recycle);
	}
	
}
