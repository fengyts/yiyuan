package ng.bayue.mchange.v2.c3p0;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

import javax.naming.Referenceable;
import javax.sql.DataSource;

import com.mchange.v2.beans.BeansUtils;
import com.mchange.v2.c3p0.DriverManagerDataSource;
import com.mchange.v2.c3p0.PooledDataSource;
import com.mchange.v2.c3p0.WrapperConnectionPoolDataSource;
import com.mchange.v2.c3p0.impl.AbstractPoolBackedDataSource;
import com.mchange.v2.log.MLevel;
import com.mchange.v2.log.MLog;
import com.mchange.v2.log.MLogger;

public final class MtComboPooledDataSource extends AbstractPoolBackedDataSource implements PooledDataSource, Serializable, Referenceable
{
    final static MLogger logger = MLog.getLogger( MtComboPooledDataSource.class );
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	final static Set TO_STRING_IGNORE_PROPS = new HashSet( Arrays.asList( new String[] { 
                    "connection",
                    "lastAcquisitionFailureDefaultUser",
                    "lastCheckinFailureDefaultUser",
                    "lastCheckoutFailureDefaultUser",
                    "lastConnectionTestFailureDefaultUser",
                    "lastIdleTestFailureDefaultUser",
                    "logWriter",
                    "loginTimeout",
                    "numBusyConnections",
                    "numBusyConnectionsAllUsers",
                    "numBusyConnectionsDefaultUser",
                    "numConnections",
                    "numConnectionsAllUsers",
                    "numConnectionsDefaultUser",
                    "numFailedCheckinsDefaultUser",
                    "numFailedCheckoutsDefaultUser",
                    "numFailedIdleTestsDefaultUser",
                    "numIdleConnections",
                    "numIdleConnectionsAllUsers",
                    "numIdleConnectionsDefaultUser",
                    "numUnclosedOrphanedConnections",
                    "numUnclosedOrphanedConnectionsAllUsers",
                    "numUnclosedOrphanedConnectionsDefaultUser",
                    "numUserPools",
                    "effectivePropertyCycleDefaultUser",
                    "startTimeMillisDefaultUser",
                    "statementCacheNumCheckedOutDefaultUser",
                    "statementCacheNumCheckedOutStatementsAllUsers",
                    "statementCacheNumConnectionsWithCachedStatementsAllUsers",
                    "statementCacheNumConnectionsWithCachedStatementsDefaultUser",
                    "statementCacheNumStatementsAllUsers",
                    "statementCacheNumStatementsDefaultUser",
                    "threadPoolSize",
                    "threadPoolNumActiveThreads",
                    "threadPoolNumIdleThreads",
                    "threadPoolNumTasksPending",
                    "threadPoolStackTraces",
                    "threadPoolStatus",
                    "overrideDefaultUser",
                    "overrideDefaultPassword",
                    "password",
                    "reference",
                    "upTimeMillisDefaultUser",
                    "user",
                    "userOverridesAsString",
                    "allUsers",
                    "connectionPoolDataSource"
    } ) );

    // not reassigned post-ctor; mutable elements protected by their own locks
    // when (very rarely) necessery, we sync this -> wcpds -> dmds

    // note that serialization of these guys happens via out superclass
    // we just have to make sure they get properly reset on deserialization
    transient DriverManagerDataSource         dmds;
    transient WrapperConnectionPoolDataSource wcpds;
    private AbstractEncryptUtils abstractEncryptUtils = null;

    public MtComboPooledDataSource()
    { this( true ); }

    public MtComboPooledDataSource( boolean autoregister )
    {
        super( autoregister );

        // System.err.println("...Initializing ComboPooledDataSource.");

        dmds  = new DriverManagerDataSource();
        wcpds = new WrapperConnectionPoolDataSource();

        wcpds.setNestedDataSource( dmds );

        try
        { this.setConnectionPoolDataSource( wcpds ); }
        catch (PropertyVetoException e)
        {
            logger.log(MLevel.WARNING, "Hunh??? This can't happen. We haven't set up any listeners to veto the property change yet!", e);
            throw new RuntimeException("Hunh??? This can't happen. We haven't set up any listeners to veto the property change yet! " + e);
        }

        // set things up in case there are future changes to our ConnectionPoolDataSource
        //
        setUpPropertyEvents();
    }

