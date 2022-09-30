# app-final
Business requirements: 
 1. Develop web service for News Management system with the following entities: 
 ![image](https://user-images.githubusercontent.com/111504102/193266878-e94eafb1-313f-4b9e-9854-f5bc9aa21493.png)

•	 – unique value

•	All Name, Title and Content fields are required

2. The system should expose REST APIs to perform the following operations:

-	CUD operations (what_is_CRUD) for News and Comment. If new tags and authors are passed during creation/modification – they should be created in the DB

-	Get News:

   o	get all

   o	by Id

   o	search (all params are optional and can be used in conjunction):
   
      *	by tag names and tag ids (many tags)
      
      *	by author name (one author)
      
      *	by part of Title 
      
      *	by part of Content

