# BoardgameListingWebApp
The board game database Full-Stack web application displays the lists of board games and their reviews. Anyone can see the board game lists and the reviews, but they are required to log in to add/ edit the board games and their reviews; 'Users' have the authority to add board games to the list and add reviews, and 'Managers' have the authority to edit/ delete the reviews.
<br><br>
<ul>
<li>Full-Stack Application using Java, Spring Boot, Thymeleaf, HTML5, CSS, JavaScript, and H2 Database Engine (In-memory)</li>
<li>Created UI components with Thymeleaf and beautified them with Twitter Bootstrap</li>
<li>Spring Security was used for authentication by allowing the users to authenticate with username and password, and authorization was implemented by granting different permissions based on the roles (non-members, users, and managers). Non-members only can see the boardgame lists and reviews, users can add boardgames and write reviews, and managers can edit and delete the reviews</li>
<li>Junit test framework was used for unit testing. Most methods and functionalities in Controller classes have been Unit Tested and adding/ deleting functions in Database classes have been also Unit Tested</li>
<li>Implemented Spring MVC best practices to segregate the roles of views, controllers, and database package</li>
<li>Added schema.sql file to customize the schema and input the initial data and enabled to manage them using JDBC and CRUD operation</li>
<li>Thymeleaf Fragments to reduce redundancy of repeating HTML elements including head, footer, and navigation</li>
</ul>