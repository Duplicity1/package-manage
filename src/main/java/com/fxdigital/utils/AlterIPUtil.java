package com.fxdigital.utils;

import com.fxdigital.bean.IpInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

@Component
public class AlterIPUtil {
	private static Logger log = LoggerFactory.getLogger(AlterIPUtil.class);
	private static final String path="/etc/sysconfig/network-scripts/";
//	private static final String path="F:/";

	/**
	 * 获取需要修改文件的文件路径集合
	 * @return
	 */
	public List<String> getListName() {
		List<String> listName = new LinkedList<String>();
		File file = new File(path);
		if (!file.exists()) {
			return null;
		}
		File f[] = file.listFiles();
		if(f!=null&&f.length>0){
			for (int i = 0; i < f.length; i++) {
				File fs = f[i];
				if (fs.isFile()) {
					if (fs.getName().startsWith("ifcfg")) {
						listName.add(fs.getAbsolutePath());
					}
				}
			}
		}
		return listName;
	}

	String[] marks ={
			"128.0.0.0",
			"192.0.0.0",
			"224.0.0.0",
			"240.0.0.0",
			"248.0.0.0",
			"252.0.0.0",
			"254.0.0.0",
			"255.0.0.0",
			"255.128.0.0",
			"255.192.0.0",
			"255.224.0.0",
			"255.240.0.0",
			"255.248.0.0",
			"255.252.0.0",
			"255.254.0.0",
			"255.255.0.0",
			"255.255.128.0",
			"255.255.192.0",
			"255.255.224.0",
			"255.255.240.0",
			"255.255.248.0",
			"255.255.252.0",
			"255.255.254.0",
			"255.255.255.0",
			"255.255.255.128",
			"255.255.255.192",
			"255.255.255.224",
			"255.255.255.240",
			"255.255.255.248",
			"255.255.255.252",
			"255.255.255.254",
			"255.255.255.255"
	};

	public boolean alterServiceInfo(String type, String content) {
		List<String> listName = getListName();
		int i = 0;
		InputStream is = null;
		FileOutputStream oFile=null;
		if(listName!=null&&listName.size()>0){
			log.info("alterServiceInfo listName:" + listName.size());
			for (String string : listName) {
				Properties prop = new Properties();
				try {
					is = new FileInputStream(string);
					prop.load(is);
					oFile = new FileOutputStream(string);// true表示追加打开
					if(prop!=null){
						String s = prop.getProperty(type);
						if (s != null) {
							if (type.equals("IPADDR") && s.equals("127.0.0.1")) {
								log.info("content: "+content +"  type:: "+type);
							} else {
								prop.setProperty(type, content);
								i++;
							}
						}
						prop.store(oFile, null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if(is!=null){
						try {
							is.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(oFile!=null){
						try {
							oFile.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}
		}
		if (i > 0) {
			return true;
		} else {
			return false;
		}

	}


	public boolean alterServiceIp(String type,String content,String oldIp){
		List<String> listName =getListName();
		int i = 0;
		
		InputStream is = null;
		FileOutputStream oFile=null;
		if(listName!=null&&listName.size()>0){
			log.info("alterServiceIp listName:" + listName.size());
			for (String string : listName) {
				Properties prop = new Properties();
				try {
					is = new FileInputStream(string);
					prop.load(is);
					oFile = new FileOutputStream(string);// true表示追加打开
					String s = prop.getProperty(type);
					if (s != null) {
						if (s.equals(oldIp)){
							log.info("content: " + content + "  type:: " + type +" oldIp:::"+oldIp);
							prop.setProperty(type, content);
						}
					}
					prop.store(oFile, null);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if(is!=null){
						try {
							is.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(oFile!=null){
						try {
							oFile.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}
		}
		if (i > 0) {
			return true;
		} else {
			return false;
		}
		
	}

	public String alterServiceInfo(IpInfo net) {
		if (net.getAddr() == null || net.getMask() == null || net.getGateway() == null) {
			return "FAIL";
		} else {
			log.info("alterServiceInfo111=== net.getOldIp():=="+net.getOldIp()+"==== net.getAddr() :" + net.getAddr() + " net.getNetMask() :"
					+ net.getMask() + " net.getGateway(): " + net.getGateway());
			alterServiceIp("IPADDR", net.getAddr(),net.getOldIp());
			alterServiceInfo("NETMASK", net.getMask());
			alterServiceInfo("GATEWAY", net.getGateway());
			return "OK";
		}
	}

	/**
	 * 判断是否存在掩码前缀
	 */
	public void isExistPrefix(){
		log.info("Enter isExistPrefix()=======>>>>");
		List<String> listName = new ArrayList<String>();
		listName = getListName();
		if(listName!=null&&listName.size()>0){
			for (String string : listName) {
				Properties p= new Properties();
				InputStream ii = null;
				OutputStream out = null;
				try {
					ii = new FileInputStream(string);
					p.load(ii);
					String prefix= p.getProperty("PREFIX");
					String netmask = p.getProperty("NETMASK");
					log.info("prefix--"+prefix+" netmask--"+netmask);
					if(prefix!=null){
						int i = Integer.parseInt(prefix);
						if(netmask==null){
							if(i>0){
								p.setProperty("NETMASK",marks[i-1]);
							}
						}
						p.remove("PREFIX");
						out = new FileOutputStream(string);
						p.store(out,null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					try {
						if(out!=null){
							out.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}

					try {
						if(ii!=null){
							ii.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		}

	}


	/**
	 * 获取服务Ip 子网掩码 网关
	 * 
	 * @return
	 */
	public IpInfo getServiceInfo() {
		isExistPrefix();
		List<String> listName = getListName();
		String ipAddr = null, netMask = null, gateway = null,prefix = null;
		IpInfo net = new IpInfo();
		if(listName!=null&&listName.size()>0){
			log.info("getServiceInfo listName:" + listName.size());
			boolean isExistPrefix = false;
			for (String string : listName) {
				Properties prop = new Properties();
				InputStream is = null;
				try {
					is = new FileInputStream(string);
					prop.load(is);
					ipAddr = prop.getProperty("IPADDR");
					netMask = prop.getProperty("NETMASK");
					gateway = prop.getProperty("GATEWAY");
					log.info("net :" + ipAddr + " net:" + netMask + " net: " + gateway);

					if (ipAddr != null && !ipAddr.equals("127.0.0.1")) {
						net.setAddr(ipAddr);
					}
					if (netMask != null) {
						net.setMask(netMask);
					}
					if (gateway != null) {
						net.setGateway(gateway);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if(is!=null){
						try {
							is.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				}
			}
		}
		log.info(" net.getNetMask() :" + net.getMask() + " net.getGateway(): "
				+ net.getGateway());
		return net;
	}

}
