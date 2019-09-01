Running the server - (for those who clone and want to run locally)

- Navigate to the **/Extra** folder. It contains all the Service resources and the workflow under the name **Procedure.docx**
- Open the **Service** folder then **pesapp**
- Run the **createdb.js** file. Run it once as it is a destructive operation (since it is in testing phase) and it will create all
the tables and insert event data. Check console. 
- If there has been any connection errors open **connection.js** and input your SQL username and password
- Run **Start.js**
- Remember to change to URL to your local url and port


Endpoints

**GET** 

https://pesaapp.herokuapp.com/valid?phone=phone&password=password -validate user

https://pesaapp.herokuapp.com/featured - return featured events

https://pesaapp.herokuapp.com/search/tinga -  search for the event "tinga"(input other name )

https://pesaapp.herokuapp.com/category/Art -  return all the art events

https://pesaapp.herokuapp.com/category/Gaming -  get gaming events

https://pesaapp.herokuapp.com/category/Music - return music events.

https://pesaapp.herokuapp.com/getall -  get all the events from all the tables

https://pesaapp.herokuapp.com/favorite?phone=703318241  - get favorite events of user with phone ?


**POST**

https://pesaapp.herokuapp.com/valid?phone=phone&password=password&email=email  - post registration data

https://pesaapp.herokuapp.com/valid?phone=phone&title=title    			-get a users favourite
