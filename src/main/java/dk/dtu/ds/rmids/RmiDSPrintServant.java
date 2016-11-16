
package dk.dtu.ds.rmids;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anders, Steen & Christoffer
 */
public class RmiDSPrintServant extends UnicastRemoteObject implements RmiDSPrintService
{
    
    AESCrypto aes = new AESCrypto("ljksdf9342kjdfs9");
    Hash shaHash = new Hash();
    UsersRole userRole;
    Roles role;
    Access access;
    String username = "";
    String password = "";
    
    public RmiDSPrintServant() throws RemoteException
    {
        super();
    }
    
    @Override
    public String startUp(String input) throws RemoteException
    {
        return "From PrintServer: " + input;
    }
    
    @Override
    public String print(String filename, String printer) throws RemoteException
    {
        if (access != null && access.isPrint() == true)
        {
            writeLogfile("Print");
            return "Filename: " + filename + " was printed on: " + "Printername: " + printer;
        }
        else
        {            
            return "Access Denied";
        }
    }
    
    @Override
    public String queue() throws RemoteException
    {
        if (access != null && access.isQueue() == true)
        {
            writeLogfile("Queue");
            return "Queue";
        }
        else
        {
           return "Access Denied!"; 
        }
    }
    
    @Override
    public void topQueue(int job) throws RemoteException
    {
        if (access != null && access.isTopQueue() == true)
        {
            writeLogfile("Top Queue");
            System.out.println("Top Queue");
        }
        else
        {
            System.out.println("Access Denied!"); 
        }
    }
    
    @Override
    public void start() throws RemoteException
    {
        if (access != null && access.isStart() == true)
        {
            writeLogfile("Start");
            System.out.println("Start");
        }
        else
        {
            System.out.println("Access Denied!"); 
        }
    }
    
    @Override
    public void stop() throws RemoteException
    {
        if (access != null && access.isStop() == true)
        {
            writeLogfile("Stop");
            System.out.println("Stop");
        }
        else
        {
            System.out.println("Access Denied!"); 
        }
    }
    
    @Override
    public void restart() throws RemoteException
    {
        if (access != null && access.isRestart() == true)
        {
            writeLogfile("Restart");
            System.out.println("Restart");
        }
        else
        {
            System.out.println("Access Denied!"); 
        }
    }
    
    @Override
    public void status() throws RemoteException
    {
        if (access != null && access.isStatus() == true)
        {
            writeLogfile("Status");
            System.out.println("Status");
        }
        else
        {
            System.out.println("Access Denied!"); 
        }
    }
    
    @Override
    public void readConfig(String parameter) throws RemoteException
    {
        if (access != null && access.isReadConfig() == true)
        {
            writeLogfile("Read Config");
            System.out.println("Read Config");
        }
        else
        {
            System.out.println("Access Denied!"); 
        }
    }
    
    @Override
    public void setConfig(String parameter, String value) throws RemoteException
    {
        if (access != null && access.isSetConfig())
        {
            writeLogfile("Set Config");
            System.out.println("Set Config");
        }
        else
        {
            System.out.println("Access Denied!"); 
        }
    }
    