    private void setUpPropertyEvents()
    {
        VetoableChangeListener wcpdsConsistencyEnforcer = new VetoableChangeListener()
        {
            // always called within synchronized mutators of the parent class... needn't explicitly sync here
            public void vetoableChange( PropertyChangeEvent evt ) throws PropertyVetoException
            {
                String propName = evt.getPropertyName();
                Object val = evt.getNewValue();

                if ( "connectionPoolDataSource".equals( propName ) )
                {
                    if (val instanceof WrapperConnectionPoolDataSource)
                    {
                        DataSource nested = (DataSource) ((WrapperConnectionPoolDataSource)val).getNestedDataSource();
                        if (! (nested instanceof DriverManagerDataSource) )
                            throw new PropertyVetoException("ComboPooledDataSource requires that its unpooled DataSource " +
                                            " be set at all times, and that it be a" +
                                            " com.mchange.v2.c3p0.DriverManagerDataSource. Bad: " + nested, evt);
                    }
                    else
                        throw new PropertyVetoException("ComboPooledDataSource requires that its ConnectionPoolDataSource " +
                                        " be set at all times, and that it be a" +
                                        " com.mchange.v2.c3p0.WrapperConnectionPoolDataSource. Bad: " + val, evt);
                }
            }
        };
        this.addVetoableChangeListener( wcpdsConsistencyEnforcer );

        PropertyChangeListener wcpdsStateUpdater = new PropertyChangeListener()
        {
            public void propertyChange( PropertyChangeEvent evt )
            { updateLocalVarsFromCpdsProp(); }
        };
        this.addPropertyChangeListener( wcpdsStateUpdater );
    }

    private void updateLocalVarsFromCpdsProp()
    {
        this.wcpds = (WrapperConnectionPoolDataSource) this.getConnectionPoolDataSource();
        this.dmds  = (DriverManagerDataSource) wcpds.getNestedDataSource();
    }

    public MtComboPooledDataSource(String configName)
    { 
        this();
        initializeNamedConfig( configName );
    }

//  // workaround sun big id #6342411 (in which reflective
//  // access to a public method of a non-public class fails,
//  // even if the non-public class is accessed via a public
//  // subclass)
//  public String getDataSourceName()
//  { return super.getDataSourceName(); }

    // DriverManagerDataSourceProperties  (count: 4)
    public String getDescription()
    { return dmds.getDescription(); }

    public void setDescription( String description )
    { dmds.setDescription( description ); }

    public String getDriverClass()
    { return dmds.getDriverClass(); }

    public void setDriverClass( String driverClass ) throws PropertyVetoException
    { 
        dmds.setDriverClass( driverClass ); 
//      System.err.println("setting driverClass: " + driverClass); 
    }

    public String getJdbcUrl()
    {  
//      System.err.println("getting jdbcUrl: " + dmds.getJdbcUrl()); 
        return dmds.getJdbcUrl(); 
    }

    public void setJdbcUrl( String jdbcUrl )
    { 
        dmds.setJdbcUrl( jdbcUrl ); 
        this.resetPoolManager( false );
//      System.err.println("setting jdbcUrl: " + jdbcUrl + " [dmds@" + C3P0ImplUtils.identityToken( dmds ) + "]"); 
//      if (jdbcUrl == null)
//      new Exception("*** NULL SETTER ***").printStackTrace();
    }

    public Properties getProperties()
    { 
        //System.err.println("getting properties: " + dmds.getProperties()); 
        return dmds.getProperties(); 
    }

    public void setProperties( Properties properties )
    { 
        //System.err.println("setting properties: " + properties); 
        dmds.setProperties( properties ); 
        this.resetPoolManager(false);
    }

