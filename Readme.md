This project is made to demonstrate the utility of **Java SERVLETS** and **APACHE TOMCAT**.  

---
### Requirements to use the web-app after cloning:  

* all machines needs to have tomcat configured  
* in */apache-tomcat-9.0.24/conf*, open **tomcat-users.xml**    
add:  
  <*role rolename="manager-gui"/*>  
  <*user username="admin" password="admin" roles="manager-gui"/*>  
under  <*tomcat users*>  tags.  
* to start **"tomcat"** go to path */apache-tomcat-9.0.24/bin* and run **"startup.bat"**. This is **ONLY** necessary for the main machine server!  

---
### Players step after configure tomcat:  

Open a browser and write: *http://"main-servers-ip":8080*  
1. Enter Manager App  
2. Deploy **"TicTacToe-1.0-SNAPSHOT.war"** located in *TicTacToe\target*  
3. After, click on it. You will have the url like: *http://"main-servers-ip":8080/TicTacToe-1.0-SNAPSHOT/*  
4. In the continuation of the url write: *"?player=x&line=1&column=1"*
 * player= "x" or "0" (depends what player you want to be);  
 * line= "numbers between 0 and 2" (coordinate *x* of the table);  
 * column= "numbers between 0 and 2" (coordinate *y* of the table);*
 * Example: http://localhost:8080/TicTacToe-1.0-SNAPSHOT/?player=x&line=1&column=2
5. You should see in the **tomcat terminal** on the main server the results.  

Enjoy testing it! :)  
*(Any number of servers can join and try to play at the same time xD)*
