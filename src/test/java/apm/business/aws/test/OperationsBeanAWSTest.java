package apm.business.aws.test;
/**
 * 
 */


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import amp.business.aws.OperationsBeanAWS;
import amp.common.api.impl.ToolkitConstants;
import amp.common.api.impl.ToolkitDataProvider;
import amp.jpa.entities.Category;
import junit.framework.TestCase;

/**
 * @author MVEKSLER
 *
 */
public class OperationsBeanAWSTest extends TestCase {

	ToolkitDataProvider cToolkitDataProvider = null;
	/**
	 * @param name
	 */
	public OperationsBeanAWSTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception 
	{
		super.tearDown();
		
		try
		{
			if ( this.cToolkitDataProvider != null )
			{
				this.cToolkitDataProvider.closeDatabaseConnection();
			}
		}
		catch( Exception e)
		{
			fail("testToolkitDataProvider failed:" + e.getStackTrace());
		}
	}

	/**
	 * Test method for {@link amp.common.api.impl.ToolkitDataProvider#ToolkitDataProvider()}.
	 */
	@Ignore 
	@Test
	public void testToolkitDataProvider() 
	{
		try
		{
			
			this.cToolkitDataProvider = new ToolkitDataProvider();
			
			if ( this.cToolkitDataProvider.isLcRes() )
			{
				boolean cRes = this.cToolkitDataProvider.openDatabaseConnection();
				if ( !cRes )
				{
					fail("testToolkitDataProvider failed to open DB connection!");
				}
			}
			
		}
		catch( Exception e)
		{
			fail("testToolkitDataProvider failed:" + e.getStackTrace());
		}
	}

	/**
	 * Test method for {@link amp.common.api.impl.ToolkitDataProvider#ToolkitDataProvider()}.
	 */
	@Ignore 
	@Test
	public void testGetRootDepartments() 
	{
		try
		{
			OperationsBeanAWS cOperationsBeanAWS = new OperationsBeanAWS(true);
    		
			List<Category> cDepartments = cOperationsBeanAWS.getRootCategoriesExt("Amazon");
			
			for( Category cDepartment : cDepartments )
			{
				System.out.println(cDepartment.getName());
			}
		}
		catch( Exception e)
		{
			fail("testToolkitDataProvider failed:" + e.getStackTrace());
		}
	}
	
	/**
	 * Test method for {@link amp.common.api.impl.ToolkitDataProvider#ToolkitDataProvider()}.
	 */
	@Ignore
	@Test
	public void testHandleCategoryNodeLookup() 
	{
		boolean cRes = true;
		
		try
		{
			Map<String, Object> cMethodParams = new HashMap<String, Object>();
		    Map<String, Object> cMethodResults = new HashMap<String, Object>();
		       	 
		    HashMap<String, String> params = new HashMap<String, String>();
		    params.put(ToolkitConstants.BROWSE_NODE_ID_PARAM, "677211011");
		    
		    cMethodParams.put("p1", params);
		    
		    OperationsBeanAWS cOperationsBeanAWS = new OperationsBeanAWS(true);
		    
		    cOperationsBeanAWS.handleCategoryNodeLookup(cMethodParams, cMethodResults);
		    
			if ( !cRes )
			{
				fail("testMappingHandler failed build MappingHandler object!");
			}
		}
		catch( Exception e)
		{
			fail("testToolkitDataProvider failed:" + e.getStackTrace());
		}
	}
	
}