    @Override
    public boolean login(User user) throws RemoteException {
        
        String userfileName = "users.txt";
        String roleFileName = "Roles.txt";
        String userRoleFileName = "UsersRole.txt";
        BufferedReader br = null;
        String line = "";
        String split = ",";
        ArrayList<User> userList = new ArrayList<>();
        ArrayList<Roles> roleList = new ArrayList<>();
        ArrayList<UsersRole> userRoleList = new ArrayList<>();
        access = new Access(false,false,false,false,false,false,false,false,false);
        
        try {
            username = aes.decrypt(user.getUsername());
            password = aes.decrypt(user.getPassword());
            
        } catch (Exception ex) {
            Logger.getLogger(RmiDSPrintServant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            
            br = new BufferedReader(new FileReader(userfileName));
            while ((line = br.readLine()) != null) {
                String[] newUser = line.split(split);
                
                User temp = new User(newUser[0],newUser[1],newUser[2]);
                userList.add(temp);
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        try {
            
            br = new BufferedReader(new FileReader(userRoleFileName));
            while ((line = br.readLine()) != null) {
                String[] newUserRole = line.split(split);
                
                UsersRole temp = new UsersRole(newUserRole[0], Boolean.parseBoolean(newUserRole[1]), Boolean.parseBoolean(newUserRole[2]), Boolean.parseBoolean(newUserRole[3]), Boolean.parseBoolean(newUserRole[4]), Boolean.parseBoolean(newUserRole[5]));
                userRoleList.add(temp);
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        try {
            
            br = new BufferedReader(new FileReader(roleFileName));
            while ((line = br.readLine()) != null) {
                String[] newAccess = line.split(split);
                
                Roles temp = new Roles(newAccess[0], Boolean.parseBoolean(newAccess[1]), Boolean.parseBoolean(newAccess[2]), Boolean.parseBoolean(newAccess[3]), Boolean.parseBoolean(newAccess[4]), Boolean.parseBoolean(newAccess[5]), Boolean.parseBoolean(newAccess[6]), Boolean.parseBoolean(newAccess[7]), Boolean.parseBoolean(newAccess[8]), Boolean.parseBoolean(newAccess[9]));
                roleList.add(temp);
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        for(UsersRole u : userRoleList){
            if(u.getUsername().equals(username))
            {
                if(u.isManager())
                {
                    for(Roles r : roleList)
                    {
                        if(r.getRole().equals("Manager"))
                        {
                            if(r.isPrint())
                            {
                                access.setPrint(true);
                            }
                            if(r.isQueue())
                            {
                                access.setQueue(true);
                            }
                            if(r.isTopQueue())
                            {
                                access.setTopQueue(true);
                            }
                            if(r.isStart())
                            {
                                access.setStart(true);
                            }
                            if(r.isStop())
                            {
                                access.setStop(true);
                            }
                            if(r.isRestart())
                            {
                                access.setRestart(true);
                            }
                            if(r.isStatus())
                            {
                                access.setStatus(true);
                            }
                            if(r.isReadConfig())
                            {
                                access.setReadConfig(true);
                            }
                            if(r.isSetConfig())
                            {
                                access.setSetConfig(true);
                            }
                        }
                    }
                }
                if(u.isServiceTechnician())
                {
                    for(Roles r : roleList)
                    {
                        if(r.getRole().equals("ServiceTechnician"))
                        {
                            if(r.isPrint())
                            {
                                access.setPrint(true);
                            }
                            if(r.isQueue())
                            {
                                access.setQueue(true);
                            }
                            if(r.isTopQueue())
                            {
                                access.setTopQueue(true);
                            }
                            if(r.isStart())
                            {
                                access.setStart(true);
                            }
                            if(r.isStop())
                            {
                                access.setStop(true);
                            }
                            if(r.isRestart())
                            {
                                access.setRestart(true);
                            }
                            if(r.isStatus())
                            {
                                access.setStatus(true);
                            }
                            if(r.isReadConfig())
                            {
                                access.setReadConfig(true);
                            }
                            if(r.isSetConfig())
                            {
                                access.setSetConfig(true);
                            }
                        }
                    }
                }
                if(u.isInspector())
                {
                    for(Roles r : roleList)
                    {
                        if(r.getRole().equals("Inspector"))
                        {
                            if(r.isPrint())
                            {
                                access.setPrint(true);
                            }
                            if(r.isQueue())
                            {
                                access.setQueue(true);
                            }
                            if(r.isTopQueue())
                            {
                                access.setTopQueue(true);
                            }
                            if(r.isStart())
                            {
                                access.setStart(true);
                            }
                            if(r.isStop())
                            {
                                access.setStop(true);
                            }
                            if(r.isRestart())
                            {
                                access.setRestart(true);
                            }
                            if(r.isStatus())
                            {
                                access.setStatus(true);
                            }
                            if(r.isReadConfig())
                            {
                                access.setReadConfig(true);
                            }
                            if(r.isSetConfig())
                            {
                                access.setSetConfig(true);
                            }
                        }
                    }
                }
                if(u.isPowerUser())
                {
                    for(Roles r : roleList)
                    {
                        if(r.getRole().equals("PowerUser"))
                        {
                            if(r.isPrint())
                            {
                                access.setPrint(true);
                            }
                            if(r.isQueue())
                            {
                                access.setQueue(true);
                            }
                            if(r.isTopQueue())
                            {
                                access.setTopQueue(true);
                            }
                            if(r.isStart())
                            {
                                access.setStart(true);
                            }
                            if(r.isStop())
                            {
                                access.setStop(true);
                            }
                            if(r.isRestart())
                            {
                                access.setRestart(true);
                            }
                            if(r.isStatus())
                            {
                                access.setStatus(true);
                            }
                            if(r.isReadConfig())
                            {
                                access.setReadConfig(true);
                            }
                            if(r.isSetConfig())
                            {
                                access.setSetConfig(true);
                            }
                        }
                    }
                }
                if(u.isUser())
                {
                    for(Roles r : roleList)
                    {
                        if(r.getRole().equals("User"))
                        {
                            if(r.isPrint())
                            {
                                access.setPrint(true);
                            }
                            if(r.isQueue())
                            {
                                access.setQueue(true);
                            }
                            if(r.isTopQueue())
                            {
                                access.setTopQueue(true);
                            }
                            if(r.isStart())
                            {
                                access.setStart(true);
                            }
                            if(r.isStop())
                            {
                                access.setStop(true);
                            }
                            if(r.isRestart())
                            {
                                access.setRestart(true);
                            }
                            if(r.isStatus())
                            {
                                access.setStatus(true);
                            }
                            if(r.isReadConfig())
                            {
                                access.setReadConfig(true);
                            }
                            if(r.isSetConfig())
                            {
                                access.setSetConfig(true);
                            }
                        }
                    }
                }
                
            }
                
        }
        System.out.println("Sådan ser access ud: " + access.isPrint() + "    " + access.isQueue() + "   " + access.isTopQueue() + "   " + access.isStart() + "    " + access.isStop() + "   " + access.isRestart() + "   " + access.isStatus() + "   " + access.isReadConfig() + "   " + access.isSetConfig());
        for (User users : userList) {
            if(users.getUsername().equals(username))
            {
                
                String saltedPassword = password + users.getSalt();
                if(users.getPassword().equals(shaHash.hash(saltedPassword)))
                {
                    writeLogfile("Login");
                    return true;
                }
                else
                {
                    try {
                        Thread.sleep(5000);
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        
        
        return false;
    }
    
    public void writeLogfile(String method)
    {
        File logFile = new File("logfile.txt");
        
        try{
            if(logFile.exists() == false)
            {
                logFile.createNewFile();
            }
            
            PrintWriter pw = new PrintWriter(new FileWriter(logFile, true));
            long ms = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");
            Date result = new Date(ms);
            pw.append(username + " has used method: " + method + ". Time: " + sdf.format(result) + "\n");
            pw.close();
            System.out.println("Done writing");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
    }
}
