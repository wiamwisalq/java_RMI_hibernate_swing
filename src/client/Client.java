package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dao.IDao;
import entity.Machine;

public class Client {
	public static void main(String[] args) {
		try {
			
			final Hashtable<Object, Object> config = new Hashtable<Object, Object>();
			config.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
			config.put(Context.PROVIDER_URL, "rmi://localhost:1099");
			InitialContext context = new InitialContext(config);
		
			IDao<Machine>  stub = (IDao<Machine>) context.lookup("dao");
			System.out.println("Create : ");
			stub.create(new Machine("AZ12", "HP", 3000, new Date()));
			stub.create(new Machine("AZ13", "DELL", 6000, new Date()));
			stub.create(new Machine("AZ14", "HP", 4000, new Date()));
			
			System.out.println("Liste");
			System.out.println(stub.findAll());
			
		} catch (RemoteException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
