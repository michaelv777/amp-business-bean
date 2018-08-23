/**
 * 
 */
package amp.business.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import amp.common.api.impl.ToolkitDataProvider;
import amp.common.api.impl.ToolkitReflection;
import amp.data.handler.base.DataHandlerBase;
import amp.jpa.entities.Category;

/**
 * @author MVEKSLER
 *
 */
public abstract class BusinessBeanBase implements BusinessBeanBaseLocal, BusinessBeanBaseRemote
{
	private static final Logger cLogger = 
			LoggerFactory.getLogger(BusinessBeanBase.class);
	
	protected boolean lcRes = true;
	
	protected DataHandlerBase cDataHandlerBase = null;
	
	protected ToolkitDataProvider cToolkitDataProvider = null;
	//---class variables---------------------------------
   
	private ToolkitReflection iReflection = null;
	//---getters/setters-------------------------------
	
    /**
	 * @return the cToolkitDataProvider
	 */
	public ToolkitDataProvider getcToolkitDataProvider() {
		return cToolkitDataProvider;
	}

	public DataHandlerBase getcDataHandlerBase() {
		return cDataHandlerBase;
	}

	public void setcDataHandlerBase(DataHandlerBase cDataHandlerBase) {
		this.cDataHandlerBase = cDataHandlerBase;
	}

	/**
	 * @param cToolkitDataProvider the cToolkitDataProvider to set
	 */
	public void setcToolkitDataProvider(ToolkitDataProvider cToolkitDataProvider) {
		this.cToolkitDataProvider = cToolkitDataProvider;
	}

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
	
	/**
	 * 
	 */
	public BusinessBeanBase() 
	{
		try
		{
			this.iReflection = new ToolkitReflection();	
		}
		catch( Exception e )
		{
			
		}
	}
	//---------------------------------------------------------------------

