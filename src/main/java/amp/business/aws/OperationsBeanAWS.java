package amp.business.aws;

import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.Table;


import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
//import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.impl.SessionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import amp.business.base.BusinessBeanBase;
import amp.common.api.impl.ToolkitReflection;
import amp.data.handler.aws.DataHandlerAWS;
import amp.data.handler.factory.DataHandlerFactory;
import amp.jpa.entities.manager.IJPAEntity;

/**
 * Session Bean implementation class OperationalBeanAWS
 */

@Stateful(name="OperationsBeanAWS", mappedName = "OperationsBeanAWS")
@Local(OperationsBeanLocalAWS.class)
@Remote(OperationsBeanRemoteAWS.class)
public class OperationsBeanAWS extends    BusinessBeanBase
							   implements OperationsBeanRemoteAWS, 
       						   	          OperationsBeanLocalAWS
{
	private static final Logger cLogger = 
			LoggerFactory.getLogger(OperationsBeanAWS.class);
	
	//---class variables---------------------------------
    private ToolkitReflection iReflection = null;
	
    
    //---getters/setters-------------------------------
    /**
	 * @param lcRes the lcRes to set
	 */
	public void setLcRes(boolean lcRes) {
		this.lcRes = lcRes;
	}

	/**
	 * @return the lcRes
	 */
	public boolean isLcRes() {
		return lcRes;
	}
    
	//---class methods---------------------------------
    /**
     * Default constructor. 
     */
    public OperationsBeanAWS() {
        // TODO Auto-generated constructor stub
    	
    }
    
    public OperationsBeanAWS(boolean isInit) 
    {
        
    	boolean cRes = true;
		
		// TODO Auto-generated method stub
		try
    	{
    		if ( isInit )
    		{
    			this.initClassVariables();
    		}
    		
    		this.setLcRes(cRes);		 
    	}
    	catch( Exception e)
    	{
    		
    	}
    }
    /*-------------------------------------------------------------------*/
    @PostConstruct
	private void initClassVariables()
	{
		boolean cRes = true;
		
		String  methodName = "";
		
		// TODO Auto-generated method stub
		try
    	{
    		this.iReflection = new ToolkitReflection();
    		
    		methodName = this.iReflection.getMethodName();
    		
    		//------
    		if ( cRes )
    		{
	    		this.cDataHandlerBase = DataHandlerFactory.getDataHandler(DataHandlerAWS.class);
	    		
	    		if ( null == this.cDataHandlerBase )
	    		{
	    			cLogger.error(methodName + "::this.cE2EDataHandlerBase id null  for E2EDataHandlerRM.class!!");
	    			
	    			cRes = false;
	    		}
    		}
    		//------
    		if ( cRes )
    		{
    			this.cToolkitDataProvider = this.cDataHandlerBase.getcToolkitDataProvider();
	    		
	    		if ( null == this.cToolkitDataProvider )
	    		{
	    			cLogger.error(methodName + "::cToolkitDataProvider is NULL!");
	    			
	    			cRes = false;
	    		}
    		}
    		//------
    		this.setLcRes(cRes);		 
    	}
    	catch( Exception e)
    	{
    		cLogger.error(methodName + "::" + e.getMessage());
    		
    		this.setLcRes(cRes = false);
    	}
	}
    /*-------------------------------------------------------------------*/
    @PreDestroy
	private void freeResources()
	{
		boolean cRes = true;
		
		String  methodName = "";
		
		// TODO Auto-generated method stub
		try
    	{
    		methodName = this.iReflection.getMethodName();
    		
    		
    		this.setLcRes(cRes);		 
    	}
    	catch( Exception e)
    	{
    		cLogger.error(methodName + "::" + e.getMessage());
    		
    		this.setLcRes(cRes = false);	
    	}
	}
    //-------------------------------------------------------------------
    @PrePassivate
    public void beforePassivate () {
    // Called before SFSB is passivated
    }
    
    @PostActivate
    public void afterActivation () {
    // Called before after SFSB is restored
    }
     
    /*
    @Remove
    public void stopSession () {
    // Call to this method signals the container
    // to remove this bean instance and terminates
    // the session. The method body can be empty.
    }
    */

    //-------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see business.configuration.E2EConfigurationBeanBaseI#handleRequest
	 * (java.lang.String,  java.util.Map)
	 */
	@Override
	public Map<String, Object> handleRequest(String cRequestMethod, 
								 			 Map<String, Object> cMethodParams) 
	{
		boolean cRes = true;
		
		String  methodName = "";
		
		Map<String, Object> cMethodResults = new HashMap<String, Object>();
		
		try
    	{
    		methodName = this.iReflection.getMethodName();
    		
    		if ( null == cMethodParams )
    		{
    			cRes = false;
    		}
    		if ( cRes )
    		{
	    		cRes = this.invokeRequestMethod(
	    						this.getClass(), 
	    						this, 
	    						cRequestMethod, 
	    						cMethodParams, 
	    						cMethodResults);
    		
    		}
    		
    		return cMethodResults;	 
    	}
		
    	catch( Exception e)
    	{
    		cLogger.error(methodName + "::" + e.getMessage());
    		
    		this.setLcRes(cRes = false);
    		
    		return cMethodResults;
    	}
	}
	//-------------------------------------------------------------------
	/**
	 * @param cMethodParams
	 * @param cMethodResults
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean getObjectsByType(Map<String, Object> cMethodParams,
							  		Map<String, Object> cMethodResults) 
	{
		boolean cRes = true;
		
		String  methodName = "";
		
		String sqlQuery = "";
		
		SQLQuery queryStmt = null;
				
		Session hbsSession = null;
		
		Transaction tx = null;
		
		List<IJPAEntity> cObjects = null;
		
		try
    	{
			
    		this.iReflection = new ToolkitReflection();
    		
    		methodName = this.iReflection.getMethodName();
    		
    		String cObjectType = (String)cMethodParams.get("OBJECT_TYPE");
    		
    		String cTableName = cObjectType;
    		
    		Class<?> cObjectTypeClazz = null; 
    		
    		if ( null == cObjectType)
    		{
    			this.setLcRes(cRes = false);
    			
    			cLogger.error(methodName + "::null == cMappingFile!");
    		}
    		//------
    		if ( cRes )
    		{
    			cObjectTypeClazz = Class.forName("amp.jpa.entities." + cObjectType);
    			
    			Annotation[] cObjectTypeClazzAnnotations = cObjectTypeClazz.getDeclaredAnnotations();
    			
    			for( Annotation cAnnotation : cObjectTypeClazzAnnotations )
    			{
    				if ( cAnnotation instanceof Table)
    				{
    					Table cAnnotationTable = (Table) cAnnotation; 
    					
    					cTableName = cAnnotationTable.name();
    				}
    			}
    		}
    		//------
    		
    		//------
    		if ( cRes )
    		{
	    		if ( null == this.cToolkitDataProvider )
	    		{
	    			cLogger.error(methodName + "::cToolkitDataProvider is NULL for the Method:" + methodName);
	    			
	    			cRes = false;
	    		}
    		}
    		//------
    		if ( cRes )
    		{
    			Vector<String> repParams = new Vector<String>();
    			
    			repParams.add(cTableName);
    			
    			sqlQuery = cToolkitDataProvider.gettSQL().getSqlQueryByFunctionName(methodName, repParams);
    			
    			if ( null == sqlQuery )
        		{
        			cLogger.error(methodName + "::sqlQuery is NULL for the Method:" + methodName);
        			
        			cRes = false;
        		}
    		}
    		//------
    		if ( cRes )
    		{
    			hbsSession = this.cToolkitDataProvider.gettDatabase().getHbsSessions().openSession();
    			
    			tx = hbsSession.beginTransaction();
    			
    			queryStmt = hbsSession.createSQLQuery(sqlQuery).addEntity(cObjectTypeClazz);
    			
    			cObjects = (List<IJPAEntity>)queryStmt.list();
    		}
    		//------
    		if ( cObjects != null )
    		{
    			List<String> cObjectsValues = new ArrayList<String>();
    			
    			for ( IJPAEntity  cObject : cObjects )
    			{
    				cObjectsValues.add(cObject.getJPAEntityName());
    			}
    			
     			cMethodResults.put(cObjectType, cObjectsValues);
    		}
    		//------
    		
    		if ( tx != null )
			{
				tx.commit();
			}
    		
    		return cRes;	 
    	}
		catch( ClassNotFoundException cnf )
		{
			cLogger.error(methodName + "::" + cnf.getMessage());
    		
    		this.setLcRes(cRes = false);
    		
    		return cRes;
		}
    	catch( Exception e)
    	{
    		cLogger.error(methodName + "::" + e.getMessage());
    		
    		this.setLcRes(cRes = false);
    		
    		if ( tx != null )
    		{
    			tx.rollback();
    		}
    		
    		return cRes;
    	}
		finally
		{
			
			if ( hbsSession != null )
    		{
    			hbsSession.close();
    		}
		}
	}
	/*-----------------------------------------------------------------------------------*/
	/**
	 * @param cMethodParams
	 * @param cMethodResults
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean getObjectsByFilter(Map<String, Object> cMethodParams,
							  		  Map<String, Object> cMethodResults) 
	{
		boolean cRes = true;
		
		String  methodName = "";
		
		String sqlQuery = "";
		
		SQLQuery queryStmt = null;
				
		Session hbsSession = null;
		
		Transaction tx = null;
		
		List<IJPAEntity> cObjects = null;
		
		try
    	{
			
    		this.iReflection = new ToolkitReflection();
    		
    		methodName = this.iReflection.getMethodName();
    		
    		String cFilter     = (String)cMethodParams.get("FILTER");
    		String cObjectType = (String)cMethodParams.get("OBJECT_TYPE");
    		
    		Class<?> cObjectTypeClazz = null; 
    		
    		if ( null == cFilter)
    		{
    			this.setLcRes(cRes = false);
    			
    			cLogger.error(methodName + "::null == cFilter!");
    		}
    		//------
    		if ( cRes )
    		{
    			cObjectTypeClazz = Class.forName("amp.jpa.entities." + cObjectType);
    			
    			Annotation[] cObjectTypeClazzAnnotations = cObjectTypeClazz.getDeclaredAnnotations();
    			
    			for( Annotation cAnnotation : cObjectTypeClazzAnnotations )
    			{
    				if ( cAnnotation instanceof Table)
    				{
    					Table cAnnotationTable = (Table) cAnnotation; 
    					
    					cObjectType = cAnnotationTable.name();
    				}
    			}
    		}
    		//------
    		
    		//------
    		if ( cRes )
    		{
	    		if ( null == cToolkitDataProvider )
	    		{
	    			cLogger.error(methodName + "::cToolkitDataProvider is NULL for the Method:" + methodName);
	    			
	    			cRes = false;
	    		}
    		}
    		//------
    		if ( cRes )
    		{
    			Vector<String> repParams = new Vector<String>();
    			
    			repParams.add(cObjectType);
    			
    			sqlQuery = cToolkitDataProvider.gettSQL().getSqlQueryByFunctionName(methodName, repParams);
    			
    			if ( null == sqlQuery )
        		{
        			cLogger.error(methodName + "::sqlQuery is NULL for the Method:" + methodName);
        			
        			cRes = false;
        		}
    		}
    		//------
    		if ( cRes )
    		{
    			hbsSession = cToolkitDataProvider.gettDatabase().getHbsSessions().openSession();
    			
    			tx = hbsSession.beginTransaction();
    			
    			queryStmt = hbsSession.createSQLQuery(sqlQuery).addEntity(cObjectTypeClazz);
    			
    			cObjects = (List<IJPAEntity>)queryStmt.list();
    		}
    		//------
    		if ( cObjects != null )
    		{
    			List<String> cValues = new ArrayList<String>();
    			
    			for ( IJPAEntity  cObject : cObjects )
    			{
    				cValues.add(cObject.getJPAEntityName());
    			}
    			
     			cMethodResults.put(cObjectType, cValues);
    		}
    		//------
    		
    		if ( tx != null )
			{
				tx.commit();
			}
    		
    		return cRes;	 
    	}
		catch( ClassNotFoundException cnf )
		{
			cLogger.error(methodName + "::" + cnf.getMessage());
    		
    		this.setLcRes(cRes = false);
    		
    		return cRes;
		}
    	catch( Exception e)
    	{
    		cLogger.error(methodName + "::" + e.getMessage());
    		
    		this.setLcRes(cRes = false);
    		
    		if ( tx != null )
    		{
    			tx.rollback();
    		}
    		
    		return cRes;
    	}
		finally
		{
			
			if ( hbsSession != null )
    		{
    			hbsSession.close();
    		}
		}
	}
	/*-----------------------------------------------------------------------------------*/
	
	/**
	 * @param cMethodParams
	 * @param cMethodResults
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean getObjectsNamesExt(Map<String, Object> cMethodParams,
						   			  Map<String, Object> cMethodResults) 
	{
		boolean cRes = true;
		
		String  methodName = "";
		
		String sqlQuery = "";
		
		SQLQuery queryStmt = null;
				
		Session hbsSession = null;
		
		Transaction tx = null;
		
		List<IJPAEntity> cObjects = null;
		
		try
    	{
    		this.iReflection = new ToolkitReflection();
    		
    		methodName = this.iReflection.getMethodName();
    		
    		String cObjectType = (String)cMethodParams.get("OBJECT_TYPE");
    		String cFunctionName = (String)cMethodParams.get("FUNCTION");
    		Vector<String> repParams = (Vector<String>)cMethodParams.get("PARAMS"); 
    		
    		if ( (null == cObjectType) || (null == cFunctionName) || (null == repParams))
    		{
    			cLogger.error(methodName + "::(null == cTableName) || " +
    									   "(null == cFunctionName) || (null == repParams):" + methodName);
    		}
    		
    		Class<?> cObjectTypeClazz = null; 
    		
    		//------
    		if ( cRes )
    		{
    			cObjectTypeClazz = Class.forName("amp.jpa.entities." + cObjectType);
    			
    			Annotation[] cObjectTypeClazzAnnotations = cObjectTypeClazz.getDeclaredAnnotations();
    			
    			for( Annotation cAnnotation : cObjectTypeClazzAnnotations )
    			{
    				if ( cAnnotation instanceof Table)
    				{
    					Table cAnnotationTable = (Table) cAnnotation; 
    					
    					cObjectType = cAnnotationTable.name();
    				}
    			}
    		}
    		//------
    		
    		//------
    		if ( cRes )
    		{
	    		if ( null == this.cToolkitDataProvider )
	    		{
	    			cLogger.error(methodName + "::cToolkitDataProvider is NULL for the Method:" + methodName);
	    			
	    			cRes = false;
	    		}
    		}
    		//------
    		if ( cRes )
    		{
    			sqlQuery = this.cToolkitDataProvider.gettSQL().getSqlQueryByFunctionName(cFunctionName, repParams);
    			
    			if ( null == sqlQuery )
        		{
        			cLogger.error(methodName + "::sqlQuery is NULL for the Method:" + methodName);
        			
        			cRes = false;
        		}
    		}
    		//------
    		if ( cRes )
    		{
    			hbsSession = cToolkitDataProvider.gettDatabase().getHbsSessions().openSession();
    			
    			tx = hbsSession.beginTransaction();
    			
    			queryStmt = hbsSession.createSQLQuery(sqlQuery).addEntity(cObjectTypeClazz);
    			
    			cObjects = (List<IJPAEntity>)queryStmt.list();
    		}
    		//------
    		if ( cObjects != null )
    		{
    			List<String> cValues = new ArrayList<String>();
    			
    			for ( IJPAEntity  cObject : cObjects )
    			{
    				cValues.add(cObject.getJPAEntityName());
    			}
    			
     			cMethodResults.put(cObjectType, cValues);
    		}
    		//------
    		
    		if ( tx != null )
			{
				tx.commit();
			}
    		
    		return cRes;	 
    	}
		catch( ClassNotFoundException cnf )
		{
			cLogger.error(methodName + "::" + cnf.getMessage());
    		
    		this.setLcRes(cRes = false);
    		
    		return cRes;
		}
    	catch( Exception e)
    	{
    		cLogger.error(methodName + "::" + e.getMessage());
    		
    		this.setLcRes(cRes = false);
    		
    		if ( tx != null )
    		{
    			tx.rollback();
    		}
    		
    		return cRes;
    	}
		finally
		{
			
			if ( hbsSession != null )
    		{
    			hbsSession.close();
    		}
		}
	}
	
	/*-----------------------------------------------------------------------------------*/
	/**
	 * @param cMethodParams
	 * @param cMethodResults
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean getObjectsNames(Map<String, Object> cMethodParams,
			   				  	  Map<String, Object> cMethodResults)
	{
		boolean cRes = true;
		
		String  methodName = "";
		
		String sqlQuery = "";
		
		Session hbsSession = null;
		
		Transaction tx = null;
		
		Statement st = null;
		
		ResultSet rs = null;
		
		try
    	{
    		this.iReflection = new ToolkitReflection();
    		
    		methodName = this.iReflection.getMethodName();
    		
    		String cObjectType    = (String)cMethodParams.get("OBJECT_TYPE");
    		String cFunctionName = (String)cMethodParams.get("FUNCTION");
    		Vector<String> repParams =  (Vector<String>)cMethodParams.get("PARAMS"); 
    		
    		if ( (null == cObjectType) || (null == cFunctionName) || (null == repParams))
    		{
    			cLogger.error(methodName + "::(null == cTableName) || " +
    									   "(null == cFunctionName) || (null == repParams):" + methodName);
    		}
    		
    		Class<?> cObjectTypeClazz = null; 
    		
    		//------
    		if ( cRes )
    		{
    			cObjectTypeClazz = Class.forName("amp.jpa.entities." + cObjectType);
    			
    			Annotation[] cObjectTypeClazzAnnotations = cObjectTypeClazz.getDeclaredAnnotations();
    			
    			for( Annotation cAnnotation : cObjectTypeClazzAnnotations )
    			{
    				if ( cAnnotation instanceof Table)
    				{
    					Table cAnnotationTable = (Table) cAnnotation; 
    					
    					cObjectType = cAnnotationTable.name();
    				}
    			}
    		}
    		//------
    		
    		//------
    		if ( cRes )
    		{
	    		if ( null == this.cToolkitDataProvider )
	    		{
	    			cLogger.error(methodName + "::cToolkitDataProvider is NULL for the Method:" + methodName);
	    			
	    			cRes = false;
	    		}
    		}
    		//------
    		if ( cRes )
    		{
    			sqlQuery = this.cToolkitDataProvider.gettSQL().getSqlQueryByFunctionName(cFunctionName, repParams);
    			
    			if ( null == sqlQuery )
        		{
        			cLogger.error(methodName + "::sqlQuery is NULL for the Method:" + methodName);
        			
        			cRes = false;
        		}
    		}
    		//------
    		if ( cRes )
    		{
    			hbsSession = cToolkitDataProvider.gettDatabase().getHbsSessions().openSession();
    			
    			tx = hbsSession.beginTransaction();
    				
    			/*
    			SessionFactoryImplementor sessionFactoryImplementation = 
    					(SessionFactoryImplementor) hbsSession.getSessionFactory();
    			
    			
    		    ConnectionProvider connectionProvider = 
    		    		sessionFactoryImplementation.getConnectionProvider();
    		    
    		    Connection conn = connectionProvider.getConnection();
    			*/
    		    
    			
    		   /*	
    		   Connection conn = hbsSession.getSessionFactory().getSessionFactoryOptions().
    		    		getServiceRegistry().getService(ConnectionProvider.class).getConnection();
    		    
    			*/
    			
    			SessionImpl sessionImpl = (SessionImpl) hbsSession;
    			Connection conn = sessionImpl.connection();
    			 
    			
    		    st = conn.createStatement();
    			
    		    cLogger.debug("M.V. Custom:: " + sqlQuery);
    			
        		rs = st.executeQuery(sqlQuery);

        		List<String> cValues = new ArrayList<String>();
        		
    			while (rs.next()) 
    			{
    				String cObjectName = rs.getString("NAME");
    				
    				cValues.add(cObjectName);
    			}
    			
    			cMethodResults.put(cObjectType, cValues);
    		}
    		//------
    		
    		if ( tx != null )
			{
				tx.commit();
			}
    		
    		return cRes;	 
    	}
		catch( ClassNotFoundException cnf )
		{
			cLogger.error(methodName + "::" + cnf.getMessage());
    		
    		this.setLcRes(cRes = false);
    		
    		return cRes;
		}
    	catch( Exception e)
    	{
    		cLogger.error(methodName + "::" + e.getMessage());
    		
    		this.setLcRes(cRes = false);
    		
    		if ( tx != null )
    		{
    			tx.rollback();
    		}
    		
    		return cRes;
    	}
		finally
		{
			if ( rs != null ) 
			{

				try 
				{ rs.close(); }
				catch( SQLException e )
				{cLogger.warn("Result set could not be closed: " + e.getMessage());}
			}

			if ( st != null ) {

				try 
				{ st.close(); }
				catch( SQLException e ) 
				{cLogger.warn("Statement could not be closed: " + e.getMessage());}
			}
			
			
			if ( hbsSession != null )
    		{
    			hbsSession.close();
    		}
		}
	}
	
	/*-----------------------------------------------------------------------------------*/
	@Override
	public boolean handleCategoryNodeLookup(Map<String, Object> cMethodParams,
			  	  				       	    Map<String, Object> cMethodResults)
    {
    	boolean cRes = true;
    	
    	String cMethodName = "";
    	
    	try
    	{
    		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        //--------
	        if ( null == cMethodParams )
	        {
	        	cLogger.error(cMethodName + "::cMethodParams is null. "
						  + "Check Map<String, Object> cMethodParams parameter!");
	        	
	        	cRes = false;
	        }
	        
	        //--------
	        if ( cRes )
	        {
	        	cRes = this.cDataHandlerBase.handleNodeLookup(cMethodParams, cMethodResults);
	        }
	        
	        cLogger.info("------------------");
	        
	        return cRes;
    	}
    	catch( Exception e)
    	{
    		cLogger.error(cMethodName + "::Exception:" + e.getMessage());
    		
    		return cRes;
    	}
    }
	
	/*-----------------------------------------------------------------------------------*/
	@Override
	public boolean handleItemSearchList(Map<String, Object> cMethodParams,
			  	  				        Map<String, Object> cMethodResults)
	{
		boolean cRes = true;
		
		String cMethodName = "";
		
		try
		{
			StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        //--------
	        if ( null == cMethodParams )
	        {
	        	cLogger.error(cMethodName + "::cMethodParams is null. "
						  + "Check Map<String, Object> cMethodParams parameter!");
	        	
	        	cRes = false;
	        }
	        
	        //--------
	        if ( cRes )
	        {
	        	cRes = this.cDataHandlerBase.handleItemSearchList(cMethodParams, cMethodResults);
	        }
	        
	        cLogger.info("------------------");
	        
	        return cRes;
		}
		catch( Exception e)
		{
			cLogger.error(cMethodName + "::Exception:" + e.getMessage());
			
			return cRes;
		}
	}

	/*-----------------------------------------------------------------------------------*/
	@Override
	public boolean getCategoryNodeLookup(Map<String, Object> cMethodParams,
			  	  				       	 Map<String, Object> cMethodResults)
    {
    	boolean cRes = true;
    	
    	String cMethodName = "";
    	
    	try
    	{
    		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        //--------
	        if ( null == cMethodParams )
	        {
	        	cLogger.error(cMethodName + "::cMethodParams is null. "
						  + "Check Map<String, Object> cMethodParams parameter!");
	        	
	        	cRes = false;
	        }
	        
	        //--------
	        if ( cRes )
	        {
	        	cRes = this.cDataHandlerBase.getNodeLookup(cMethodParams, cMethodResults);
	        }
	        
	        cLogger.info("------------------");
	        
	        return cRes;
    	}
    	catch( Exception e)
    	{
    		cLogger.error(cMethodName + "::Exception:" + e.getMessage());
    		
    		return cRes;
    	}
    }
	@Override
	public boolean getItemSearchList(Map<String, Object> cMethodParams, 
									 Map<String, Object> cMethodResults) 
	{
		
		boolean cRes = true;
    	
    	String cMethodName = "";
    	
    	try
    	{
    		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	        StackTraceElement ste = stacktrace[1];
	        cMethodName = ste.getMethodName();
	        
	        //--------
	        if ( null == cMethodParams )
	        {
	        	cLogger.error(cMethodName + "::cMethodParams is null. "
						  + "Check Map<String, Object> cMethodParams parameter!");
	        	
	        	cRes = false;
	        }
	        
	        //--------
	        if ( cRes )
	        {
	        	cRes = this.cDataHandlerBase.getItemSearchList(cMethodParams, cMethodResults);
	        }
	        
	        cLogger.info("------------------");
	        
	        return cRes;
    	}
    	catch( Exception e)
    	{
    		cLogger.error(cMethodName + "::Exception:" + e.getMessage());
    		
    		return cRes;
    	}
	}
}
