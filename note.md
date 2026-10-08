

For rating server, we need to make the mongo server up. By running the 
command: sudo mongod --dbpath=/Users/pritamkumarray/data/db


For ratelimiter we need the redis server to be up



## Docker command to run the redis server -> here redis-server is the container name

docker run -d \
  --name redis-server \
  -p 6379:6379 \
  redis:latest
  

# Stop
docker stop redis-server

# Start
docker start redis-server


# Check
docker ps

# Redis CLI
docker exec -it redis-server redis-cli

# Remove container completely
docker rm -f redis-server