	//---------------------------------------------------------------------
	@Override
	public boolean invokeRequestMethod(Class<?> cClassToInvoke, 
									   Object   cObjectToInvoke,
									   String   cRequestMethod, 
									   Map<String, Object> cMethodParams, 
									   Map<String, Object> cMethodResults )
	{
		boolean cRes = true;
		
		try
		{
			Method[] cMethods = cClassToInvoke.getMethods();
			
			for(Method cMethod : cMethods)
			{
	            if(cRequestMethod.equals((cMethod.getName())))
	            {
	            	cMethod.invoke(cObjectToInvoke, cMethodParams, cMethodResults);
	            	
	            	break;
	            }
	        }
			
			return cRes;
		}
		catch( IllegalArgumentException iae )
		{
			cLogger.error("invokeRequestMethod::" + iae.getMessage());
			
			return ( cRes = false );
		}
		catch( InvocationTargetException ite )
		{
			cLogger.error("invokeRequestMethod::" + ite.getMessage());
			
			return ( cRes = false );
		}
		catch( IllegalAccessException  iae )
		{
			cLogger.error("invokeRequestMethod::" + iae.getMessage());
			
			return ( cRes = false );
		}
		catch( SecurityException se )
		{
			cLogger.error("invokeRequestMethod::" + se.getMessage());
			
			return ( cRes = false );
		}
		catch( Exception e )
		{
			cLogger.error("invokeRequestMethod::" + e.getMessage());
			
			return ( cRes = false );
		}
	}
	//---------------------------------------------------------------------
	@Override	
	public boolean invokeRequestMethodExt(Class<?> cClassToInvoke, 
										  Object   cObjectToInvoke,
									      String   cRequestMethod, 
									      Map<String, Object> cMethodParams, 
									      Map<String, Object> cMethodResults )
	{
		boolean cRes = true;
		
		try
		{
			
			Class<?> c = Class.forName(cClassToInvoke.getClass().getName());
			
			Method method = c.getDeclaredMethod(cRequestMethod, Map.class, Map.class);
			
			method.invoke(cObjectToInvoke, cMethodParams, cMethodResults);
			
			return cRes;
		}
		catch( NoSuchMethodException nme )
		{
			cLogger.error("invokeRequestMethod::" + nme.getMessage());
			
			return ( cRes = false );
		}
		catch( InvocationTargetException ite )
		{
			cLogger.error("invokeRequestMethod::" + ite.getMessage());
			
			return ( cRes = false );
		}
		catch( IllegalAccessException  iae )
		{
			cLogger.error("invokeRequestMethod::" + iae.getMessage());
			
			return ( cRes = false );
		}
		catch( ClassNotFoundException cnf )
		{
			cLogger.error("invokeRequestMethod::" + cnf.getMessage());
			
			return ( cRes = false );
		}
		catch( IllegalArgumentException iae )
		{
			cLogger.error("invokeRequestMethod::" + iae.getMessage());
			
			return ( cRes = false );
		}
		catch( SecurityException se )
		{
			cLogger.error("invokeRequestMethod::" + se.getMessage());
			
			return ( cRes = false );
		}
		catch( Exception e )
		{
			cLogger.error("invokeRequestMethod::" + e.getMessage());
			
			return ( cRes = false );
		}
	}
	/*-----------------------------------------------------------------------------------*/
	/**
	 * @param cSourceName
	 * @return
	 */
	@Override
	public List<Category> getRootCategoriesExt(String cSourceName)
	{
		boolean cRes = true;
		
		String  methodName = "";
		
		List<Category> cCategories = new LinkedList<Category>();
		
		try
    	{
    		this.iReflection = new ToolkitReflection();
    		
    		methodName = this.iReflection.getMethodName();
    		
    		if ( null == cSourceName )
    		{
    			cLogger.error(methodName + "::(null == cSourceName)");
    			
    			cRes = false;
    		}
    		
    		if ( cRes )
    		{
    			cCategories = this.cDataHandlerBase.getRootCategories(cSourceName);
    		}
    		//------
    		
    		return cCategories;	 
    	}
    	catch( Exception e)
    	{
    		cLogger.error(methodName + "::" + e.getMessage());
    		
    		this.setLcRes(cRes = false);
    		
    		
    		return new LinkedList<Category>();
    	}
	}
	//---------------------------------------------------------------------
	/**
	 * @param cMethodParams
	 * @param cMethodResults
	 * @return
	 */
	@Override
	public boolean getRootCategories(Map<String, Object> cMethodParams,
			   						 Map<String, Object> cMethodResults)
	{
		boolean cRes = true;
		
		String  methodName = "";
	
		try
    	{
    		this.iReflection = new ToolkitReflection();
    		
    		methodName = this.iReflection.getMethodName();
    				
    		if ( null == cMethodParams )
    		{
    			cLogger.error(methodName + "::cMethodParams is null!");
    			
    			cRes = false;
    		}
    		
    		if ( cRes )
    		{
    			cRes = this.cDataHandlerBase.getRootCategories(cMethodParams, cMethodResults);
    		}
    		
    		return cRes;	 
    	}
    	catch( Exception e)
    	{
    		cLogger.error(methodName + "::" + e.getMessage());
    		
    		this.setLcRes(cRes = false);
    		
    		return cRes;
    	}
	}
	
	//---------------------------------------------------------------------
	/**
	 * @param cMethodParams
	 * @param cMethodResults
	 * @return
	 */
	@Override
	public boolean getRootCategoriesNodes(Map<String, Object> cMethodParams,
			   						 	  Map<String, Object> cMethodResults)
	{
		boolean cRes = true;
		
		String  methodName = "";
	
		try
    	{
    		this.iReflection = new ToolkitReflection();
    		
    		methodName = this.iReflection.getMethodName();
    				
    		if ( null == cMethodParams )
    		{
    			cLogger.error(methodName + "::cMethodParams is null!");
    			
    			cRes = false;
    		}
    		
    		if ( cRes )
    		{
    			cRes = this.cDataHandlerBase.getRootCategoriesNodes(cMethodParams, cMethodResults);
    		}
    		
    		return cRes;	 
    	}
    	catch( Exception e)
    	{
    		cLogger.error(methodName + "::" + e.getMessage());
    		
    		this.setLcRes(cRes = false);
    		
    		return cRes;
    	}
	}
		
