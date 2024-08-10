# Repository for AWS Backend

## Create MYSQL

### 1. Create new docker image mysql

```
docker network create aws-training
```
```
docker run -d --name mysql-server -p 3306:3306 --network aws-training -e "MYSQL_ROOT_PASSWORD=mysql" mysql
```

### 2. Create table in mysql
`docker exec -it mysql-server mysql -u root -p mysql`

```
    create database testdb;

    use testdb;

    CREATE TABLE posts (
        id varchar(255),
        title varchar(255),
        published boolean,
        tags varchar(255)
    );
```

## How to build new image.
```
    docker build -t thoaitrands/aws-backend:0.0.1 .
```

## How to run.
### 1. Run directory.
```
    mvn spring-boot:run
```

### 2. Run by jar file
```
    java -jar target/aws-backend-0.0.1-SNAPSHOT.jar
```

### 3. Using by docker images
```
    docker run --network aws-training -e DATABASE_URL=jdbc:mysql://mysql-server:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false thoaitrands/aws-backend:0.0.1 -e DATABASE_USER=root -e DATABASE_PASSWORD=mysql
```



