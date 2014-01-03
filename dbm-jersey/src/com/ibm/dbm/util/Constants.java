package com.ibm.dbm.util;

public class Constants {
	public static final String SH_VMC_LS_IMAGE       = "vmc_ls_image.sh";
	
	public static final String SH_LS_IO_ETH          = "ls_io_eth.sh";
	public static final String SH_CHECK_AVAIL_CPU_MEM = "check_avail_sys_proc_mem.sh";
	public static final String SH_CHECK_AVAIL_IO_ETH = "check_avail_io_eth.sh";
	public static final String SH_CHECK_IO_ETH_STATE = "check_io_eth_state.sh";
	
	public static final String SH_LS_IO_HBA          = "ls_io_hba.sh";
	public static final String SH_CHECK_AVAIL_IO_HBA = "check_avail_io_hba.sh";
	public static final String SH_CHECK_IO_HBA_STATE = "check_io_hba_state.sh";
	
	public static final String SH_CHECK_IP           = "check_ip.sh";
	
	public static final String SH_DEPLOY_LPAR = "deploy_vm2.sh"; // deploy virtual partition
	public static final String SH_DEPLOY_PPAR = "deploy_lpar.sh"; // deploy physical partition
	public static final String SH_UPDATE_WORKLOAD = "ch_config.sh";
	
	public static final String SH_MIGRATE_V2P = "v2p.sh";
	
	public static final String SH_RECYCLE = "remove_all2.sh";
	
	//sysparam.properties key:was server information
	public static final String LOG_FROM_DIR        = "LOG_FROM_DIR";
	public static final String LOG_TO_DIR          = "LOG_TO_DIR";
	public static final String WAS_SERVER_IP       = "WAS_SERVER_IP";
	public static final String WAS_SERVER_USERNAME = "WAS_SERVER_USERNAME";
	public static final String WAS_SERVER_PASSWORD = "WAS_SERVER_PASSWORD";
	
	//sysparam.properties key:shell server information
	public static final String SHELL_SERVER_IP       = "SHELL_SERVER_IP";
	public static final String SHELL_SERVER_USERNAME = "SHELL_SERVER_USERNAME";
	public static final String SHELL_SERVER_PASSWORD = "SHELL_SERVER_PASSWORD";
	public static final String SHELL_DIRECTORY       = "SHELL_DIRECTORY";
	
}
