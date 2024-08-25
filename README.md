This simple project represents a programme where you can create notes about your friends.  
All information  is stored in database using mysql server and JDBC.  
  
!!All information below is about what programme include(without reading it programme will not work)  
  
  
before start the programme:  
  
1.dependencies  
I used maven to download mysql:  
<dependency>  
            <groupId>mysql</groupId>  
            <artifactId>mysql-connector-java</artifactId>  
            <version>8.0.33</version>  
</dependency>  
  
2.about Connetion  
I used mysql workbench to create shema(you need to do the same)  
after that you need put URL,NAME,PASSWORD after one line into file "InfoConnection.txt" to connect to your DB  
  
Programms menu  
  
*Menu 1  
1 - input (after that command you have to put your "login" and "password" to enter to your accout and get access to "menu 2"  
2 - registration (registration for your account(need put your name,surname,login,password))  
3 - exit(close programme)  
  
*Menu 2  
1 - output friend(s) (have two type: first is output on console all friends,second is output certain friend by name or surname)  
2 - input new friend (have two type: first is input all information about friend,second is input only neccesery info)  
3 - change friend (change selected type of selected((by id)) friend)  
4 - delete friend (delete selected friend(by id))  
5 - back (leave from "menu 2" to "menu 1")
