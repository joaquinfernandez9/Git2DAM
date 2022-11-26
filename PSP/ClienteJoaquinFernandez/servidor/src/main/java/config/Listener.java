package config;

import dao.dataBase.DataBaseConnectionPool;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener()
public class Listener implements ServletContextListener {
    private final DataBaseConnectionPool pool;

    @Inject
    public Listener(@Named("database") DataBaseConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        pool.close();
    }
}
