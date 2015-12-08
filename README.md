# PA165_pneuservis  
EN: Git repository for team project - pneuservis for course PA165 / 2015  
SK: Git repozitár pre teamový projekt - pneuservis pre predmet PA165 / 2015  

##Oficiální zadání:  
Vytvořte systém, ve kterém si bude moci zákazník vybrat nové obutí pro svoje vozidlo z nabídky pneuservisu. Pneuservis bude poskytovat různé typy pneumatik, velikosti, od různých výrobců, s různou cenou. Výběr zákazníka bude zaznamenán a evidován pod jeho jménem, adresou, telefonem a typem vozidla, které chce přezout. Kromě přezouvání bude pneuservis poskytovat i nějaké doprovodné služby, jakými může být kontrola sbíhavosti kol apod. Ty si může zákazník přiobjednat k přezutí kol. Jeden zákazník si může vytvořit více objednávek. Systém také bude schopný poskytnout přehled všech objednávek dle zákazníků, celkovou cenu obejdnávky apod.

###Teamový kolegovia:  
448288 - Komárek, Ondřej  
436321 - Mészáros, Filip (team leader)
374029 - Šumaj, Jozef  
436353 - Holý, Jakub  

###Detaily:  
Zákazník je schopný si cez webovú službu vybrať a následne objednať pneumatiky pre svoje vozidlo, prípadne službu pre svoje vozidlo, ako napríklad kontrolu zbiehania pneumatík, atď. Po zvolení daných produktov a služieb o ktoré má zákazník záujem sa zákazníkovi objaví ponuka pre vyplnenie osobných údajov. Po vyplnení osobných údajov má zákazník možnosť odoslať objednávku pneuservisu. Pracovník v pneuservise na základe aktuálnych objednávok zavolá zákazníkovi a dohodne s ním, kedy sa zákazník ukáže aj s vozidlom v pneuservise. Zamestnanec pneuservisu môže každú objednávku zmeniť priamo v systéme. Platby prevádza zákazník zamestnancovi priamo pri osobnej návšteve pneuservisu. O vybavenie a uzatváranie objednávok sa stará priamo zamestnanec pneuservisu pri osobnej návšteve pneuservisu zákazníkom. 

###Use Case Diagram:

![alt tag](use_case_diagram.jpg)  


###Class Diagram: 

![alt tag](class_diagram.jpg)


##Project requirements


###Milestone 3 requirements:  
- User interface:
   - [ ] To start the web application you have to configure maven plugin (tomcat7 or jetty). 
   It is very important that it’s super easy to start the web application from command line. 
   So for example using tomcat7 plugin, the following sequence of commands should start the web application: 
   “mvn clean install && cd web && mvn tomcat7:run”
   - [ ] The web application must be available on the following HTTP context: http://localhost:8080/pa165
   - [ ] You application should use in­memory database. 
   This means that after application restart (killing web container and starting it again with mvn tomcat7:run) the data may be reset.
   - [ ] Implement the user interface using Spring MVC or Angular JS. Spring MVC is preferred. 
   Angular will be covered at the last minute so it might not be the best choice for you if you are not familiar with it already.
   - [ ] The user interface should allow to carry out all business functions of your system.
   - [ ] You should fill in all the necessary data automatically. So for example you can use Web Listener to load data during Web Application startup.
   - [ ] Make sure there are validations implemented on user interface.
   - [ ] Your user interface should use either Facade layer or REST layer to access the system. Do not directly access database and do not directly use Service layer.
   - [ ] The web interface layer may reside in separate maven module (if this is helpful).
   - [ ] Each member of the team must implement (mostly independently, without copy­pasting) part of user interface. Including controller and view.
- REST Layer:
   - [ ] Your application should have a basic REST interface.
   - [ ] At least one entity and operations on that entity must be exposed.
   This is mainly to demonstrate you can implement this, it’s not necessary to have all application functions accessible through this interface.
   - [ ] The REST must be accessible at http://localhost:8080/pa165/rest. It is not required to have the interface secured.
   - [ ] You should include a README file with instructions how to test the REST interface (e.g. CURL commands for command line)
- Security:
   - [ ] There should be at least 2 roles in the system (e.g. Administrator, User). 
   Each role should have some differences in user interface or in capabilities.
   - [ ] There should be login form (not HTTP Basic)
   - [ ] Registration is NOT required. You can prefill the users and their passwords in the database.
   - [ ] Password should not be saved in the database in open form.
- [ ] Evaluate another team project.

#####Points gained from milestone3:  
X points gained from milestone 3 out of total 15 points  


###Milestone 2 requirements:  
- [x] Implement Facade layer interfaces and implementations.
   - [x] Everything that should happen in the system must be available through these interfaces (create entities, deleting them etc).
   - [ ] You must have at least 2 non-trivial business functions on Service Layer (the example project contains several of them). Service layer is not always just a place to delegate to DAO.
- [x] Other points about the Facade and Service layers:
   - [x] All the classes must be wired via dependency injection. Your service objects should obtain an instance of the EntityManager that way.
   - [x] All the facade interfaces must not reference entities, but Transfer Objects only.
   - [x] All service interfaces must reference only entities, not Data Transfer Objects.
   - [x] You can use Dozer framework to map entity instances to transfer objects. The mapping may be done on Facade Layer.
   - [x] Facade layer is used to drive transactions.
- [x] Change layout of your project to Multimodule Maven project (tutorial here http://maven.apache.org/guides/getting-started/index.html). Your project should have 3 separate modules:
   - DAO layer
   - Service Layer and Facade Layer implementation
   - API layer - just DTOs and facade interfaces!
- [x] Facade layer will use Service layer and Service layer will use DAO layer.
- [x] Make sure that DataAccessException or its subclass is thrown in case of any exception on a the DAO layer.
- [x] Implement simple unit tests for facade layer. Just one simple test per method is enough. This is mainly so that it’s easy to verify the Facade layer works.
- [x] There must be extensive unit tests for the service layer (particularly for your 2 business functions) and all the tests of service layer must use Mock DAO objects.
- [x] Evaluate another team project.

#####Points gained from milestone2:  
13 points gained from milestone 2 out of total 15 points  
-2 points was for: Chybi vam druha business funkce.  

###Milestone 1 requirements 2:  
- [x] Create a project in a Github repository that is publicly accessible (for read) choose a short and descriptive name. Create some project wiki to publish other information for your project.
- [x] On the project wiki there will be a project description, a use case diagram and a class diagram for entity classes. There will be at least two user roles in the use case diagram. Associations between entities will be present in the class diagram.  
- [x] Create 4 entity classes for your project.
- [x] Create a DAO layer interface (with proper javadoc).
- [x] Create the JPA implementation of the DAO classes (CRUD operations are enough for the first checkpoint).
- [x] Create unit tests for DAO classes (use in-memory database).
- [x] Every team member should work with different entities on different parts of the project (e.g. member 1 will create implementation of DAO for entity A, but member 2 will create unit test for entity A). In every class there will  be javadoc @author with the name of the class author. Also you must commit into Git only the changes that you made yourself. If you commit on behalf of somebody else this will not be regarded as his work!
- [x] The project will be built using maven, and make sure you have all dependencies correctly configured, so it is possible to build it using just the command line.
- [x] Evaluate another team project.

#####Points gained from milestone 1:  
7 points gained from milestone 1 out of total 10 points   
-3 points was for: Java konfigurace Springu byla jedna ku jedné zkopírována z ukázkového projektu. Jak jsem již několikrát zmiňoval, toto je neakceptovatelné.  