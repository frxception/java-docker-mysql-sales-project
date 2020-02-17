# ZUHLKE Sales Order (CSV DB writer)

This is an app to read and write csv files that will save in the database

####Technology Stacks
- SpringBoot
- SpringBatch
- JDK 1.8
- Docker
- MySQL
- H2
- IntelliJ

##### Why SpringBatch in the project?
To provide and give us a more robust batch processing and highly-scalable enterprise solution to execute the jobs for uploading large volumes of file(s)/data to store in the Database.
Includes also loggin/tracing, transacation management, job processing statistics, job restart, skip and resource management.

### Getting Started

##### Upload sales.csv in the database
- `http://localhost:8080/` - Main page
- `http://localhost:8080/sales-upload` - Upload file using Spring Batch


Running the project

1. Install docker on your local machine. You can find the installation steps on this link: https://docs.docker.com/install/
2. Open terminal and navigate to project directory
2. Run command: ```mvn clean install -DskipTests```
3. Run command: ```mvn clean package -DskipTest```


### Build images and deploy docker stack

1. Run command to build images: ```docker-compose build```
2. Run command to deploy stack locally: ```docker stack deploy -c docker-compose.yml <some-stack-name>```
3. Run command ```docker ps``` to see running containers.

### View docker container logs

1. Run command: ```docker ps```
2. Run command: ```docker logs -f <container_id>```


## Notes and Limitations/Constraints

- Needed to add and modify the table because there are some missing columns from the csv files in order for the entity mapping to work. The given schema is not correct and i need to change it.
- Although, it can handle some bad and invalid items (using spring batch skip policy to catch any exception) but duplicate records can be still be read and persisted
- No proper data item sanitation
- Takes longer and not optimize to handle/upload very huge or multiple csv files ( not able to used spring batch approach like multithreaded, async/parallel or partionioning batch processing)
- Invalid values/item are checked and assigned a default value type in order for some record to be read/write
- CSV file is located in a fixed location (/resources/data.sales.csv)
- Manual datasource configration (between H2 or MySQL)
- No unit testing

##TODO's

- Implement a proper frontend client application using React/Angular/Vuejs
- Cover all exception handling for each csv item and its contents (sanitize item content, handle duplicate, item parsing for invalid values)
- Add upload page web controller to upload files in using the input file HTML component
- Create controller to display meta data for the upload files
- Make the batch application as standalone reusable jar/executable library
- Create datasource switcher or configuration (to work with various databases)
- Unit testing (coverage for all case scenarios in uploading the sales csv file)
- Use Multi partioned-scheme in spring batch to read/upload multiple csv files to handle large volumes of data
- Integrate RabbitMQ for the message broker or any scheduler system to launch/trigger spring batch jobs
- Use CI/CD
- Dockerize the spring batch app, mysql, rabbitmq and nginx
- Use kubernetes to orchestrate the containers
