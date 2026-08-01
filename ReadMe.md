
## DevAnalyzer
DevAnalyzer is an **Ai powered App** which can analyze profile and give a sarcastic analysis based on various datas.

The Analysis feature is available for **Github**,**Leetcode** and **Codeforces**.

## Features

- Analyze GitHub profiles
- Analyze LeetCode profiles
- Analyze Codeforces profiles
- AI-generated Sarcastic roast of the profile
- Dockerized for easy deployment
- RESTful APIs built with Spring Boot

## Tech Stack

- Java 25
- Spring Boot
- Maven
- Docker
- Groq API
- GitHub API
- LeetCode GraphQL API
- Codeforces API

## API Endpoints

### GitHub

GET /analyze/github/{username}

### LeetCode

GET /analyze/leetcode/{username}

### Codeforces

GET /analyze/codeforces/{username}

## How to run locally

    git clone https://github.com/Toshit-Jain07/DevAnalyzer.git

    cd DevAnalyzer

## Set Variables and Port
    
### In 
**\src\main\resources\application.properties**

    groq.api.key= YOUR KEY!!
### Run without docker
    ./mvnw spring-boot:run

### with docker
    
    ./mvnw clear package

    docker build -t devanalyzer .

    docker run -p {port}:8080 devanalyzer -e GROQ_API_KEY=YOURKEY!!

## Author 

### Toshit Jain
    


    

