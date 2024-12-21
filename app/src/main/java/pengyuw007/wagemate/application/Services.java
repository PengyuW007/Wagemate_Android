package pengyuw007.wagemate.application;

import pengyuw007.wagemate.persistence.IPersistenceAccess;
import pengyuw007.wagemate.persistence.stub.DataAccessStub;

public class Services {
    private static IPersistenceAccess dataAccessService = null;

    public static IPersistenceAccess createDataAccess(String dbName)
    {
        if (dataAccessService == null)
        {
            dataAccessService = new DataAccessStub(dbName);
            dataAccessService.open(Main.getDBPathName());
        }
        return dataAccessService;
    }

    public static IPersistenceAccess createDataAccess(DataAccessStub alternateDataAccessService)
    {
        if (dataAccessService == null)
        {
            dataAccessService = alternateDataAccessService;
            dataAccessService.open(Main.getDBPathName());
        }
        return dataAccessService;
    }

    public static IPersistenceAccess getDataAccess(String dbName)
    {
        if (dataAccessService == null)
        {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return dataAccessService;
    }

    public static void closeDataAccess()
    {
        if (dataAccessService != null)
        {
            dataAccessService.close();
        }
        dataAccessService = null;
    }
}