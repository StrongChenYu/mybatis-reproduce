package com.source.mybatis.binding.datasource.pooled;

import com.source.mybatis.binding.datasource.unpooled.UnPooledDataSource;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PooledDataSource extends UnPooledDataSource implements DataSource {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(PooledDataSource.class);

    private final PoolState poolState = new PoolState(this);

    protected int poolMaximumActiveConnections = 10;
    protected int poolMaximumIdleConnections = 5;
    protected int poolMaximumCheckoutTime = 20000;
    protected int poolTimeToWait = 20000;
    protected String poolPingQuery = "NO PING QUERY SET";
    protected int poolPingConnectionsNotUsedFor = 0;
    private int expectedConnectionTypeCode;
    

    public void pushConnection(PooledConnection connection) throws SQLException {
        synchronized (poolState) {
            poolState.activeConnections.remove(connection);
            if (!connection.isValid()) {
                // 连接不合法
                logger.info("A bad connection (" + connection.getRealHashCode() + ") attempted to return to the pool, discarding connection.");
                poolState.badConnectionCount++;
                return;
            }

            if (poolState.idleConnections.size() < poolMaximumIdleConnections && connection.getConnectionTypeCode() == expectedConnectionTypeCode) {
                poolState.accumulatedCheckoutTime += connection.getCheckoutTime();

                if (!connection.getRealConnection().getAutoCommit()) {
                    connection.getRealConnection().rollback();
                }

                PooledConnection newConnection = new PooledConnection(connection.getRealConnection(), this);
                poolState.idleConnections.add(newConnection);
                newConnection.setCreatedTimestamp(connection.getCreatedTimestamp());
                newConnection.setLastUsedTimestamp(connection.getLastUsedTimestamp());
                connection.invalidate();

                logger.info("Returned connection " + newConnection.getRealHashCode() + " to pool.");

                poolState.notifyAll();
            } else {
                // idle中的连接很多，我直接关了
                poolState.accumulatedCheckoutTime += connection.getCheckoutTime();
                if (!connection.getRealConnection().getAutoCommit()) {
                    connection.getRealConnection().rollback();
                }
                connection.getRealConnection().close();
                logger.info("Closed connection " + connection.getRealHashCode() + ".");
                connection.invalidate();
            }
        }
    }

    public PooledConnection popConnection() throws SQLException {
        boolean countedWait = false;
        PooledConnection conn = null;
        long t = System.currentTimeMillis();
        long localBadConnectionCount = 0;

        while (conn == null) {
            synchronized (poolState) {
                if (!poolState.idleConnections.isEmpty()) {
                    conn = poolState.idleConnections.remove(0);
                    logger.info("Check out connection " + conn.getRealHashCode() + " from pool.");
                } else {
                    if (poolState.idleConnections.size() < poolMaximumActiveConnections) {
                        conn = new PooledConnection(super.getConnection(), this);
                        logger.info("Create connection " + conn.getRealHashCode() + ".");
                    } else {
                        // 从active的链接里面关
                        // 如果超时了 就把你回滚了关了 然后交给新的
                        // checkout time就是拿出来的时间
                        PooledConnection oldestActiveConnection = poolState.activeConnections.get(0);
                        // 最长可以被拿出来的时间
                        long longestCheckoutTime = oldestActiveConnection.getCheckoutTime();

                        if (longestCheckoutTime > poolMaximumCheckoutTime) {
                            poolState.claimedOverdueConnectionCount++;
                            poolState.accumulatedCheckoutTimeOfOverdueConnections += longestCheckoutTime;
                            poolState.accumulatedCheckoutTime += longestCheckoutTime;
                            poolState.activeConnections.remove(oldestActiveConnection);

                            if (!oldestActiveConnection.getRealConnection().getAutoCommit()) {
                                oldestActiveConnection.getRealConnection().rollback();
                            }

                            conn = new PooledConnection(oldestActiveConnection.getRealConnection(), this);
                            oldestActiveConnection.invalidate();
                            logger.info("Claimed overdue connection " + conn.getRealHashCode() + ".");
                        } else {
                            try {
                                if (!countedWait) {
                                    poolState.hadToWaitCount++;
                                    countedWait = true;
                                }

                                logger.info("Waiting as long as " + poolTimeToWait + " milliseconds for connection.");
                                long wt = System.currentTimeMillis();
                                poolState.wait(poolTimeToWait);
                                poolState.accumulatedWaitTime += System.currentTimeMillis() - wt;
                            } catch (InterruptedException e) {
                                break;
                            }
                        }
                    }
                }

                if (conn != null) {
                    if (conn.isValid()) {
                        if (!conn.getRealConnection().getAutoCommit()) {
                            conn.getRealConnection().rollback();
                        }
                        conn.setConnectionTypeCode(assembleConnectionTypeCode(url, username, password));
                        conn.setCheckoutTimestamp(System.currentTimeMillis());
                        conn.setLastUsedTimestamp(System.currentTimeMillis());
                        poolState.activeConnections.add(conn);
                        poolState.requestCount++;
                        poolState.accumulatedRequestTime += System.currentTimeMillis() - t;
                    } else {
                        logger.info("A bad connection (" + conn.getRealHashCode() + ") was returned from the pool, getting another connection.");
                        poolState.badConnectionCount++;
                        localBadConnectionCount++;
                        conn = null;
                        if (localBadConnectionCount > (poolMaximumIdleConnections + 3)) {
                            logger.debug("PooledDataSource: Could not get a good connection to the database.");
                            throw new SQLException("PooledDataSource: Could not get a good connection to the database.");
                        }
                    }
                }
            }
        }

        if (conn == null) {
            logger.debug("PooledDataSource: Unknown severe error condition.  The connection pool returned a null connection.");
            throw new SQLException("PooledDataSource: Unknown severe error condition.  The connection pool returned a null connection.");
        }

        return conn;
    }

    private int assembleConnectionTypeCode(String url, String username, String password) {
        return (url + username + password).hashCode();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    @Override
    public void setDriver(String driver) {
        super.setDriver(driver);
    }

    @Override
    public void setUrl(String url) {
        super.setUrl(url);
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public void setAutoCommit(Boolean autoCommit) {
        super.setAutoCommit(autoCommit);
    }

    @Override
    public void setTransactionIsolationLevel(Integer transactionIsolationLevel) {
        super.setTransactionIsolationLevel(transactionIsolationLevel);
    }

}
