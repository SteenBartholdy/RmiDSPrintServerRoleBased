/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ds.rmids;

/**
 *
 * @author Anders, Steen & Christoffer
 */
public class Access {
    
    private boolean print, queue, topQueue, start, stop, restart, status, readConfig, setConfig;
    
    public Access(boolean print, boolean queue, boolean topQueue, boolean start, boolean stop, boolean restart, boolean status, boolean readConfig, boolean setConfig)
    {
        this.print = print;
        this.queue = queue;
        this.topQueue = topQueue;
        this.start = start;
        this.stop = stop;
        this.restart = restart;
        this.status = status;
        this.readConfig = readConfig;
        this.setConfig = setConfig; 
    }

    public boolean isPrint() {
        return print;
    }

    public void setPrint(boolean print) {
        this.print = print;
    }

    public boolean isQueue() {
        return queue;
    }

    public void setQueue(boolean queue) {
        this.queue = queue;
    }

    public boolean isTopQueue() {
        return topQueue;
    }

    public void setTopQueue(boolean topQueue) {
        this.topQueue = topQueue;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public boolean isRestart() {
        return restart;
    }

    public void setRestart(boolean restart) {
        this.restart = restart;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isReadConfig() {
        return readConfig;
    }

    public void setReadConfig(boolean readConfig) {
        this.readConfig = readConfig;
    }

    public boolean isSetConfig() {
        return setConfig;
    }

    public void setSetConfig(boolean setConfig) {
        this.setConfig = setConfig;
    }
    
}
