# sharding

https://techblog.woowahan.com/2687/  
I followed the techniques outlined in this article.


# How to run
1. docker-compose -f ./docker/docker-compose.yml up -d  
2. run project
3. run curl
```
curl -X POST http://localhost:8080 \
  -H "Content-Type: application/json" \
  -d '{
        "userId": 123
      }'

curl -X GET http://localhost:8080/123
```
