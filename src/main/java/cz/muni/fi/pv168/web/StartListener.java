package cz.muni.fi.pv168.web;


import cz.muni.fi.pv168.common.DBUtils;
import cz.muni.fi.pv168.model.CrewmanManagerImpl;
import org.apache.derby.jdbc.EmbeddedDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebListener
public class StartListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent ev){
        try {
            ServletContext servletContext = ev.getServletContext();
            DataSource dataSource = prepareDataSource();
            servletContext.setAttribute("crewmanManager", new CrewmanManagerImpl(dataSource));
        } catch (SQLException | IOException ex) {
            throw new RuntimeException();
        }
    }


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private static DataSource prepareDataSource() throws SQLException, IOException {
        EmbeddedDataSource ds = new EmbeddedDataSource();
        ds.setDatabaseName("memory:shipmanager");
        ds.setCreateDatabase("create");
        DBUtils.executeSqlScript(ds, ClassLoader.class.getResourceAsStream("/createTables.sql"));
        return ds;
    }
}
