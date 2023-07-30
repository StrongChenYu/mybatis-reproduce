package com.source.mybatis.binding.datasource.pooled;

import java.util.ArrayList;
import java.util.List;

public class PoolState {

    protected PooledDataSource pooledDataSource;

    protected final List<PooledConnection> idleConnections = new ArrayList<>();
    protected final List<PooledConnection> activeConnections = new ArrayList<>();

    protected long requestCount = 0;

    // 总共需求的时间
    protected long accumulatedRequestTime = 0;
    // 链接总共取出的时间
    protected long accumulatedCheckoutTime = 0;
    protected long claimedOverdueConnectionCount = 0;
    protected long accumulatedCheckoutTimeOfOverdueConnections = 0;

    // 总等待时间
    protected long accumulatedWaitTime = 0;
    // 要等待的次数
    protected long hadToWaitCount = 0;
    // 失败的链接次数
    protected long badConnectionCount = 0;

    public PoolState(PooledDataSource pooledDataSource) {
        this.pooledDataSource = pooledDataSource;
    }

    public PooledDataSource getPooledDataSource() {
        return pooledDataSource;
    }

    public void setPooledDataSource(PooledDataSource pooledDataSource) {
        this.pooledDataSource = pooledDataSource;
    }

    public List<PooledConnection> getIdleConnections() {
        return idleConnections;
    }

    public List<PooledConnection> getActiveConnections() {
        return activeConnections;
    }

    public long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(long requestCount) {
        this.requestCount = requestCount;
    }

    public long getAccumulatedRequestTime() {
        return accumulatedRequestTime;
    }

    public void setAccumulatedRequestTime(long accumulatedRequestTime) {
        this.accumulatedRequestTime = accumulatedRequestTime;
    }

    public long getAccumulatedCheckoutTime() {
        return accumulatedCheckoutTime;
    }

    public void setAccumulatedCheckoutTime(long accumulatedCheckoutTime) {
        this.accumulatedCheckoutTime = accumulatedCheckoutTime;
    }

    public long getClaimedOverdueConnectionCount() {
        return claimedOverdueConnectionCount;
    }

    public void setClaimedOverdueConnectionCount(long claimedOverdueConnectionCount) {
        this.claimedOverdueConnectionCount = claimedOverdueConnectionCount;
    }

    public long getAccumulatedCheckoutTimeOfOverdueConnections() {
        return accumulatedCheckoutTimeOfOverdueConnections;
    }

    public void setAccumulatedCheckoutTimeOfOverdueConnections(long accumulatedCheckoutTimeOfOverdueConnections) {
        this.accumulatedCheckoutTimeOfOverdueConnections = accumulatedCheckoutTimeOfOverdueConnections;
    }

    public long getAccumulatedWaitTime() {
        return accumulatedWaitTime;
    }

    public void setAccumulatedWaitTime(long accumulatedWaitTime) {
        this.accumulatedWaitTime = accumulatedWaitTime;
    }

    public long getHadToWaitCount() {
        return hadToWaitCount;
    }

    public void setHadToWaitCount(long hadToWaitCount) {
        this.hadToWaitCount = hadToWaitCount;
    }

    public long getBadConnectionCount() {
        return badConnectionCount;
    }

    public void setBadConnectionCount(long badConnectionCount) {
        this.badConnectionCount = badConnectionCount;
    }
}