	//---------------------------------------------------------------------
	/**
	 * @param cMethodParams
	 * @param cMethodResults
	 * @return
	 */
	@Override
	public boolean getRootCategorySubNodes(Map<String, Object> cMethodParams,
			   						 	   Map<String, Object> cMethodResults)
	{
		boolean cRes = true;
		
		String  methodName = "";
	
		try
    	{
    		this.iReflection = new ToolkitReflection();
    		
    		methodName = this.iReflection.getMethodName();
    				
    		if ( null == cMethodParams )
    		{
    			cLogger.error(methodName + "::cMethodParams is null!");
    			
    			cRes = false;
    		}
    		
    		if ( cRes )
    		{
    			cRes = this.cDataHandlerBase.getRootCategorySubNodes(cMethodParams, cMethodResults);
    		}
    		
    		return cRes;	 
    	}
    	catch( Exception e)
    	{
    		cLogger.error(methodName + "::" + e.getMessage());
    		
    		this.setLcRes(cRes = false);
    		
    		return cRes;
    	}
	}
	//---------------------------------------------------------------------
	@Override
	public boolean setResources(HashMap<String, String> cResources) 
	{
		// TODO Auto-generated method stub
		String  methodName = "";
		
		boolean cRes = true;
		
		try
		{
			this.iReflection = new ToolkitReflection();
    		
    		methodName = this.iReflection.getMethodName();
    		
    		if ( null == cResources)
    		{
    			cLogger.error(methodName + "::null == cResources!");
    			
    			this.setLcRes(cRes = false);
    		}
    		
    		if ( cRes )
    		{
	    		if ( this.cDataHandlerBase.getcToolkitDataProvider() != null && 
	    			 this.cDataHandlerBase.getcToolkitDataProvider().gettSettings() != null )
	    		{
	    			if ( cRes )
	    			{
	    				cRes = this.cDataHandlerBase.getcToolkitDataProvider().setResources(cResources);
	    			}
	    			if ( cRes )
	    			{
	    				cRes = this.cDataHandlerBase.getcToolkitDataProvider().loadSettings();
	    			}
	    			if ( cRes )
	    			{
	    				cRes = this.cDataHandlerBase.getcToolkitDataProvider().initClassObjects();
	    			}
	        		
	    		}
	    		else
	    		{
	    			this.cDataHandlerBase.setcToolkitDataProvider(new ToolkitDataProvider(false));
	    			
	    			if ( cRes )
	    			{
	    				cRes = this.cDataHandlerBase.getcToolkitDataProvider().initSettings(false);
	    			}
	    			if ( cRes )
	    			{
	    				cRes = this.cDataHandlerBase.getcToolkitDataProvider().setResources(cResources);
	    			}
	    			if ( cRes )
	    			{
	    				cRes = this.cDataHandlerBase.getcToolkitDataProvider().loadSettings();
	    			}
	    			if ( cRes )
	    			{
	    				cRes = this.cDataHandlerBase.getcToolkitDataProvider().initClassObjects();
	    			}
	    			
	    		}
	    	}
    		
    		if ( cRes )
    		{
    			this.cToolkitDataProvider = this.cDataHandlerBase.getcToolkitDataProvider();
    		}
    		
			return cRes ;
		}
		catch( Exception e)
		{
			cLogger.error(methodName + "::" + e.getMessage());
    		
    		this.setLcRes(cRes = false);
    		
    		return cRes;
		}
	}
}
