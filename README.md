# Spring Boot Hello World API

This is a small Spring-Boot Hello World application to demonstrate a quick and simple API.

## DB Setup

Create a Google CloudSQL instance, disable Public IP address and enable Private IP address only. Also select "Allow SSL Connections Only" in the instance's security settings. 

- Create Database instance called test
- In the instance test, create a table called ACCOUNTS using the following DDL 

```sql
    CREATE TABLE ACCOUNTS (
        id int,
        name varchar(255),
        desc varchar(255)
    );
```

## Generating the keystore and truststore

### trustCertificateKeyStore

```bash
keytool -importcert -file server-ca.pem -keystore truststore -storepass password@1234

keytool -importkeystore -srckeystore truststore  -srcstorepass password@1234 -destkeystore truststore -deststoretype pkcs12 -deststorepass password@1234
```

### clientCertificateKeyStore

```bash
openssl pkcs12 -export -in client-cert.pem -inkey client-key.pem -passout pass:password@1234 -out client-keystore.p12

keytool -importkeystore -srckeystore client-keystore.p12 -srcstoretype pkcs12 -srcstorepass password@1234 -destkeystore keystore -deststoretype pkcs12 -deststorepass password@1234
```

## Packaging the code into a jar

```bash
mvn clean install
```

## Containerizing the code

Change the [PROJECT_ID] with your project's ID

```bash
docker build -t gcr.io/[PROJECT_ID]/hello-world-spring-app:latest
docker push gcr.io/[PROJECT_ID]/hello-world-spring-app:latest
```

## Creating configMap for keystore and truststore

```bash
kubectl create cm trust-keys --from-file=keystore --from-file=truststore
```

## Deploying the app on GKE

Change the [PROJECT_ID] with your project's ID

```bash
kubectl create -f deployment.yaml
kubectl expose deployment test --port 80 --target-port 8080 --type LoadBalancer
```


> There is nothing to display on the `/` endpoint. Please visit `[LB_IP]/getTuples`