    // DriverManagerDataSource "virtual properties" based on properties
    public String getUser()
    { return dmds.getUser(); }

    public void setUser( String user )
    { 
        dmds.setUser( user ); 
        this.resetPoolManager( false );
    }

    public String getPassword()
    { return dmds.getPassword(); }

    public void setPassword( String password )
    { 
    	String description = getDescription();
    	if(description != null && description.trim().length()>0){
    		logger.log(MLevel.INFO, "password is encrypt : "+password);
			abstractEncryptUtils = DesUtils.getInstance(description);
			password = abstractEncryptUtils.decrypt(password);
			logger.log(MLevel.INFO, "password is decrypt : "+password);
		}
    	dmds.setPassword( password );
        this.resetPoolManager( false );
    }

    // WrapperConnectionPoolDataSource properties
    public int getCheckoutTimeout()
    { return wcpds.getCheckoutTimeout(); }

    public void setCheckoutTimeout( int checkoutTimeout )
    { 
        wcpds.setCheckoutTimeout( checkoutTimeout ); 
        this.resetPoolManager( false );
    }

    public int getAcquireIncrement()
    { return wcpds.getAcquireIncrement(); }

    public void setAcquireIncrement( int acquireIncrement )
    { 
        wcpds.setAcquireIncrement( acquireIncrement ); 
        this.resetPoolManager( false );
    }

    public int getAcquireRetryAttempts()
    { return wcpds.getAcquireRetryAttempts(); }

    public void setAcquireRetryAttempts( int acquireRetryAttempts )
    { 
        wcpds.setAcquireRetryAttempts( acquireRetryAttempts ); 
        this.resetPoolManager( false );
    }

    public int getAcquireRetryDelay()
    { return wcpds.getAcquireRetryDelay(); }

    public void setAcquireRetryDelay( int acquireRetryDelay )
    { 
        wcpds.setAcquireRetryDelay( acquireRetryDelay ); 
        this.resetPoolManager( false );
    }

    public boolean isAutoCommitOnClose()
    { return wcpds.isAutoCommitOnClose(); }

    public void setAutoCommitOnClose( boolean autoCommitOnClose )
    { 
        wcpds.setAutoCommitOnClose( autoCommitOnClose ); 
        this.resetPoolManager( false );
    }

    public String getConnectionTesterClassName()
    { return wcpds.getConnectionTesterClassName(); }

    public void setConnectionTesterClassName( String connectionTesterClassName ) throws PropertyVetoException
    { 
        wcpds.setConnectionTesterClassName( connectionTesterClassName ); 
        this.resetPoolManager( false );
    }

    public String getAutomaticTestTable()
    { return wcpds.getAutomaticTestTable(); }

    public void setAutomaticTestTable( String automaticTestTable )
    { 
        wcpds.setAutomaticTestTable( automaticTestTable ); 
        this.resetPoolManager( false );
    }

    public boolean isForceIgnoreUnresolvedTransactions()
    { return wcpds.isForceIgnoreUnresolvedTransactions(); }

    public void setForceIgnoreUnresolvedTransactions( boolean forceIgnoreUnresolvedTransactions )
    { 
        wcpds.setForceIgnoreUnresolvedTransactions( forceIgnoreUnresolvedTransactions ); 
        this.resetPoolManager( false );
    }

    public int getIdleConnectionTestPeriod()
    { return wcpds.getIdleConnectionTestPeriod(); }

    public void setIdleConnectionTestPeriod( int idleConnectionTestPeriod )
    { 
        wcpds.setIdleConnectionTestPeriod( idleConnectionTestPeriod ); 
        this.resetPoolManager( false );
    }

    public int getInitialPoolSize()
    { return wcpds.getInitialPoolSize(); }

    public void setInitialPoolSize( int initialPoolSize )
    { 
        wcpds.setInitialPoolSize( initialPoolSize ); 
        this.resetPoolManager( false );
    }

    public int getMaxIdleTime()
    { return wcpds.getMaxIdleTime(); }

