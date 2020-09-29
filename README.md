# dice-roll-simulator

Application simulates dice rolls and allows to fetch statistics for performed simulations.
Implementation based on Spring-boot framework with Hiberante in persitence layer. Application is deployed on embedded tomcat server. 

It's worth to highlight the approach of used solution. 
All performed operations are calculated in memory without flushing the chunk of generated data. 
It causes a problem with the size of heap space, when a large volume of data will generate. 
The solution of this issue is reactive approaching, which could be consider to implement.

# How to run an application:

## 1. Fetch the source code from repository

``` bash
  git clone git@github.com:gwarywod/dice-roll-simulator.git
```
### 2. Build and run application.
  
  In the root directory call:
  
``` bash
  ./mvnw install spring-boot:run
```

# Usage

Application is listening on port 8080 and provides two endpoints:

|No.|Method|Resource|Query Params|
| ---- | ---- | ---- | ---- |
|1| GET | /api/dice-roll/simulation | dicePieces,diceSides,rolls | 
|2| GET | /api/dice-roll/statistics | none | 

### Example

To perform simulation just call:

```bash
curl 'http://localhost:8080/api/dice-roll/simulation/?dicePieces=2&diceSides=4&rolls=200'
```

it produces list of pairs numbers where each of them is a sum of rolled and its number of occurrence

```json
{
    "occurrenceFrequency": {
        "2": 14,
        "3": 27,
        "4": 43,
        "5": 52,
        "6": 32,
        "7": 25,
        "8": 7
    }
}
```
After that we can call to fetch the statistics

```bash
curl 'http://localhost:8080/api/dice-roll/statistics'
```

```json
{
    "statistics": [
        {
            "diceNumber": 2,
            "diceSides": 4,
            "totalSimulations": 1,
            "totalRolls": 200,
            "numberOccurrences": [
                {
                    "value": 2,
                    "occurrencePercent": "7.00%"
                },
                {
                    "value": 3,
                    "occurrencePercent": "13.50%"
                },
                {
                    "value": 4,
                    "occurrencePercent": "21.50%"
                },
                {
                    "value": 5,
                    "occurrencePercent": "26.00%"
                },
                {
                    "value": 6,
                    "occurrencePercent": "16.00%"
                },
                {
                    "value": 7,
                    "occurrencePercent": "12.50%"
                },
                {
                    "value": 8,
                    "occurrencePercent": "3.50%"
                }
            ]
        }
    ]
}
```
> Note
    - Application uses in memory database and after restarting the all stored data disappear.  
