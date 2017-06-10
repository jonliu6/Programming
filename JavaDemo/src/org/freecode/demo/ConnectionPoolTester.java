package org.freecode.demo;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionPoolTester {

    public static void main(String[] args)
    {
        ConnectionPoolTester tester = new ConnectionPoolTester();
        tester.connectToPool();
    }
    
    public void connectToPool()
    {
        Hashtable env = new Hashtable();
        
        DataSource ds = null;
        Context ctx = null;
        
        try
        {
            env.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
            env.put(javax.naming.Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
            env.put(javax.naming.Context.PROVIDER_URL, "jnp://localhost:1099");
            Context anEnvContext = new InitialContext(env);
            DataSource aDataSource = (DataSource) anEnvContext.lookup("java:/jdbc/pact");
            
//            InitialContext ic = new InitialContext();
//            ds = (DataSource) ic.lookup("java:/jdbc/pact");
            System.out.println("datasource is: "+ds);
        }
        catch ( Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
