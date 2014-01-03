package com.ibm.dbm.util;

import java.io.IOException;
import java.io.InputStream;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class RemoteShellInvoke{
	PropertiesSingleton ps = PropertiesSingleton.getInstance();
	
	private Connection conn;
    private String     ip;
    private String     user;
    private String     password;
    private String     charset = "utf-8";
    
    private String command;
    private String directory = ps.getPropertyValue(Constants.SHELL_DIRECTORY);
    private String returnMsg;
    private String errorMsg;
    private int    returnCode;
    
    private static final int TIME_OUT = 1000 * 5 * 60;
    
    public RemoteShellInvoke() {
        this.ip       = ps.getPropertyValue(Constants.SHELL_SERVER_IP);//172.16.66.166   9.125.103.166
        this.user     = ps.getPropertyValue(Constants.SHELL_SERVER_USERNAME);
        this.password = ps.getPropertyValue(Constants.SHELL_SERVER_PASSWORD);
    }
    
    private boolean login() throws IOException {
        conn = new Connection(ip);
        conn.connect();
        return conn.authenticateWithPassword(user, password);
    }
    
    public int exec() throws Exception {
        InputStream stdOut = null;
        InputStream stdErr = null;
        int ret = -1;
        try {
            if (login()){
                // Open a new {@link Session} on this connection
                Session session = conn.openSession();
                // Execute a command on the remote machine.
                session.execCommand("sh " + getDirectory() + getCommand());
                
                stdOut = new StreamGobbler(session.getStdout());
                setReturnMsg(processStream(stdOut, charset));
                
                stdErr = new StreamGobbler(session.getStderr());
                setErrorMsg(processStream(stdErr, charset));
                
                session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);
                
                ret = session.getExitStatus().intValue();
                
                session.close();
            } else {
               // throw new AppException("��¼Զ�̻���ʧ��" + ip);
            }
        }catch(Exception e){
        	e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if(stdOut != null){
            	stdOut.close();
            }
            if(stdErr != null){
            	stdErr.close();
            }
        }
        return ret;
    }
    
    private String processStream(InputStream in, String charset) throws Exception {
    	byte[] buf = new byte[1024];
    	StringBuffer sb = new StringBuffer();
        while (in.read(buf) != -1) {
        	sb.append(new String(buf, charset));
        }
        return sb.toString();
    }

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
