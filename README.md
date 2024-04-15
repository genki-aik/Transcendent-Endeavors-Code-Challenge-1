# Transcendent Endeavors Code Challenge 1
Author: Genki Aikawa

This is my code to answer #1 on the Transcendent Endeavors code challenge! The goal of this code challenge is to allow the user to input a number, and return the input number doubled and then squared. In the process, the input number and the input number that was doubled and squared needs to be stored in a database.

For this challenge, I have elected to use a Spring Boot backend and a React Frontend, with MongoDB as my database. This is a rather simple challenge, but I decided to separate by backend and frontend to show what I would do given a real-world example and problem. I use HTTP to communicate between the backend and the frontend.

I attempt to follow best practices where applicable, but since this is a rather simple project and not a production ready project, there are some things that are considered "bad practices". I will discuss in detail later in this README.

## How to Start the Project
1. Clone the repo by `git clone <this repo's HTTPS clone>`
2. Open up your favorite IDE and open the project you just cloned
3. First, the environment variables needs to be set. The best practice is to ignore `.env` files since I do not want any malicious actors calling my API or DB. HOWEVER, since this is a simple project, I have decided to include the `.env` file within the repository.
    - I use a free MongoDB instance and my Spring Application is not for production use so I don't mind allowing other users to hit my endpoint or db.
4. Let's start the backend first.
    - Change directory into the backend folder by typing in the command `cd backend` from root folder.
    - Now that you are within the `backend` directory, run the command `./gradlew bootRun` to start the backend server locally. Ensure that port 8080 is free. 
5. Now let's start the frontend to interact with the backend. Open up a new terminal window without terminating the backend server that is running locally.
    - Change into the `frontend` directory by running the command `cd frontend` (This command is run from the root directory). Just make sure you are in the `frontend` directory. You can check that by typing in the command `pwd` on terminal.
    - We need to generate `node_modules` first. Run the command `npm install`.
    - Once that is done, start the frontend by typing in the command `npm start`.
6. Go to `localhost:3000` (The port number that the frontend is running on). Now you should be able to interact with the website. Type in a number to see your desired results!

## Frontend UI
![Screenshot 2024-04-15 at 3 32 59 AM](https://github.com/genki-aik/Transcendent-Endeavors-Code-Challenge-1/assets/72573851/04a2eceb-eada-467f-b114-be76dd6e3d6c)

I opened the network tab so that you can see the request being sent to my backend endpoint `storeNumber` once the button is clicked.

## MongoDB Entry
![Screenshot 2024-04-15 at 3 33 43 AM](https://github.com/genki-aik/Transcendent-Endeavors-Code-Challenge-1/assets/72573851/1f0a6a06-6e4c-4f11-9a28-9f6acffc569a)

The result is populated within my database.

## Coding Decisions
For me, I like to keep my code readable and simple. That means small functions with good names, variables with good names, and code that is self-explanatory. You might notice the lack of comments within my code, and that is because I believe comments are not necessarily good. When code gets updated, comments can get outdated, leading to confusion in the future. While I worked at big tech comapnies such as Meta and Palantir, they all follow these coding paradigms.

There are use cases where comments might be needed, but this is not the case here.

## Design Decisions
### Backend
For the backend, I used Spring Boot to create the API that will take in the input number and do the necessary calculations, and putting those values into the MongoDB database.

I created an object `InputNumberResult` that will contain the input number and all of its calculation results. In terms of naming, this seemed the most reasonable to me.

With Spring, I expose the backend API within the `InputNumberResultController` class. The controller class will delegate the business logic to a service class called `InputnumberResultService` that will access the database and make db operations there. This adds a separation of concerns pattern which I believe is a better thing to do instead of directly accessing the db within the controller class.

I elected to use `long` type as the type for the input number and its calculated results because squaring the double of the input number can produce a quite large number. Even though I have not added it, it is probably best to add a number limit so that it does not exceed the `long` type max number within Java, producing an unexpected result. Arguably, this validation should be done in the frontend.

#### Caching
In a real world, I would add an in-memory cache if the code challenge asked me to get certain values from the DB. The reason being network calls are slow compared to in-memory calls to the cache, so if there is a hot key (value that is inputted a lot), that should be cached.

I would probably use `Caffeine` or `Redis` as my cache here. Since this project is extremely simple, a cache here is totally unnecessary, but something to think about when working on production software.

#### Testing
I am a strong believer in writing unit tests. For this simple project, I don't necessarily think a unit test is that necessary because we are just performing basic arithmetic operations; however, I have elected to add a unit test for the service class that will perform the business logic to show what I would normally do in a production environment.

The test is named in a way where it is easy to see what the test is for. I named the test `InputNumberResultServiceTest` since I am testing the `InputNumberResultService`. I mock the `MongoOperations` class that perform the MongoDB operation to insert and read data from the db. Even though there is nothing to assert for here, because the arithmetic operations are straightforward, I ensure that the correct `InputNumberResult` object is passed in with accurate numbers using the `when` function in Mockito.

#### CORS Configuration
In order for my frontend (client) to hit my backend (server) POST endpoint, I need to tell my backend server to allow any website or browser to access my API. This is certainly not a good practice, but for the sake of this code challenge, I have allowed any website to hit my endpoint. Since this is not a critical endpoint, I don't anticipate any problems here.

#### MongoDB
For the DB, I have used MongoDB, which is a document based NoSQL database. For this challenge, I actually think a database is not necessary since we aren't pulling anything from the db; we just perform a arithmetic operation and return the result to the client. However, to simplify the code challenge, I decided to use a NoSQL database since there are no relations to maintain here.

Document based databases are useful for the flexible schema, so for the data that I am storing, this is probably the most simple and efficient way to store the data. I index the data using the input number as the key. The reason is that I do not want duplicated entries for the same number. If I use the input number as the key, there will be no duplicated entries because there can only be one unique key.

Since we are writing a lot to the db, and there are no complex reads (or any reads for that matter) being done here, NoSQL makes the most sense to me.

### Frontend
For the frontend, I used React and TypeScript to create the simple one page UI. Nothing much to add here, I just used `axios` to call my POST API that I created in the backend. This should return a valid HTTP response. Now the best practice here might be to handle different HTTP errors differently, but to keep it simple, I elected not to.

I have also added a simple validation so that the user input has to be a valid number; I believe validation should be done on the frontend in this case and the backend should not concern itself with whether or not the request body is valid.

## Thank you! Please let me know if you have any questions regarding the decisions I made or anything about me!