    public void setMaxIdleTime( int maxIdleTime )
    { 
        wcpds.setMaxIdleTime( maxIdleTime ); 
        this.resetPoolManager( false );
    }

    public int getMaxPoolSize()
    { return wcpds.getMaxPoolSize(); }

    public void setMaxPoolSize( int maxPoolSize )
    { 
        wcpds.setMaxPoolSize( maxPoolSize ); 
        this.resetPoolManager( false );
    }

    public int getMaxStatements()
    { return wcpds.getMaxStatements(); }

    public void setMaxStatements( int maxStatements )
    { 
        wcpds.setMaxStatements( maxStatements ); 
        this.resetPoolManager( false );
    }

    public int getMaxStatementsPerConnection()
    { return wcpds.getMaxStatementsPerConnection(); }

    public void setMaxStatementsPerConnection( int maxStatementsPerConnection )
    { 
        wcpds.setMaxStatementsPerConnection( maxStatementsPerConnection ); 
        this.resetPoolManager( false );
    }

    public int getMinPoolSize()
    { return wcpds.getMinPoolSize(); }

    public void setMinPoolSize( int minPoolSize )
    { 
        wcpds.setMinPoolSize( minPoolSize ); 
        this.resetPoolManager( false );
    }

    public String getOverrideDefaultUser()
    { return wcpds.getOverrideDefaultUser(); }

    public void setOverrideDefaultUser(String overrideDefaultUser)
    { 
        wcpds.setOverrideDefaultUser( overrideDefaultUser ); 
        this.resetPoolManager( false );
    }

    public String getOverrideDefaultPassword()
    { return wcpds.getOverrideDefaultPassword(); }

    public void setOverrideDefaultPassword(String overrideDefaultPassword)
    { 
        wcpds.setOverrideDefaultPassword( overrideDefaultPassword ); 
        this.resetPoolManager( false );
    }

    public int getPropertyCycle()
    { return wcpds.getPropertyCycle(); }

    public void setPropertyCycle( int propertyCycle )
    { 
        wcpds.setPropertyCycle( propertyCycle ); 
        this.resetPoolManager( false );
    }

    public boolean isBreakAfterAcquireFailure()
    { return wcpds.isBreakAfterAcquireFailure(); }

    public void setBreakAfterAcquireFailure( boolean breakAfterAcquireFailure )
    { 
        wcpds.setBreakAfterAcquireFailure( breakAfterAcquireFailure ); 
        this.resetPoolManager( false );
    }

    public boolean isTestConnectionOnCheckout()
    { return wcpds.isTestConnectionOnCheckout(); }

    public void setTestConnectionOnCheckout( boolean testConnectionOnCheckout )
    { 
        wcpds.setTestConnectionOnCheckout( testConnectionOnCheckout ); 
        this.resetPoolManager( false );
    }

    public boolean isTestConnectionOnCheckin()
    { return wcpds.isTestConnectionOnCheckin(); }

    public void setTestConnectionOnCheckin( boolean testConnectionOnCheckin )
    { 
        wcpds.setTestConnectionOnCheckin( testConnectionOnCheckin ); 
        this.resetPoolManager( false );
    }

    public boolean isUsesTraditionalReflectiveProxies()
    { return wcpds.isUsesTraditionalReflectiveProxies(); }

    public void setUsesTraditionalReflectiveProxies( boolean usesTraditionalReflectiveProxies )
    { 
        wcpds.setUsesTraditionalReflectiveProxies( usesTraditionalReflectiveProxies ); 
        this.resetPoolManager( false );
    }

    public String getPreferredTestQuery()
    { return wcpds.getPreferredTestQuery(); }

    public void setPreferredTestQuery( String preferredTestQuery )
    { 
        wcpds.setPreferredTestQuery( preferredTestQuery ); 
        this.resetPoolManager( false );
    }

    public String getUserOverridesAsString()
    { return wcpds.getUserOverridesAsString(); }

