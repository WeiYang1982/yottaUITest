# yottawebUITest

How to use
--
  1. Start selenium-standalone server use this command:
  
      java -Dwebdriver.chrome.driver=${PATH}\chromedriver.exe -jar ${PATH}\selenium-server-standalone-3.3.1.jar 
      
    ps: this chromedriver.exe must be suitable for the system  
    
    
  2. Use maven verify to start test and use -Dcucumber.options to control tags. like this:
  
      mvn -Dcucumber.options="--tags @spl" verify
      
    ps: you can change the @spl witch you want to run
    
    
Config
---
  1. Change the selenium_server_url in the custom.properties
  2. Change the browser type in the same file
  3. Change the base url in the same file
  
  
      
