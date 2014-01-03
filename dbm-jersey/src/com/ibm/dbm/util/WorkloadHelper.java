package com.ibm.dbm.util;

import com.ibm.dbm.bean.Deployment;
import com.ibm.dbm.bean.Migration;
import com.ibm.dbm.bean.Recycle;
import com.ibm.dbm.bean.Response;
import com.ibm.dbm.bean.Workload;
import com.ibm.dbm.init.DelployQueueSingleton;
import com.ibm.dbm.init.Operation;
import com.ibm.dbm.init.ShellBean;
import com.ibm.dbm.init.ShellThread;

public class WorkloadHelper {

	public Response deployLogicalPartition(Deployment deployment) {
		String space = " ";
		int deployNumber = (deployment.getDeployNumber() <= 0) ? 1 : deployment.getDeployNumber();
		String command = "";
		ShellBean sb = null;
		DelployQueueSingleton dqs = DelployQueueSingleton.getInstance();
		
		for (int i = 0; i < deployNumber; i ++) {
			command = Constants.SH_DEPLOY_LPAR 
            	+ space + deployment.getServerName()
            	+ space + deployment.getPartitionName().replaceAll(" ", "-") + ((deployNumber > 1) ? i : "")
            	+ space + deployment.getPhysicalCPU() 
            	+ space + deployment.getVirtualCPU() 
            	+ space + deployment.getMemory() 
            	+ space + deployment.getDisk() 
            	+ space + deployment.getNetwork().getIp() 
            	+ space + deployment.getNetwork().getNetmask() 
            	+ space + deployment.getNetwork().getGateway() 
            	+ space + deployment.getImageName() 
            	+ space + deployment.getSvcPool().getSvc_ip() 
            	+ space + deployment.getSvcPool().getName(); // pool name
			System.out.println("Deploy LPAR: command=" + command);
			
			sb = new ShellBean(command, Operation.DEPLOY);
			dqs.add(sb);
		}
		
		return new Response(Response.Status.Successful, "Deployment request has been submitted successfully, and the system is processing. Please wait.", deployment);
	}
	
	public Response deployPhysicalPartition(Deployment deployment) {
		String space = " ";
		int deployNumber = (deployment.getDeployNumber() <= 0) ? 1 : deployment.getDeployNumber();
		String command = "";
		ShellBean sb = null;
		DelployQueueSingleton dqs = DelployQueueSingleton.getInstance();
		
		for (int i = 0; i < deployNumber; i ++) {
			command = Constants.SH_DEPLOY_PPAR 
            	+ space + deployment.getServerName()
            	+ space + deployment.getPartitionName().replaceAll(" ", "-") + ((deployNumber > 1) ? i : "")
            	+ space + deployment.getPhysicalCPU() 
            	+ space + deployment.getVirtualCPU() 
            	+ space + deployment.getMemory() 
            	+ space + deployment.getDisk() 
            	+ space + deployment.getNetwork().getIp() 
            	+ space + deployment.getNetwork().getNetmask() 
            	+ space + deployment.getNetwork().getGateway() 
            	+ space + deployment.getImageName() 
            	+ space + deployment.getSvcPool().getSvc_ip() 
            	+ space + deployment.getSvcPool().getName() // pool name
            	+ space + deployment.getEthCard()
            	+ space + deployment.getHbaCard()
            	+ space + deployment.getHbaWwpn();
			System.out.println("Deploy PPAR: command=" + command);
			
			sb = new ShellBean(command, Operation.DEPLOY);
			dqs.add(sb);
		}
		
		return new Response(Response.Status.Successful, "Deployment request has been submitted successfully, and the system is processing. Please wait.", deployment);
	}
	
	public Response V2P(Migration migration) {
		String space = " ";
		ShellBean sb = null;
		DelployQueueSingleton dqs = DelployQueueSingleton.getInstance();
		
		String hbaString = migration.getHbaCards().get(0);
		for (int i = 1; i < migration.getHbaCards().size(); i ++) {
			hbaString += "+" + migration.getHbaCards().get(i);
		}
		
		String ethString = migration.getEthCards().get(0);
		for (int i = 1; i < migration.getEthCards().size(); i ++) {
			ethString += "+" + migration.getEthCards().get(i);
		}
		
		String command = Constants.SH_MIGRATE_V2P 
    		+ space + migration.getSourceServer()
    		+ space + migration.getTargetServer()
    		+ space + migration.getPartitionName()
    		+ space + migration.getNetwork().getIp() 
    		+ space + migration.getNetwork().getNetmask() 
    		+ space + migration.getNetwork().getGateway() 
    		+ space + migration.getPhysicalCPU() 
    		+ space + migration.getVirtualCPU() 
    		+ space + migration.getMemory()
    		+ space + hbaString
    		+ space + ethString;
		System.out.println("V2P: command=" + command);
	
		sb = new ShellBean(command, Operation.V2P);
		dqs.add(sb);
		
		return new Response(Response.Status.Successful, "Migration:V2P request has been submitted successfully, and the system is processing. Please wait.", migration);
	}
	
	public Response recycle(Recycle recycle) {
		String space = " ";
		
		String command = Constants.SH_RECYCLE
			+ space + recycle.getPartitionName()
			+ space + recycle.getSourceServerName()
			+ space + recycle.getTargetServerName()
			+ space + recycle.getIp()
			+ space + recycle.getStorageIp();
		System.out.println("Recycle: command=" + command);
	
		ShellThread st = new ShellThread(command, Operation.RECYCLE);
		st.start();
		
		return new Response(Response.Status.Successful, "Recycle request has been submitted successfully, and the system is processing. Please wait.", recycle);
	}
	
	public Response update(Workload workload) {
		String space = " ";
		
		String hbaString = workload.getHbaCards().get(0);
		for (int i = 1; i < workload.getHbaCards().size(); i ++) {
			hbaString += "+" + workload.getHbaCards().get(i);
		}
		
		String ethString = workload.getEthCards().get(0);
		for (int i = 1; i < workload.getEthCards().size(); i ++) {
			ethString += "+" + workload.getEthCards().get(i);
		}
		
		
		String command = Constants.SH_UPDATE_WORKLOAD
			+ space + workload.getServerName()
			+ space + workload.getName()
			+ space + workload.getNetwork().getIp()
			+ space + workload.getNetwork().getNetmask()
			+ space + workload.getNetwork().getGateway()
			+ space + workload.getPhysicalCPU()
			+ space + workload.getDisk()
			+ space + workload.getEthCards().size()
			+ space + workload.getHbaCards().size()
			+ space + ethString
			+ space + hbaString;
		System.out.println("Update Workload: command=" + command);
	
		ShellThread st = new ShellThread(command, Operation.UPDATE_WORKLOAD);
		st.start();
		
		return new Response(Response.Status.Successful, "Update workload request has been submitted successfully, and the system is processing. Please wait.", workload);
	}
	
}