    public void setUserOverridesAsString( String userOverridesAsString ) throws PropertyVetoException
    { 
        wcpds.setUserOverridesAsString( userOverridesAsString ); 
        this.resetPoolManager( false );
    }

    public int getMaxAdministrativeTaskTime()
    { return wcpds.getMaxAdministrativeTaskTime(); }

    public void setMaxAdministrativeTaskTime( int maxAdministrativeTaskTime )
    { 
        wcpds.setMaxAdministrativeTaskTime( maxAdministrativeTaskTime ); 
        this.resetPoolManager( false );
    }

    public int getMaxIdleTimeExcessConnections()
    { return wcpds.getMaxIdleTimeExcessConnections(); }

    public void setMaxIdleTimeExcessConnections( int maxIdleTimeExcessConnections )
    { 
        wcpds.setMaxIdleTimeExcessConnections( maxIdleTimeExcessConnections ); 
        this.resetPoolManager( false );
    }

    public int getMaxConnectionAge()
    { return wcpds.getMaxConnectionAge(); }

    public void setMaxConnectionAge( int maxConnectionAge )
    { 
        wcpds.setMaxConnectionAge( maxConnectionAge ); 
        this.resetPoolManager( false );
    }

    public String getConnectionCustomizerClassName()
    { return wcpds.getConnectionCustomizerClassName(); }

    public void setConnectionCustomizerClassName( String connectionCustomizerClassName )
    { 
        wcpds.setConnectionCustomizerClassName( connectionCustomizerClassName ); 
        this.resetPoolManager( false );
    }

    public int getUnreturnedConnectionTimeout()
    { return wcpds.getUnreturnedConnectionTimeout(); }

    public void setUnreturnedConnectionTimeout(int unreturnedConnectionTimeout)
    {
        wcpds.setUnreturnedConnectionTimeout( unreturnedConnectionTimeout ); 
        this.resetPoolManager( false );
    }

    public boolean isDebugUnreturnedConnectionStackTraces()
    { return wcpds.isDebugUnreturnedConnectionStackTraces(); }

    public void setDebugUnreturnedConnectionStackTraces(boolean debugUnreturnedConnectionStackTraces)
    {
        wcpds.setDebugUnreturnedConnectionStackTraces( debugUnreturnedConnectionStackTraces ); 
        this.resetPoolManager( false );
    }

    // shared properties (count: 1)
    public String getFactoryClassLocation()
    { return super.getFactoryClassLocation(); }

    public void setFactoryClassLocation( String factoryClassLocation )
    {
        dmds.setFactoryClassLocation( factoryClassLocation );
        wcpds.setFactoryClassLocation( factoryClassLocation );
        super.setFactoryClassLocation( factoryClassLocation );
    }

    public String toString()
    {
        //System.err.println("ComboPooledDataSource.toString()");

        StringBuffer sb = new StringBuffer(512);
        sb.append( this.getClass().getName() );
        sb.append(" [ ");
        try { BeansUtils.appendPropNamesAndValues(sb, this, TO_STRING_IGNORE_PROPS); }
        catch (Exception e)
        { 
            sb.append( e.toString() ); 
            //e.printStackTrace();
        }
        sb.append(" ]");

//      Map userOverrides = wcpds.getUserOverrides();
//      if (userOverrides != null)
//      sb.append("; userOverrides: " + userOverrides.toString());

        return sb.toString();
    }

    // serialization stuff -- set up bound/constrained property event handlers on deserialization
    private static final long serialVersionUID = 1;
    private static final short VERSION = 0x0001;

    private void writeObject( ObjectOutputStream oos ) throws IOException
    {
        oos.writeShort( VERSION );
    }

    private void readObject( ObjectInputStream ois ) throws IOException, ClassNotFoundException
    {
        short version = ois.readShort();
        switch (version)
        {
        case VERSION:
            updateLocalVarsFromCpdsProp();
            setUpPropertyEvents();
            break;
        default:
            throw new IOException("Unsupported Serialized Version: " + version);
        }
    }

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	
}
