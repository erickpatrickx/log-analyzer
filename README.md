# Test project


## Instructions

### Configurations before running app
- Update file /src/main/resources/application.properties with correct configuration for the MySQL database 
- create database parserwb


### Generate```
mvn install
```

### Run the .jar generated in target folder. Example:
```
java -jar parser.jar 2017-01-01.13:00:00 hourly 100

Obs.


The jar execution syntax has changed due to the use of spring boot that changes the behavior of MANIFEST.MF


```

## MYSQL Schema (not needed as the app will create the schema)

```sql
CREATE TABLE `logger` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `http_code` varchar(255) DEFAULT NULL,
  `http_from` varchar(255) DEFAULT NULL,
  `http_method` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `blocked_ip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
```

## SQL Examples

- Find IPs that made more than a cetain number of requests for a given time period:

```sql
SELECT ip
FROM log
WHERE start_date >= '2017-01-01 13:00:00' AND start_date < '2017-01-01 14:00:00'
GROUP BY ip
HAVING COUNT(ip) > 100
```

- Find requests made by a given IP:

```sql
SELECT http_from
FROM log
WHERE ip = '192.168.228.188'
```
