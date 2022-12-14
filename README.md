# Spring boot application to
- check if a SSN in valid or not
- get forex for currencies SEK, USD, EUR using cache
- schedule task that fetches exchange rates for SEK, USD and EUR every hour and updates database

## Technologies used
 ### - Java Spring boot
 ### - Quartz Job Scheduler
 ### - MySQL
 ### - Hibernate



## Start up the application
- Clone the github repo
- Create database named "forexapi" or any other name (YOUR_DB_NAME)
- Update values in application.properties for following variables:
      
        spring.datasource.url=jdbc:mysql://localhost:3308/YOUR_DB_NAME?useSSL=false
        spring.datasource.username=root 
        spring.datasource.password=
        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
        forex.api_key=YOUR_API_KEY_FROM_ "https://apilayer.com" 

### Example endpoints
- /api/forex


       -  /api/forex
       -  Method : GET
       - Request body 
            example:    
            {
                "from":"EUR",
                "to":"USD",
                "from_amount": 595
            }
 
         - Response body 
            example:    
            {
                "from":"EUR",
                "to":"USD",
                "from_amount": 595,
                "exchange_rate": 0.980478
            }

- /api/startJob
- start Scheduled execution of exchange rate update manually
- fetch and store exchange rates once an hour

       -  /api/startJob
       -  Method : GET
                 



        

